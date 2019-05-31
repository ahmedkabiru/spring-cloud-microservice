package com.hamsoft.monitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@EnableDiscoveryClient
@SpringBootApplication
@EnableHystrixDashboard
@EnableTurbine
//@Controller
public class MonitoringApplication {


//    @RequestMapping("/")
//    public String home() {
//        return "forward:/hystrix";
//    }

    public static void main(String[] args) {
        SpringApplication.run(MonitoringApplication.class, args);
    }


}
