package cn.emernet.base.role.repository;

import cn.emernet.base.role.domain.UserRoleDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author lilei
 */
public interface UserRoleRepository extends JpaRepository<UserRoleDO,Integer>, JpaSpecificationExecutor<UserRoleDO> {
    /**
     * 根据用户id删除
     * @param userId
     */
    public void deleteAllByUserId(Integer userId);

    /**
     * 根据角色id删除
     * @param roleId
     */
    public void deleteAllByRoleId(Integer roleId);

    /**
     * 根据用户id查询角色
     * @param userId
     * @return
     */
    @Query("select roleId from user_role where userId=?1")
    public List<Integer> findByUserId(Integer userId);
}
