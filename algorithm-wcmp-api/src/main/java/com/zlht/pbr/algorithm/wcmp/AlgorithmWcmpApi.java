package com.zlht.pbr.algorithm.wcmp;

import com.zlht.pbr.algorithm.wcmp.tools.service.EnvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author zi jian Wang
 */
@SpringBootApplication
@EnableOpenApi
@EnableFeignClients
@EnableScheduling
public class AlgorithmWcmpApi implements ApplicationRunner {

    @Autowired
    private EnvService envService;


    public static void main(String[] args) {
        SpringApplication.run(AlgorithmWcmpApi.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        envService.printSwaggerAddress();
    }
}