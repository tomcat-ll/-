package cn.emernet.base.role.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * @author lilei
 * @ClassName:UserRoleDO
 * @Description:
 * @Date 2020/7/20
 * @Version 1.0
 */
@Table
@Entity(name = "user_role")
@Data
public class UserRoleDO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty
    private Integer id;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 角色id
     */
    private Integer roleId;

    public UserRoleDO(Integer userId, Integer roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    public UserRoleDO() {
    }
}
