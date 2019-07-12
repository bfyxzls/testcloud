package com.gateway.gateway;

import com.gateway.gateway.config.TokenContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
@RestController
public class GatewayApplication {

  private final static Logger logger = LoggerFactory.getLogger(GatewayApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(GatewayApplication.class, args);
  }

  @GetMapping("/index")
  public String index() {
    logger.info("hello world!");
    return "hello gateway" + TokenContext.getToken();
  }


  @GetMapping("/test")
  public String way() {
    return "hello way";
  }
}
