package cn.edu.nju.fantasybox;

import cn.edu.nju.fantasybox.util.RSAEncrypt;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.edu.nju.fantasybox.mapper")
@SpringBootApplication
public class FantasyBoxApplication {

    public static void main(String[] args) {
        SpringApplication.run(FantasyBoxApplication.class, args);
    }

}
