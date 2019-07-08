package com.gateway.gateway.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TokenDataFilter implements Filter {

  @Autowired
  ObjectMapper objectMapper;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    //从网关拿到TOKEN_DATA里的对象，并存储到自己的TokenContext里
    String tokenJson = request.getHeader("TOKEN_DATA");
    if (StringUtils.isNotBlank(tokenJson)) {
      TokenContext.setToken(objectMapper.readValue(tokenJson, TokenData.class));
    }
    //渲染页面
    filterChain.doFilter(servletRequest, servletResponse);
    //页面执行完成后，执行这个方法，清除当前tokencontext,实事上threadLocal应该是会自动清空的
    TokenContext.removeToken();
  }

  @Override
  public void destroy() {

  }
}
