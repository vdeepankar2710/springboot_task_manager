package com.spring.task_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskManagerApplication {

    public static void main(String[] args) {

        SpringApplication.run(TaskManagerApplication.class, args);

        System.out.println("This app is running");

    }

}
