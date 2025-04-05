package com.example.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object loginUser = session.getAttribute("loginUser");

        if (loginUser == null) {
            // 로그인 안 되어 있음 → 로그인 페이지로 리다이렉트
            response.sendRedirect(request.getContextPath() + "/login");
            return false; // 컨트롤러로 진행하지 않음
        }

        return true; // 로그인 상태 → 컨트롤러로 진행
    }
}
