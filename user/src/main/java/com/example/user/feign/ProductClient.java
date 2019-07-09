package com.example.user.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
    value = "product",
    // url = "${product.url:}", //当url不为空时，会直接走url，而不走value，因为认为不是在一个eureka里，
    // 当然url不写或者为空，还是会走value的，我的配置是使用配置文件的，如果不配置，就用空的
    fallback = ProductClientFallback.class)
public interface ProductClient {
  @GetMapping("/")
  String index();

  @GetMapping("/add")
  String add(@RequestParam("param") String param);
}