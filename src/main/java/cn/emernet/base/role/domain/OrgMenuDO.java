package cn.emernet.base.role.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * @author lilei
 * @ClassName:OrgMenuDO
 * @Description:
 * @Date 2020/7/20
 * @Version 1.0
 */
@Table
@Entity(name = "org_menu")
@Data
public class OrgMenuDO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty
    private Integer id;
    /**
     * 机构id
     */
    private Integer orgId;
    /**
     * 菜单id
     */
    private Integer menuId;

    public OrgMenuDO(Integer orgId, Integer menuId) {
        this.orgId = orgId;
        this.menuId = menuId;
    }

    public OrgMenuDO() {
    }
}
