package main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//自动加载配置文件 因为配置了插件maven会自动扫描resources下面的yaml文件，如果用了properties，则扫描的是application.properties
@MapperScan("main.dao")//扫描包
public class Application {

    public static void main(String[] args) {
            SpringApplication.run(Application.class);
    }
}
