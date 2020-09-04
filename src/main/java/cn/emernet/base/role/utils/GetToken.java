package cn.emernet.base.role.utils;

import cn.emernet.base.role.bean.AccessToken;
import cn.emernet.base.role.bean.Urlbean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName:GetToken
 * @Description: 获取access_token
 * @Author lilei
 * @Date 2020/7/30
 * @Version 1.0
 * */
public class GetToken {

    public static AccessToken getAccessToken(String loginName, String password) {
        RestTemplate restTemplate = new RestTemplate();
        String url1 = Urlbean.urlpath+"/oauth/token?username=" + loginName + "&password=" + password + "&grant_type=password&client_id=dev&client_secret=dev";
        AccessToken jsonResult = restTemplate.getForObject(url1, AccessToken.class);
        return jsonResult;
    }

    public static AccessToken refreshToken(String refreshToken) {
        RestTemplate restTemplate = new RestTemplate();
        String url = Urlbean.urlpath+"/oauth/token?grant_type=refresh_token&refresh_token=" + refreshToken + "&client_id=dev&client_secret=dev";
        AccessToken jsonResult = restTemplate.getForObject(url, AccessToken.class);
        return jsonResult;
    }
}
