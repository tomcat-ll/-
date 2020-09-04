package cn.emernet.base.role.service;

import cn.emernet.base.role.bean.RoleBean;
import cn.emernet.base.role.bean.SecurityTree;
import cn.emernet.cdamb.common.bean.JsonResult;
import cn.emernet.base.role.domain.RoleDO;

import java.util.List;

/**
 * @author lilei
 */
public interface RoleService {
    /**
     * 获取医院的菜单权限树
     *
     * @param orgId
     * @return
     */

    public JsonResult<SecurityTree> getOrgMenu(Integer orgId);

    /**
     * 医院新增，修改权限
     *
     * @param orgId
     * @param menuIds
     * @return
     */

    public JsonResult insertOrgMenu(Integer orgId, List<Integer> menuIds);

    /**
     * 获取医院下的角色
     *
     * @param orgId
     * @return
     */

    public JsonResult<List<RoleDO>> getOrgRole(Integer orgId);

    /**
     * 新增角色
     *
     * @param orgId
     * @param name
     * @param roleDescribe
     * @return
     */

    public JsonResult insertRole(Integer orgId, String name, String roleDescribe);

    /**
     * 角色修改，新增权限
     *
     * @param roleId
     * @param menuIds
     * @return
     */
    public JsonResult insertRolePermission(Integer roleId, List<Integer> menuIds);

    /**
     * 用户设定角色
     *
     * @param userId
     * @param orgId
     * @param roleIds
     * @return
     */

    public JsonResult insertUserRole(Integer userId, Integer orgId, List<Integer> roleIds);

    /**
     * 删除角色
     *
     * @param roleId
     * @return
     */

    public JsonResult deleteRole(Integer roleId);

    /**
     * 修改角色信息
     *
     * @param roleId
     * @param name
     * @param roleDescribe
     * @return
     */

    public JsonResult updateRole(Integer roleId, String name, String roleDescribe);

    /**
     * 分页条件查询角色
     *
     * @param page
     * @param limit
     * @param orgId
     * @return
     */

    public JsonResult<RoleBean> getRole(Integer page, Integer limit, Integer orgId);

    /**
     * 查询角色的菜单节点
     * @param roleId
     * @return
     */
    public List<Integer> getRoleMenuNode(Integer roleId);

    /**
     * 查询医院下的菜单节点
     * @param orgId
     * @return
     */
    public List<Integer> getOrgMenuNode(Integer orgId);
    /**
     * 查询用户的菜单
     * @param userId
     * @return
     */
    public List<String> getUserMenuCode(Integer userId);
}
