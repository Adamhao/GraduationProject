package cn.edu.qdu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by Bro_Dong on 2019/4/24.
 */
@Configuration
public class MyConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("user/login");
        registry.addViewController("/login").setViewName("user/login");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/register").setViewName("user/register");
        registry.addViewController("/updatePassword").setViewName("user/updatePassword");
        registry.addViewController("/updatePassByPhone").setViewName("user/updatePassByPhone");
        registry.addViewController("/updatePassByEmail").setViewName("user/updatePassByEmail");
        registry.addViewController("/order").setViewName("order/order");
    }
}
