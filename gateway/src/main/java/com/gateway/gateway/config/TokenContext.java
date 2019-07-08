package com.gateway.gateway.config;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * 请求上下文.
 *
 * @author xia
 * @since 2018-10-07
 */
public class TokenContext {
  public static final String KEY_TOKEN_IN_HTTP_HEADER = "UserDetails";

  private static ThreadLocal<TokenData> tokenLocal = new ThreadLocal<>();

  public static TokenData getToken() {
    return tokenLocal.get();
  }

  public static void setToken(TokenData token) {
    tokenLocal.set(token);
  }

  public static void removeToken() {
    tokenLocal.set(null);
    tokenLocal.remove();
  }
}
