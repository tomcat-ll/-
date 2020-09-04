package cn.emernet.base.role.repository;

import cn.emernet.base.role.domain.RolePermissionDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
/**
 * @author lilei
 */
public interface RolePermissionRepository extends JpaRepository<RolePermissionDO,Integer>, JpaSpecificationExecutor<RolePermissionDO> {
    /**
     * 根据角色id删除
     * @param roleId
     */
    public void deleteAllByRoleId(Integer roleId);


}
