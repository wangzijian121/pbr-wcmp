package com.zlht.pbr.algorithm.wcmp;

import com.zlht.pbr.algorithm.wcmp.tools.service.EnvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author zi jian Wang
 */
@SpringBootApplication
@EnableOpenApi
@EnableFeignClients
public class AlgorithmwcmpApi implements ApplicationRunner {

    @Autowired
    private EnvService envService;


    public static void main(String[] args) {
        SpringApplication.run(AlgorithmwcmpApi.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        envService.printSwaggerAddress();
    }
}