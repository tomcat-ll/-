package cn.emernet.base.role.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * @author lilei
 * @ClassName:MenuPermissionDO
 * @Description:
 * @Date 2020/7/20
 * @Version 1.0
 */

@Table
@Entity(name = "menu_permission")
@Data
public class MenuPermissionDO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty
    private Integer id;
    /**
     * 权限id
     */
    private Integer permissionId;
    /**
     * 菜单id
     */
    private Integer menuId;

    public MenuPermissionDO(int permissionId, int menuId) {
        this.permissionId = permissionId;
        this.menuId = menuId;
    }

    public MenuPermissionDO() {
    }
}
