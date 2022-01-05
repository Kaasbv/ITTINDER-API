package com.ittinder.rest.Config;

import com.ittinder.rest.Interceptor.SessionInterceptor;
import com.ittinder.rest.Service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Component
public class InterceptorConfig implements WebMvcConfigurer {
  @Autowired
  private final SessionService sessionService;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new SessionInterceptor(sessionService));
  }

}