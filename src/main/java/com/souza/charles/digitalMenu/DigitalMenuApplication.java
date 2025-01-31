package com.souza.charles.digitalMenu;

import com.souza.charles.digitalMenu.environment.LoadEnvironment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DigitalMenuApplication {

    public static void main(String[] args) {
        LoadEnvironment.loadEnv();
        SpringApplication.run(DigitalMenuApplication.class, args);
    }

}
