package com.example.user.config;


import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

//@Configuration
//public class FeignConfig implements RequestInterceptor {
//
//  @Override
//  public void apply(RequestTemplate requestTemplate) {
//    OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails)
//
//        SecurityContextHolder.getContext().getAuthentication()
//            .getDetails();
//
//    requestTemplate.header("Authorization",
//        "bearer " + details.getTokenValue());
//  }
//}

@Configuration
public class FeignConfig implements RequestInterceptor {
  @Override
  public void apply(RequestTemplate requestTemplate) {
    //添加token
    OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
    String token = details.getTokenType();
    requestTemplate.header("Authorization", token);
  }
}

