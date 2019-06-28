package com.example.user.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("product")
public interface ProductClient {
  @GetMapping("/")
  String index();
}
