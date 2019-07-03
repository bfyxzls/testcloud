package com.example.product.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 发送FeignClient设置Header信息.
 * http://www.itmuch.com/spring-cloud-sum/hystrix-threadlocal/
 * Hystrix传播ThreadLocal对象
 */
@Component
public class TokenFeignClientInterceptor implements RequestInterceptor {

  static final String KEY_OAUTH2_TOKEN = "Authorization";

  /**
   * token放在请求头.
   *
   * @param requestTemplate 请求参数
   */
  @Override
  public void apply(RequestTemplate requestTemplate) {
    RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
    if (requestAttributes != null) {
      HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
      String token = request.getHeader(KEY_OAUTH2_TOKEN);
      requestTemplate.header(KEY_OAUTH2_TOKEN,
          new String[] {token});
    }
  }
}
