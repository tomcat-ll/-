package cn.emernet.base.role.bean;

import lombok.Data;

/**
 * @ClassName:AccessToken
 * @Description: 获取token的bean
 * @Author lilei
 * @Date 2020/7/30
 * @Version 1.0
 * */
@Data
public class AccessToken {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private long expires_in;
    private String scope;
}
