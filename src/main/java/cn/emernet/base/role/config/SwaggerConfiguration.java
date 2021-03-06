package cn.emernet.base.role.config;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author lilei
 * @ClassName:SwaggerConfiguration
 * @Description:
 * @Date 2020/7/20
 * @Version 1.0
 */
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class SwaggerConfiguration {

    /**
     * API 信息定义
     *
     * @return
     */
    @Bean(value = "defaultApi")
    @Order(value = 0)
    public Docket defaultApi() {

        //Swagger扫描包
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.emernet.base.role.controller"))
                .paths(PathSelectors.any())
                .build();
        /*.securityContexts(Lists.newArrayList(securityContext(),securityContext1())).securitySchemes(Lists.<SecurityScheme>newArrayList(apiKey(),apiKey1()));*/
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("融云Api文档")
                .description("RESTful APIs")
                .termsOfServiceUrl("http://127.0.0.1:8080/")
                //.contact("188140040@qq.com")
                .version("1.0")
                .build();
    }
}
