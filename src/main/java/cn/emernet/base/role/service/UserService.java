package cn.emernet.base.role.service;

import cn.emernet.cdamb.common.bean.JsonResult;
import cn.emernet.base.role.domain.UserDO;

import javax.servlet.http.HttpSession;

/**
 * @author lilei
 */
public interface UserService {
    /**
     * 新增用户
     *
     * @param loginName
     * @param password
     * @param orgId
     * @param accountType
     * @param userName
     * @return
     */
    public JsonResult insertUser(String loginName, String password, Integer orgId, String accountType, String userName);

    /**
     * 修改密码
     *
     * @param id
     * @param userName
     * @param loginName
     * @param oldPassword
     * @param newPassword
     * @param password
     * @return
     */
    public JsonResult updatePassword(Integer id, String userName, String loginName, String oldPassword, String newPassword, String password);

    /**
     * 用户登录
     *
     * @param loginName
     * @param password
     * @param session
     * @return
     */
    public UserDO login(String loginName, String password, HttpSession session);
    /**
     * 禁用用户
     * @param id
     * @return
     */
    public JsonResult disableUser(Integer id);
}
