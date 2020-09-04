package cn.emernet.base.role.repository;

import cn.emernet.base.role.domain.MenuPermissionDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author lilei
 */
public interface MenuPermissionRepository extends JpaRepository<MenuPermissionDO, Integer>, JpaSpecificationExecutor<MenuPermissionDO> {
    /**
     * 通过菜单id查询权限id
     *
     * @param menuId
     * @return
     */
    @Query(value = "select permissionId from menu_permission where menuId=?1")
    public List<Integer> findByMenuId(int menuId);



}
