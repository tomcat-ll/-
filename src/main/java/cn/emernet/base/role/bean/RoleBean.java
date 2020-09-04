package cn.emernet.base.role.bean;

import cn.emernet.base.role.domain.RoleDO;
import lombok.Data;

import java.util.List;
/**
 * @author lilei
 * @ClassName:RoleBean
 * @Description:
 * @Date 2020/7/20
 * @Version 1.0
 */

@Data
public class RoleBean {
    private long total;
    private List<RoleDO> data;
}
