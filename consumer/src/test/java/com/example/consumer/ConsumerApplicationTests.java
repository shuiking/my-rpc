package com.example.consumer;

import com.example.consumer.service.ExampleServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ConsumerApplicationTests {
    @Resource
    private ExampleServiceImpl exampleService;
    @Test
    void contextLoads() {
        exampleService.test();
    }

}
