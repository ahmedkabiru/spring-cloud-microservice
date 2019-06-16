package com.hamsoft.testservice;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created By kabiruahmed on May 2019
 */

@RestController
@RequestMapping("/rest")
public class HelloResource {


    @HystrixCommand(fallbackMethod = "fallback", groupKey = "Hello",
            commandKey = "hello",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "4000")
    },
            threadPoolKey = "helloThread")
    @GetMapping
    public String hello() throws InterruptedException {
        Thread.sleep(3000);
        return "Hello World";
    }


    private String fallback() {
       return "Request fails. It takes long time to response";
    }



}
