package com.example.demo;

import com.example.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class DemoApplication {

    @Autowired
    DemoService service = new DemoService();

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
		System.out.println("hello");

    }
    @PostConstruct
    public void start()
    {
        service.insertUsingTransactionMethod();
    }

}
