package com.ittinder.rest.Interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ittinder.rest.Entities.User;
import com.ittinder.rest.Service.SessionService;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.WebUtils;

public class SessionInterceptor implements HandlerInterceptor {
  private final SessionService sessionService;

  public SessionInterceptor(SessionService sessionService) {
    this.sessionService = sessionService;
  }

  @Override
  public boolean preHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler) {
    Cookie cookie = WebUtils.getCookie(request, "session_id");

    if (cookie != null) {
      String sessionId = cookie.getValue();
      //Try to get the User from the session service
      User user = sessionService.getUserBySessionId(sessionId);

      if (user != null) {
        request.setAttribute("logged_in_user_id", user.getId());
      }
    }
    //if returned false, we need to make sure 'response' is sent
    return true;
  }

}