package cn.edu.nju.fantasybox.configuration;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;

@Configuration
public class DozerConfig {

    @Bean(name = "org.dozer.Mapper")
    public DozerBeanMapper dozer() {
        List<String> mappingFiles = Collections.singletonList("dozer/dozer-mapping.xml");
        DozerBeanMapper dozerBean = new DozerBeanMapper();
        dozerBean.setMappingFiles(mappingFiles);
        return dozerBean;
    }
}
