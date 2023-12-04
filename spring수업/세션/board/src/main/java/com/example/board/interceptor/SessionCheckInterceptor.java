package com.example.board.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class SessionCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String reqURI = request.getRequestURI();
        System.out.println(reqURI);

//        request 서버로 부터 전달
//        response 서버로 전달
        HttpSession hs = request.getSession();
//        LoginInfo(userid, passwd, name...) - 객체

        if(hs == null || hs.getAttribute("LoginInfo") == null) {
            System.out.println("You are not Incorrectly");
            response.sendRedirect("/login?redirectURI="+reqURI);
            return false;
        }

        return true;
    }
}
