package cn.emernet.base.role.repository;

import cn.emernet.base.role.domain.MenuDO;
import cn.emernet.base.role.interfaceutil.MenuCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

/**
 * @author lilei
 */
public interface MenuRepository extends JpaRepository<MenuDO, Integer>, JpaSpecificationExecutor<MenuDO> {
    /**
     * 通过id查询菜单
     *
     * @param id
     * @return
     */
    public MenuDO findById(int id);


    /**
     * 查询所有菜单code
     *
     * @param
     * @return
     */
    @Query("select menuCode from menu")
    public List<String> findMenuCode();


    /**
     * 查询菜单的code属性
     *
     * @param ids
     * @return
     */
    public List<MenuCode> findByIdIn(Set<Integer> ids);

}
