package cn.emernet.base.role.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author lilei
 * @ClassName:UserDO
 * @Description:
 * @Date 2020/7/20
 * @Version 1.0
 */
@Table
@Entity(name = "user")
@Data
public class UserDO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty
    private Integer id;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 登录名
     */
    private String loginName;
    /**
     * 机构id
     */
    private Integer orgId;
    /**
     * 密码
     */
    private String password;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 账户类型
     */
    private String accountType;

    public UserDO() {
    }

    public UserDO(Date createTime, Date updateTime, String userName, String loginName, Integer orgId, String password, Integer status, String accountType) {
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.userName = userName;
        this.loginName = loginName;
        this.orgId = orgId;
        this.password = password;
        this.status = status;
        this.accountType = accountType;
    }
}

