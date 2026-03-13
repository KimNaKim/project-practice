package com.camping.erp.global.config;

import com.camping.erp.domain.user.UserRepository;
import com.camping.erp.global.auth.AdminInterceptor;
import com.camping.erp.global.auth.DevAutoLoginInterceptor;
import com.camping.erp.global.auth.LoginInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final UserRepository userRepository;

    @Value("${dev.auto-login:false}")
    private boolean devAutoLogin;

    @Value("${dev.auto-login.username:ssar}")
    private String devAutoLoginUsername;

    public WebMvcConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path uploadDir = Paths.get("./uploads");
        String uploadPath = uploadDir.toFile().getAbsolutePath();

        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:///" + uploadPath + "/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Dev 자동 로그인 (모든 경로에서 세션 주입)
        if (devAutoLogin) {
            registry.addInterceptor(new DevAutoLoginInterceptor(userRepository, devAutoLoginUsername))
                    .addPathPatterns("/**");
        }

        // 모든 인증이 필요한 경로
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/mypage/**", "/reservations/**", "/admin/**", "/boards/**", "/replies/**", "/qna/new",
                        "/reviews/new")
                .excludePathPatterns("/boards/[0-9]+"); // 상세 조회는 비로그인 허용 (필요시)

        // 관리자 전용 경로
        registry.addInterceptor(new AdminInterceptor())
                .addPathPatterns("/admin/**");
    }
}
