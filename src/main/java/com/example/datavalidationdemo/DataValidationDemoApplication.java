package com.example.datavalidationdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@SpringBootApplication
@EnableKafkaStreams
public class DataValidationDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataValidationDemoApplication.class, args);
    }

}
