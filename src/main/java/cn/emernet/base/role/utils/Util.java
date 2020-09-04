package cn.emernet.base.role.utils;

import cn.emernet.base.role.bean.SecurityData;
import cn.emernet.base.role.bean.SecurityTree;


import java.util.ArrayList;
import java.util.List;

/**
 * @author Util
 * @ClassName:UserRoleDO
 * @Description:
 * @Date 2020/7/20
 * @Version 1.0
 */
public class Util {
    /**
     * 生成树的结构的方法
     *
     * @param lists
     * @param securityTree
     * @return
     */
    public static SecurityTree creatTree(List<SecurityData> lists, SecurityTree securityTree) {

        if (securityTree != null) {
            //取出我的节点
            int node = securityTree.getData().getId();
            List<SecurityTree> children = securityTree.getChildren();
            for (SecurityData data : lists) {
                //取出父节点
                int parentNode = data.getParentId();
                //如果父节点等于我的节点,说明该条数据是树的子集
                if (node == parentNode) {
                    SecurityTree t1 = new SecurityTree();
                    t1.setData(data);
                    t1.setChildren(new ArrayList<SecurityTree>());
                    children.add(t1);
                    creatTree(lists, t1);
                }
            }
            securityTree.setChildren(children);
        } else {
            securityTree = new SecurityTree();
            SecurityData data1 = new SecurityData(0, "顶级", -1, 1);
            securityTree.setData(data1);
            securityTree.setChildren(new ArrayList<SecurityTree>());
            creatTree(lists, securityTree);
        }
        return securityTree;
    }

}
