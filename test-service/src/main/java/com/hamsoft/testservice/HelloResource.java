package com.hamsoft.testservice;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
            commandKey = "hello",
            threadPoolKey = "helloThread")
    @GetMapping
    public String hello() {
        return "Hello World";
    }


}
