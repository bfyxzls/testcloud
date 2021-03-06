package com.example.user;

import com.example.user.config.TokenContext;
import com.example.user.feign.ProductClient;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@RestController
public class UserApplication {

  @Autowired
  ProductClient productClient;

  public static void main(String[] args) {
    SpringApplication.run(UserApplication.class, args);
  }

  @GetMapping("/")
  public String index() {
    UserDetails userDetails = TokenContext.getToken();
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append("hello user");
    stringBuffer.append("\n" + productClient.index());
    return stringBuffer.toString();
  }

  @GetMapping("/user")
  public Principal user(Principal user) {
    return user;
  }

  @GetMapping("/test")
  public String test(@RequestParam String name) {

    String body = productClient.add(name);
    return body;
  }
}
