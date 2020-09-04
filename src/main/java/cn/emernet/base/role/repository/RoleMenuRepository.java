package cn.emernet.base.role.repository;

import cn.emernet.base.role.domain.RoleMenuDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author lilei
 */
public interface RoleMenuRepository extends JpaSpecificationExecutor<RoleMenuDO>, JpaRepository<RoleMenuDO,Integer> {
    /**
     * 根据角色id删除
     * @param roleId
     */
    public void deleteAllByRoleId(Integer roleId);

    /**
     * 根据角色id查询菜单id
     * @param roleId
     * @return
     */
    @Query("select menuId from role_menu where roleId=?1")
    public List<Integer> findByRoleId(Integer roleId);


}
