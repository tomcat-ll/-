package cn.emernet.base.role.controller;

import cn.emernet.cdamb.common.bean.JsonResult;

import cn.emernet.base.role.bean.AccessToken;
import cn.emernet.base.role.domain.UserDO;
import cn.emernet.base.role.exception.MsgException;
import cn.emernet.base.role.service.UserService;
import cn.emernet.base.role.utils.GetToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author lilei
 * @ClassName:UserController
 * @Description:
 * @Date 2020/7/20
 * @Version 1.0
 */
@Api(tags = "账号管理")
@RestController("User")
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;

    @ApiOperation(value = "创建账号用户")
    @PostMapping("/insertUser")
    public JsonResult insertUser(
            @ApiParam(value = "登录名", required = true) @RequestParam() String loginName,
            @ApiParam(value = "密码，不能为弱密码，即8位以上混合", required = true) @RequestParam() String password,
            @ApiParam(value = "机构Id", required = true) @RequestParam() Integer orgId,
            @ApiParam(value = "账号类型（人，集群，单兵，等）", required = true) @RequestParam() String accountType,
            @ApiParam(value = "用户名") @RequestParam(required = false) String userName) {
        return userService.insertUser(loginName, password, orgId, accountType, userName);
    }

    @ApiOperation(value = "修改密码")
    @PostMapping("/updatePassword")
    public JsonResult updatePossword(
            @ApiParam(value = "账号id", required = true) @RequestParam() Integer id,
            @ApiParam(value = "用户名", required = true) @RequestParam() String userName,
            @ApiParam(value = "登录名", required = true) @RequestParam() String loginName,
            @ApiParam(value = "旧密码", required = true) @RequestParam() String oldPassword,
            @ApiParam(value = "新密码", required = true) @RequestParam() String newPassword,
            @ApiParam(value = "确认密码", required = true) @RequestParam() String password) {
        return userService.updatePassword(id, userName, loginName, oldPassword, newPassword, password);
    }

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public JsonResult login(
            @ApiParam(value = "登录名", required = true) @RequestParam() String loginName,
            @ApiParam(value = "密码", required = true) @RequestParam() String password,
            HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(15 * 60);
            UserDO user = userService.login(loginName, password, session);
            //登录成功
            //存入session 设置会话时长15分分钟
            session.setAttribute(loginName, user);
            AccessToken accessToken=GetToken.getAccessToken(loginName,password);
            //保证每次登录为最新的token
            return JsonResult.buildOne(GetToken.refreshToken(accessToken.getRefresh_token()));

        } catch (MsgException e) {
            return JsonResult.buildError(e.getMessage());
        }
    }

    @ApiOperation(value = "退出")
    @PostMapping("/logout")
    public JsonResult logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return JsonResult.buildOne("200");
    }
    @ApiOperation(value = "禁用用户")
    @PostMapping("/disableUser")
    public JsonResult disableUser( @ApiParam(value = "账号id", required = true) @RequestParam() Integer id){
        return userService.disableUser(id);
    }

}
