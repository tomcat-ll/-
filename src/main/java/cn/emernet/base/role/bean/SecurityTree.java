package cn.emernet.base.role.bean;


import lombok.Data;

import java.util.List;
/**
 * @author lilei
 * @ClassName:SecurityTree
 * @Description:
 * @Date 2020/7/20
 * @Version 1.0
 */

@Data
public class SecurityTree {
    private SecurityData data;
    private List<SecurityTree> children;


}
