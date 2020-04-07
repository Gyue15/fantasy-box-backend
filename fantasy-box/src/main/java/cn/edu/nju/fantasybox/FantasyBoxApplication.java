package cn.edu.nju.fantasybox;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("cn.edu.nju.fantasybox.mapper")
@SpringBootApplication
@ComponentScan(basePackages={"cn.edu.nju.fantasybox.*"})
public class FantasyBoxApplication {

    public static void main(String[] args) {
        SpringApplication.run(FantasyBoxApplication.class, args);
    }

}
