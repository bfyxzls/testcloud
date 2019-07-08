package com.example.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class ProductApplication {

  @Autowired
  ObjectMapper objectMapper;

  public static void main(String[] args) {
    SpringApplication.run(ProductApplication.class, args);
  }

  @GetMapping("/")
  public String index() throws Exception {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String user = objectMapper.writeValueAsString(authentication.getPrincipal());
    String s = "hello product " + user;
    return s;
  }

  @GetMapping("/add")
  public String add(@RequestParam("param") String param) {
    if (!param.equals("ok")) {
      throw new RuntimeException("error");
    }
    return "ok";
  }
}
