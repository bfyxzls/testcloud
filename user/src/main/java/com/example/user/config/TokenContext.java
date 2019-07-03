package com.example.user.config;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * 请求上下文.
 *
 * @author xia
 * @since 2018-10-07
 */
public class TokenContext {
  public static final String KEY_TOKEN_IN_HTTP_HEADER = "UserDetails";

  private static ThreadLocal<UserDetails> tokenLocal = new ThreadLocal<>();

  public static UserDetails getToken() {
    return tokenLocal.get();
  }

  public static void setToken(UserDetails token) {
    tokenLocal.set(token);
  }

  public static void removeToken() {
    tokenLocal.set(null);
    tokenLocal.remove();
  }
}
