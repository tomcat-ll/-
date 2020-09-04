package cn.emernet.base.role.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
/**
 * @author lilei
 * @ClassName:RoleDO
 * @Description:
 * @Date 2020/7/20
 * @Version 1.0
 */
@Table
@Entity(name = "role")
@Data
public class RoleDO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty
    private Integer id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 所属机构id
     */
    private Integer orgId;
    /**
     * 角色描述
     */
    private String roleDescribe;
    /**
     * 创建时间
     */
    private Date createTime;

    public RoleDO(String name, Integer orgId, String roleDescribe, Date createTime) {
        this.name = name;
        this.orgId = orgId;
        this.roleDescribe = roleDescribe;
        this.createTime = createTime;
    }

    public RoleDO() {
    }
}
