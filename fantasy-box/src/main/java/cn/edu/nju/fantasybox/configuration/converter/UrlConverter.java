package cn.edu.nju.fantasybox.configuration.converter;

import org.dozer.DozerConverter;
import org.springframework.beans.factory.annotation.Value;

public class UrlConverter extends DozerConverter<String, String> {

    @Value("${file-service.url-prefix}")
    private String urlPrefix;

    public UrlConverter() {
        super(String.class, String.class);
    }

    @Override
    public String convertTo(String s, String s2) {
        return urlPrefix + s;
    }

    @Override
    public String convertFrom(String s, String s2) {
        return s2.replace(urlPrefix, "");
    }
}
