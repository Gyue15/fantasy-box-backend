package cn.edu.nju.fantasybox.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 允许localhost的跨域请求
 *
 * @author zjy
 */
@Configuration
public class CorsConfig {
    private org.springframework.web.cors.CorsConfiguration buildConfig() {
        org.springframework.web.cors.CorsConfiguration corsConfiguration = new org.springframework.web.cors.CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); // 允许所有跨域
        corsConfiguration.addAllowedHeader("*"); // 允许所有的Header
        corsConfiguration.addAllowedMethod("*"); // 允许所有的方法
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig()); //这个配置对所有访问路径生效
        return new CorsFilter(source);
    }
}
