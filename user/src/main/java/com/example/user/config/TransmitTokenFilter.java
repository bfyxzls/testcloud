package com.example.user.config;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * 过滤器.
 *
 * @author xia
 * @since 2018-10-07
 */
@Component
public class TransmitTokenFilter implements Filter {


  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response,
                       FilterChain chain) throws IOException, ServletException {
    HttpServletRequest servletRequest = (HttpServletRequest) request;
    this.initToken(servletRequest);
    chain.doFilter(request, response);
    TokenContext.removeToken();
  }

  /**
   * 初始化token.
   *
   * @param request 请求参数
   */
  private void initToken(HttpServletRequest request) {
    if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      TokenContext.setToken((UserDetails) authentication.getPrincipal());
    }
  }

  @Override
  public void destroy() {
  }
}
