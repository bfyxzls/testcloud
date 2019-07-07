package com.gateway.gateway.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;


import static com.netflix.zuul.context.RequestContext.getCurrentContext;

/**
 * 路由过滤器.
 */
@Component
public class UrlValidateFilter extends ZuulFilter {
  @Override
  public String filterType() {
    return "pre";
  }

  @Override
  public int filterOrder() {
    return 0;
  }

  @Override
  public boolean shouldFilter() {
    return true;
  }

  @Override
  public Object run() throws ZuulException {
    RequestContext context = RequestContext.getCurrentContext();
    HttpServletRequest request = context.getRequest();
    if (request.getParameter("token") == null) {
      context.setResponseStatusCode(401);
      context.setSendZuulResponse(false);
    }
    return null;
  }
}
