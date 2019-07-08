package com.example.user.feign;

import org.springframework.stereotype.Component;

@Component
public class ProductClientFallback implements ProductClient {
  @Override
  public String index() {
    return "product.error";
  }

  @Override
  public String add(String param) {
    return null;
  }
}
