package cn.emernet.base.role.repository;


import cn.emernet.base.role.domain.PermissionDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
/**
 * @author lilei
 */
public interface PermissionRepository extends JpaRepository<PermissionDO,Integer>, JpaSpecificationExecutor<PermissionDO> {



    /**
     * 返回所有id
     * @return
     */
    @Query("select id from permission ")
    public List<Integer> findAllid();


}
