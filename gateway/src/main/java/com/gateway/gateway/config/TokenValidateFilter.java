package com.gateway.gateway.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * token校验和对象向下流传递.
 */
@Component
public class TokenValidateFilter extends ZuulFilter {
  @Autowired
  ObjectMapper objectMapper;

  @Override
  public String filterType() {
    return "pre";
  }

  @Override
  public int filterOrder() {
    return -1;
  }

  @Override
  public boolean shouldFilter() {
    return true;
  }

  @Override
  public Object run() throws ZuulException {
    RequestContext context = RequestContext.getCurrentContext();
    HttpServletRequest request = context.getRequest();
    try {
      if (request.getParameter("token") == null) {
        context.setResponseStatusCode(401);
        context.setSendZuulResponse(false);
        context.addZuulResponseHeader("Content-Type", "application/json; charset=utf-8");
        Map<String, String> map = new HashMap<String, String>() {{
          put("message", "没有token");
        }};
        context.setResponseBody(objectMapper.writeValueAsString(map));
      } else {
        if (request.getParameter("token").equals("lind")) {//将token取出的用户数据向下流传递
          //读redis将token对应的user对象向下流传递
          TokenData tokenData = new TokenData("zzl", "zzl@sina.com");
          context.addZuulRequestHeader("TOKEN_DATA", objectMapper.writeValueAsString(tokenData));
        } else {
          context.setResponseStatusCode(403);
          context.setSendZuulResponse(false);
          context.addZuulResponseHeader("Content-Type", "application/json; charset=utf-8");
          Map<String, String> map = new HashMap<String, String>() {{
            put("message", "token无效");
          }};
          context.setResponseBody(objectMapper.writeValueAsString(map));
        }
      }

    } catch (Exception ex) {
      ex.getStackTrace();
    }
    return null;
  }
}