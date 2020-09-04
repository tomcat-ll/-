package cn.emernet.base.role.controller;

import cn.emernet.base.role.bean.SecurityTree;
import cn.emernet.cdamb.common.bean.JsonResult;
import cn.emernet.base.role.domain.RoleDO;
import cn.emernet.base.role.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName:RoleController
 * @Description:
 * @Author lilei
 * @Date 2020/7/20
 * @Version 1.0
 */
@Api(tags = "新增角色权限")
@RestController("RolePermission")
@CrossOrigin
public class RoleController {

    @Autowired
    RoleService roleService;

    @ApiOperation(value = "医院机构添加，修改权限")
    @PostMapping("/InsertOrgMenu")
    public JsonResult insertOrgMenu(
            @ApiParam(value = "医院id", required = true) @RequestParam() Integer orgId,
            @ApiParam(value = "菜单id", required = true) @RequestParam() List<Integer> menuIds) {
        return roleService.insertOrgMenu(orgId, menuIds);
    }

    @ApiOperation(value = "获取医院机构的菜单权限")
    @GetMapping("/getOrgMenuTree")
    public JsonResult<SecurityTree> getOrgMenu(
            @ApiParam(value = "医院机构id", required = true) @RequestParam() Integer orgId) {
        return roleService.getOrgMenu(orgId);
    }

    @ApiOperation(value = "获取医院下的权限菜单节点")
    @GetMapping("/getOrgMenuNode")
    public JsonResult<List<Integer>> getOrgMenuNode(
            @ApiParam(value = "医院机构id", required = true) @RequestParam() Integer orgId) {
        return JsonResult.buildOne(roleService.getOrgMenuNode(orgId));
    }

    @ApiOperation(value = "医院下新建角色")
    @PostMapping("/insertRole")
    public JsonResult insertRole(
            @ApiParam(value = "医院id", required = true) @RequestParam() Integer orgId,
            @ApiParam(value = "角色名字", required = true) @RequestParam() String name,
            @ApiParam(value = "角色描述", required = true) @RequestParam() String roleDescribe) {
        return roleService.insertRole(orgId, name, roleDescribe);
    }

    @ApiOperation(value = "获取医院下的角色")
    @GetMapping("/getOrgRole")
    public JsonResult<List<RoleDO>> getOrgRole(
            @ApiParam(value = "医院id", required = true) @RequestParam() Integer orgId) {
        return roleService.getOrgRole(orgId);
    }

    @ApiOperation(value = "为角色设权限")
    @PostMapping("/insertRolePermission")
    public JsonResult insertRolePermission(
            @ApiParam(value = "角色Id", required = true) @RequestParam() Integer roleId,
            @ApiParam(value = "菜单id", required = true) @RequestParam() List<Integer> menuIds) {
        return roleService.insertRolePermission(roleId, menuIds);
    }

    @ApiOperation(value = "用户赋予角色(新增，修改)")
    @PostMapping("/insertUserRole")
    public JsonResult insertUserRole(
            @ApiParam(value = "用户id", required = true) @RequestParam() Integer userId,
            @ApiParam(value = "医院id", required = true) @RequestParam() Integer orgId,
            @ApiParam(value = "角色id", required = true) @RequestParam() List<Integer> roleIds) {
        return roleService.insertUserRole(userId, orgId, roleIds);
    }

    @ApiOperation(value = "删除角色")
    @PostMapping("/deleteRole")
    public JsonResult deleteRole(
            @ApiParam(value = "角色id", required = true) @RequestParam() Integer roleId) {
        return roleService.deleteRole(roleId);
    }

    @ApiOperation(value = "修改角色信息")
    @PostMapping("/updateRole")
    public JsonResult updateRole(
            @ApiParam(value = "角色id", required = true) @RequestParam() Integer roleId,
            @ApiParam(value = "角色名字") @RequestParam(required = false) String name,
            @ApiParam(value = "角色描述") @RequestParam(required = false) String roleDescribe) {
        return roleService.updateRole(roleId, name, roleDescribe);
    }

    @ApiOperation(value = "分页条件查询角色")
    @GetMapping("/getRole")
    public JsonResult<List<RoleDO>> getRole(
            @ApiParam(value = "页数", required = true) @RequestParam() Integer page,
            @ApiParam(value = "每页条数", required = true) @RequestParam() Integer limit,
            @ApiParam(value = "医院id") @RequestParam(required = false) Integer orgId) {
        return JsonResult.buildOne(roleService.getRole(page, limit, orgId));
    }

    @ApiOperation(value = "获取角色的菜单权限节点")
    @GetMapping("/getRoleMenu")
    public JsonResult<List<Integer>> getRoleMenuNode(
            @ApiParam(value = "角色的唯一id", required = true) @RequestParam() Integer roleId) {
        return JsonResult.buildOne(roleService.getRoleMenuNode(roleId));
    }
    @ApiOperation(value = "获取用户的菜单节点")
    @GetMapping("/getUserMenuCode")
    public JsonResult<List<String>> getUserMenuCode(
            @ApiParam(value = "用户id",required = true)@RequestParam() Integer userId){
        return JsonResult.buildOne(roleService.getUserMenuCode(userId));
    }

    @ApiOperation(value = "测试")
    @GetMapping("/hello")
    public String hello(String name) {
        return "你好------" + name;
    }

}
