package com.qurich.external;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.qurich.external.conf.PropConfig;


@Controller
@EnableWebMvc
@SpringBootApplication
@EnableConfigurationProperties({PropConfig.class})
//@MapperScan(basePackages = "tk.mybatis.springboot.mapper", markerInterface = MyMapper.class)
//@MapperScan(basePackages = "com.forman.livingshow.mapper")
@EnableScheduling
public class Application extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
}
