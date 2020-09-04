package cn.emernet.base.role.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName:MenuDO
 * @Description:
 * @author lilei
 * @Date 2020/7/20
 * @Version 1.0
 * */
@Table
@Entity(name = "menu")
@Data
public class MenuDO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty
    private Integer id;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 菜单code
     */
    private String menuCode;
    /**
     * 菜单名称
     */
    private String menuName;
    /**
     * 菜单地址
     */
    private String menuUrl;
    /**
     * 菜单父节点
     */
    private Integer parentId;
    /**
     * 菜单树的节点顺序
     */
    private Integer sort;
    /**
     * 状态
     */
    private Integer state;
    /**
     * 更改日期
     */
    private Date updateTime;

    public MenuDO() {
    }

    public MenuDO(Date createTime, String menuCode, String menuName, String menuUrl, int parentId, int sort, int state) {
        this.createTime = createTime;
        this.menuCode = menuCode;
        this.menuName = menuName;
        this.menuUrl = menuUrl;
        this.parentId = parentId;
        this.sort = sort;
        this.state = state;
    }
}
