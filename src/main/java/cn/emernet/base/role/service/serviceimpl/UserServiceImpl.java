package cn.emernet.base.role.service.serviceimpl;

import cn.emernet.base.role.exception.MsgException;
import cn.emernet.base.role.service.UserService;
import cn.emernet.base.role.utils.CheckPassword;
import cn.emernet.cdamb.common.bean.JsonResult;
import cn.emernet.base.role.domain.UserDO;
import cn.emernet.base.role.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;

/**
 * @ClassName:UserServiceImpl
 * @Description:
 * @Author lilei
 * @Date 2020/7/20
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    public static final String STRING = "弱";
    public static final int INT = 8;
    public static final int INT1 = 30;
    public static final int INT2 = 3;
    @Autowired
    UserRepository userRepository;

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
    @Override
    public JsonResult insertUser(String loginName, String password, Integer orgId, String accountType, String userName) {
        if (userRepository.existsByLoginName(loginName)) {
            return JsonResult.buildError("账号名已存在");
        } else {
            if (password == null || "".equals(password)) {
                password = "123456";
            }
            if (STRING.equals(CheckPassword.checkPassword(password)) || password.getBytes().length < INT) {
                return JsonResult.buildError("密码强度过低");
            }
            UserDO user = new UserDO(new Date(), new Date(), userName, loginName, orgId, DigestUtils.md5DigestAsHex(password.getBytes()), 0, accountType);
            userRepository.save(user);
            return JsonResult.buildOne("200");
        }
    }

    /**
     * 修改密码
     *
     * @param id
     * @param username
     * @param loginName
     * @param oldPassword
     * @param newPassword
     * @param password
     * @return
     */
    @Override
    public JsonResult updatePassword(Integer id, String username, String loginName, String oldPassword, String newPassword, String password) {
        if (!newPassword.equals(password)) {
            return JsonResult.buildError("两次密码不一致");
        } else if (oldPassword.equals(newPassword)) {
            return JsonResult.buildError("新密码与旧密码相同");
        } else {
            if (STRING.equals(CheckPassword.checkPassword(password)) || password.getBytes().length < INT) {
                return JsonResult.buildError("密码强度过低");
            }
            UserDO user = userRepository.findByid(id);
            user.setLoginName(loginName);
            user.setUserName(username);
            user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
            user.setUpdateTime(new Date());
            userRepository.save(user);
            return JsonResult.buildOne("200");
        }
    }

    /**
     * 登录
     *
     * @param loginName
     * @param password
     * @param session
     * @return
     */
    @Override
    public UserDO login(String loginName, String password, HttpSession session) {
        if (!checkLock(session, loginName)) {
            throw new MsgException("账号密码输错3次，锁定30分钟");
        }
        UserDO user = userRepository.findByLoginNameAndPassword(loginName, DigestUtils.md5DigestAsHex(password.getBytes()));
        if (user == null) {
            //新增登录失败记录
            addFailNum(session, loginName);
            throw new MsgException("用户名和密码错误");
        }
        if(user.getStatus()==1){
            throw new MsgException("用户名被禁用");
        }
        //清空登录失败记录
        cleanFailNum(session, loginName);
        return user;
    }

    @Override
    public JsonResult disableUser(Integer id) {
        UserDO userDO =userRepository.findByid(id);
        if(userDO!=null) {
            userDO.setStatus(1);
            userRepository.save(userDO);
        }
        return JsonResult.buildOne("200");
    }

    /**
     * 校验用户登录失败次数
     *
     * @param session
     * @param loginName
     * @return
     */
    public boolean checkLock(HttpSession session, String loginName) {
        Object o = session.getServletContext().getAttribute(loginName);
        if (o == null) {
            return true;
        }
        HashMap<String, Object> map = (HashMap<String, Object>) o;
        int num = (int) map.get("num");
        Date date = (Date) map.get("lastDate");
        long timeDifference = ((System.currentTimeMillis() - date.getTime()) / 60 / 1000);
        if (num >= INT2 && timeDifference < INT1) {
            return false;
        }
        return true;
    }

    /**
     * 新增用户登录失败次数
     *
     * @param session
     * @param loginName
     */
    public void addFailNum(HttpSession session, String loginName) {
        Object o = session.getServletContext().getAttribute(loginName);
        HashMap<String, Object> map = null;
        int num = 0;
        if (o == null) {
            map = new HashMap<String, Object>(16);
        } else {
            map = (HashMap<String, Object>) o;
            num = (int) map.get("num");
            Date date = (Date) map.get("lastDate");
            long timeDifference = ((System.currentTimeMillis() - date.getTime()) / 60 / 1000);
            if (timeDifference >= INT1) {
                num = 0;
            }
        }
        map.put("num", num + 1);
        map.put("lastDate", new Date());
        session.getServletContext().setAttribute(loginName, map);
    }

    /**
     * 清理用户登录失败的记录
     *
     * @param session
     * @param loginName
     */
    public void cleanFailNum(HttpSession session, String loginName) {
        session.getServletContext().removeAttribute(loginName);
    }


}
