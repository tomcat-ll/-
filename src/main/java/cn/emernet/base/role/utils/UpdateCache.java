package cn.emernet.base.role.utils;


import cn.emernet.base.role.bean.Urlbean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName:UpdateCache
 * @Description: 更新权限缓存
 * @Author lilei
 * @Date 2020/7/20
 * @Version 1.0
 * */
public class UpdateCache {
    public static void updatePermission() {
        RestTemplate restTemplate = new RestTemplate();
       restTemplate.getForObject(Urlbean.urlpath+"/updateCache",String.class);
  }
}
