package cn.emernet.base.role.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * @author lilei
 * @ClassName:RoleMenuDO
 * @Description:
 * @Date 2020/7/20
 * @Version 1.0
 */

@Table
@Entity(name = "role_menu")
@Data
public class RoleMenuDO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty
    private Integer id;
    /**
     * 角色id
     */
    private Integer roleId;
    /**
     * 菜单id
     */
    private Integer menuId;


    public RoleMenuDO() {
    }

    public RoleMenuDO(Integer roleId, Integer menuId) {
        this.roleId = roleId;
        this.menuId = menuId;
    }
}
