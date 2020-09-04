package cn.emernet.base.role.repository;

import cn.emernet.base.role.domain.OrgMenuDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author lilei
 */
public interface OrgMenuRepository extends JpaRepository<OrgMenuDO, Integer>, JpaSpecificationExecutor<OrgMenuDO> {
    /**
     * 查询机构下的菜单
     *
     * @param orgId
     * @return
     */
    @Query("select menuId from org_menu where orgId=?1")
    public List<Integer> findByOrgId(Integer orgId);

    /**
     * 删除菜单根据机构id
     *
     * @param orgId
     */
    public void deleteAllByOrgId(Integer orgId);


}
