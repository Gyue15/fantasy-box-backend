package cn.edu.nju.fantasybox.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FilePathConfig implements WebMvcConfigurer {

    @Value("${file-service.local}")
    private String localPath;

    @Value("${file-service.url-pattern}")
    private String urlPattern;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(urlPattern).addResourceLocations("file:"+localPath);
    }
}
