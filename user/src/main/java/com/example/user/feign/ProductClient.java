package com.example.user.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "product",
    fallback = ProductClientFallback.class)
public interface ProductClient {
  @GetMapping("/")
  String index();
}
