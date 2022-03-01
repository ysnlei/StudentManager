package club.justca.studentmanager.config;

import club.justca.studentmanager.interceptor.LoginInterceptor;
import club.justca.studentmanager.interceptor.RoleInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/student/**") //拦截所有/student请求
                .addPathPatterns("/teacher/**") //拦截所有/teacher请求
                .addPathPatterns("/upload/**")
                .excludePathPatterns("/login", "/userLogin", "/teacherLogin", "/studentLogin");

        registry.addInterceptor(new RoleInterceptor())
                .addPathPatterns("/teacher/**")
                .addPathPatterns("/student/**")
                .excludePathPatterns("/login", "/userLogin", "/teacherLogin", "/studentLogin");
    }

    //wangEditor的配置文件映射
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:E:/upload/");
    }
}