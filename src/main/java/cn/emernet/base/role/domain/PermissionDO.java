package cn.emernet.base.role.domain;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author lilei
 * @ClassName:PermissionDO
 * @Description:
 * @Date 2020/7/20
 * @Version 1.0
 */
@Table
@Entity(name = "permission")
@Data
public class PermissionDO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty
    private int id;
    /**
     * 权限接口地址
     */
    private String url;
    /**
     * 权限名称
     */
    private String name;
    /**
     * 权限描述
     */
    private String description;

    /**
     * 权限code
     */
    private String code;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 权限类型
     */
    private String type;
    /**
     * 权限状态
     */
    private String status;

    public PermissionDO(String url, String name, String code, Date createTime, String type, String status) {
        this.url = url;
        this.name = name;
        this.code = code;
        this.createTime = createTime;
        this.type = type;
        this.status = status;
    }

    public PermissionDO() {
    }
}
