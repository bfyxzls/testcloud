package com.example.user.config;

import static feign.FeignException.errorStatus;

import com.example.user.exception.Exceptions;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

/**
 * feign异常拦截器,当从feign抛出异常时走这个对象,注册当使用熔断后,会走fallback返回值.
 */
@Configuration
@Slf4j
public class FeignClientErrorDecoder implements ErrorDecoder {

  @Override
  public Exception decode(String methodKey, Response response) {
    logger.info("feign client response:", response);
    String body = null;
    try {
      body = Util.toString(response.body().asReader());
    } catch (IOException e) {
      logger.error("feign.IOException", e);
    }
    if (response.status() >= 400 && response.status() <= 500) {
      throw Exceptions.badRequestParams(body);
    }
    return errorStatus(methodKey, response);
  }
}