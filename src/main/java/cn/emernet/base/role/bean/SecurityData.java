package cn.emernet.base.role.bean;

import lombok.Data;
/**
 * @ClassName:SecurityData
 * @Description:
 * @Author lilei
 * @Date 2020/7/20
 * @Version 1.0
 * */
@Data
public class SecurityData {
    private Integer id;

    private String menuUrl;

    private String name;

    private Integer state;

    private Integer parentId;

    private Integer sort;

    public SecurityData(Integer id, String name, Integer parentId, Integer sort) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.sort = sort;
    }

    public SecurityData(Integer id, String menuUrl, String name, Integer state, Integer parentId, Integer sort) {
        this.id = id;
        this.menuUrl = menuUrl;
        this.name = name;
        this.state = state;
        this.parentId = parentId;
        this.sort = sort;
    }

    public SecurityData() {
    }
}
