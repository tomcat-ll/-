package cn.emernet.base.role.repository;

import cn.emernet.base.role.domain.RoleDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author lilei
 */
public interface RoleRepository extends JpaRepository<RoleDO,Integer> , JpaSpecificationExecutor<RoleDO> {
    /**
     * 查询角色
     * @param id
     * @return
     */
    public RoleDO findById(int id);

    /**
     * 根据机构id查询角色
     * @param orgId
     * @return
     */
    public List<RoleDO> findByOrgId(Integer orgId);

    /**
     * 查询角色，返回id
     * @param orgId
     * @return
     */
    @Query("select id from role where orgId=?1 ")
    public List<Integer> findByOrgIdfRoleId(Integer orgId);
}
