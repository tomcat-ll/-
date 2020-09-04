package cn.emernet.base.role.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName:Urlbean
 * @Description: http访问外部url地址
 * @Author lilei
 * @Date 2020/8/26
 * @Version 1.0
 */
@Data
@Component
public class Urlbean {
    public static String urlpath="";
    /**
     *  springboot给静态属性设置参数，通过set方法
     */
    @Value("${app.service.security.url}")
    public void setUrlpath(String url) {
        urlpath = url;
    }

}
