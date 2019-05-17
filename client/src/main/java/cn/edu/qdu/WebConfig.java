package cn.edu.qdu;

import cn.edu.qdu.common.web.AuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
@EnableSpringDataWebSupport
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthenticationInterceptor()).addPathPatterns(
                "/user/*","/order/*","/cart/*"
        ).excludePathPatterns("/user/login","/user/reg","/user/logout",
                "/user/activation","/user/checkCode","/user/checkPhone","/user/checkEmail","/user/checkPhoneIsTrue","/user/checkNameIsTrue",
                "/user/checkName","/user/addUser");

    }

    @Bean(name = "multipartResolver")
    public StandardServletMultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

}
