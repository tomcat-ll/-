package cn.emernet.base.role.service.serviceimpl;

import cn.emernet.base.role.bean.RoleBean;
import cn.emernet.base.role.bean.SecurityTree;
import cn.emernet.base.role.domain.*;
import cn.emernet.base.role.repository.*;
import cn.emernet.base.role.utils.UpdateCache;
import cn.emernet.base.role.utils.Util;
import cn.emernet.cdamb.common.bean.JsonResult;
import cn.emernet.base.role.bean.SecurityData;
import cn.emernet.base.role.interfaceutil.MenuCode;
import cn.emernet.base.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.transaction.support.TransactionSynchronizationUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

/**
 * @ClassName:RoleServiceImpl
 * @Description:
 * @Author lilei
 * @Date 2020/7/20
 * @Version 1.0
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    OrgMenuRepository orgMenuRepository;
    @Autowired
    PermissionRepository permissionRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    RolePermissionRepository rolePermissionRepository;
    @Autowired
    UserRoleRepository userRoleRepository;
    @Autowired
    MenuRepository menuRepository;
    @Autowired
    MenuPermissionRepository menuPermissionRepository;
    @Autowired
    RoleMenuRepository roleMenuRepository;

    /**
     * 获取医院下的权限菜单
     *
     * @param orgId
     * @return
     */

    @Override
    public JsonResult<SecurityTree> getOrgMenu(Integer orgId) {
        List<SecurityData> securityData = new ArrayList<>();
        //根据orgid查询菜单id
        List<Integer> list = orgMenuRepository.findByOrgId(orgId);
        if (list.size() >= 1) {
            for (Integer i : list) {
                MenuDO p = menuRepository.findById(i.intValue());
                SecurityData s = new SecurityData(p.getId(), p.getMenuUrl(), p.getMenuName(),p.getState(),p.getParentId(), p.getSort());
                securityData.add(s);
            }
        }
        HashSet h = new HashSet(securityData);
        securityData.clear();
        securityData.addAll(h);
        //对list排序
        //对list排序--按sort排序
        Collections.sort(securityData, new Comparator<SecurityData>() {

            @Override
            public int compare(SecurityData o1, SecurityData o2) {
                if (o1.getSort() > o2.getSort()) {
                    return 1;
                } else if (o1.getSort() < o2.getSort()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        SecurityTree securityTree = Util.creatTree(securityData, null);
        return JsonResult.buildOne(securityTree);
    }

    /**
     * 新增机构下菜单
     *
     * @param orgId
     * @param menuIds
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult insertOrgMenu(Integer orgId, List<Integer> menuIds) {
        orgMenuRepository.deleteAllByOrgId(orgId);
        //以防万一 对list去重
        HashSet h = new HashSet(menuIds);
        menuIds.clear();
        menuIds.addAll(h);
        try {
            for (Integer i : menuIds) {
                if (menuRepository.findById(i) != null) {
                    orgMenuRepository.save(new OrgMenuDO(orgId, i));
                }
            }
            return JsonResult.buildOne("200");
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.buildError(e.getMessage());
        }

    }

    /**
     * 获取医院下的角色
     *
     * @param orgId
     * @return
     */
    @Override
    public JsonResult<List<RoleDO>> getOrgRole(Integer orgId) {
        return JsonResult.buildOne(roleRepository.findByOrgId(orgId));
    }

    /**
     * 医院下创建角色
     *
     * @param orgId
     * @param name
     * @param roleDescribe
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult insertRole(Integer orgId, String name, String roleDescribe) {
        try {
            roleRepository.save(new RoleDO(name, orgId, roleDescribe, new Date()));
            return JsonResult.buildOne("200");
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.buildError(e.getMessage());
        }
    }

    /**
     * 为角色赋予菜单，接口权限
     *
     * @param roleId
     * @param menuIds
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult insertRolePermission(Integer roleId, List<Integer> menuIds) {
        //删除原来的权限
        rolePermissionRepository.deleteAllByRoleId(roleId);
        roleMenuRepository.deleteAllByRoleId(roleId);
        List<Integer> list = new ArrayList<>();
        //-1机构下为超级管理员，可以选择所有权限接口
        if (roleRepository.findById(roleId.intValue()).getOrgId() == -1) {
            list = permissionRepository.findAllid();
        }
        //获取机构下的菜单
        else {
            list = orgMenuRepository.findByOrgId(roleRepository.findById(roleId.intValue()).getOrgId());
        }
        // 交集
        menuIds.retainAll(list);
        for (Integer i : menuIds) {
            //角色菜单
            roleMenuRepository.save(new RoleMenuDO(roleId, i));
            //角色权限
            //获取菜单下的接口权限
            List<Integer> permissionids = menuPermissionRepository.findByMenuId(i);
            for (Integer k : permissionids) {
                rolePermissionRepository.save(new RolePermissionDO(roleId, k));
            }
        }
        /*
         * 当事务提交成功后，执行更新权限缓存，
         * @Transactional里的方法
         * */
        TransactionSynchronizationManager.registerSynchronization( new TransactionSynchronizationAdapter(){
            @Override
            public void afterCommit() {
                //更新权限缓存
                UpdateCache.updatePermission();
            }
        });
        return JsonResult.buildOne("200");
    }

    /**
     * 新增绑定用户角色
     *
     * @param userId
     * @param orgId
     * @param roleIds
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult insertUserRole(Integer userId, Integer orgId, List<Integer> roleIds) {
        userRoleRepository.deleteAllByUserId(userId);
        List<Integer> list = roleRepository.findByOrgIdfRoleId(orgId);
        roleIds.retainAll(list);
        for (Integer i : roleIds) {
            userRoleRepository.save(new UserRoleDO(userId, i));
        }
        return JsonResult.buildOne("200");
    }

    /**
     * 删除角色
     *
     * @param roleId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult deleteRole(Integer roleId) {
        rolePermissionRepository.deleteAllByRoleId(roleId);
        roleMenuRepository.deleteAllByRoleId(roleId);
        userRoleRepository.deleteAllByRoleId(roleId);
        roleRepository.deleteById(roleId);
        TransactionSynchronizationUtils.triggerFlush();
        /*
        * 当事务提交成功后，执行更新权限缓存，
        * @Transactional里的方法
        * */
        TransactionSynchronizationManager.registerSynchronization( new TransactionSynchronizationAdapter(){
            @Override
            public void afterCommit() {
                //更新权限缓存
                UpdateCache.updatePermission();
            }
        });

        return JsonResult.buildOne("200");
    }

    /**
     * 更新角色信息
     *
     * @param roleId
     * @param name
     * @param roleDescribe
     * @return
     */
    @Override
    public JsonResult updateRole(Integer roleId, String name, String roleDescribe) {
        RoleDO roleDO = roleRepository.findById(roleId.intValue());
        if (name != null && !"".equals(name)) {
            roleDO.setName(name);
        }
        if (roleDescribe != null && !"".equals(roleDescribe)) {
            roleDO.setRoleDescribe(roleDescribe);
        }
        roleRepository.save(roleDO);
        return JsonResult.buildOne("200");
    }

    /**
     * 获取医院下角色
     *
     * @param page
     * @param limit
     * @param orgId
     * @return
     */
    @Override
    public JsonResult<RoleBean> getRole(Integer page, Integer limit, Integer orgId) {
        Pageable pageable = PageRequest.of(page, limit, Sort.Direction.DESC, "id");
        RoleBean result = new RoleBean();
        final Integer orgId1=orgId;
        //动态查询条件
        Page<RoleDO> pages = roleRepository.findAll(new Specification<RoleDO>() {
            @Override
            public Predicate toPredicate(Root<RoleDO> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();

                if (orgId1 != null) {
                    predicate.getExpressions().add(cb.equal(root.get("orgId"), orgId1));
                }
                //不查出超级管理员--23号为超级管理员
                predicate.getExpressions().add(cb.not(root.get("id").in(23)));
                return predicate;
            }
        }, pageable);
        result.setTotal(pages.getTotalElements());
        result.setData(pages.getContent());
        return JsonResult.buildOne(result);
    }

    /**
     * 查询角色下的菜单节点
     * @param roleId
     * @return
     */
    @Override
    public List<Integer> getRoleMenuNode(Integer roleId) {
       return roleMenuRepository.findByRoleId(roleId);
    }

    /**
     * 查询医院下的权限菜单节点
     * @param orgId
     * @return
     */
    @Override
    public List<Integer> getOrgMenuNode(Integer orgId) {
        return orgMenuRepository.findByOrgId(orgId);
    }

    @Override
    public List<String> getUserMenuCode(Integer userId) {
        //查询用户有哪些角色
        List<Integer> roleIds=userRoleRepository.findByUserId(userId);
        List<String> menuCodes=new ArrayList<>();
        for (Integer roleId : roleIds) {
            //超级管理员有所有菜单权限
            if(roleId==0){
                return menuRepository.findMenuCode();
            }else{
                //查出每个角色所能看到的菜单
                HashSet<Integer> menuIds=new HashSet<>(roleMenuRepository.findByRoleId(roleId));
                List<MenuCode> list=menuRepository.findByIdIn(menuIds);
                for (MenuCode code:list) {
                    menuCodes.add(code.getMenuCode());
                }
            }
        }
        //去重
        HashSet set=new HashSet(menuCodes);
        menuCodes.clear();
        menuCodes.addAll(set);
        return menuCodes;
    }

}

