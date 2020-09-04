package cn.emernet.base.role.repository;

import cn.emernet.base.role.domain.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
/**
 * @author lilei
 */
public interface UserRepository extends JpaRepository<UserDO,Integer>, JpaSpecificationExecutor<UserDO> {
    /**
     * 判断登录名是否已存在
     * @param loginName
     * @return
     */
    public Boolean existsByLoginName(String loginName);

    /**
     * 查询user详情
     * @param id
     * @return
     */
    public UserDO findByid(Integer id);

    /**
     * 通过登录名密码，查询用户
     * @param loginName
     * @param password
     * @return
     */
    public UserDO findByLoginNameAndPassword(String loginName,String password);

}
