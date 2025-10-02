package com.souko.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.souko.dto.Result;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.PrintWriter;
import java.util.Enumeration;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    private void writeErrorResponse(HttpServletResponse response, String message) throws Exception {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        Result result = Result.fail(message); // 你已有的封装返回类
        PrintWriter writer = response.getWriter();
        writer.write(new ObjectMapper().writeValueAsString(result));
        writer.flush();
    }
    //拦截所有controller的方法
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 预检请求直接放行
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }
        //获取请求头
        String header = request.getHeader("Authorization" );
        if (header == null) {
            header = request.getHeader("authorization"); // 兼容处理
        }
        if (header == null || !header.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        //    Enumeration<String> headerNames = request.getHeaderNames();
//            while (headerNames.hasMoreElements()) {
//                String name = headerNames.nextElement();
//              //  System.out.println(name + " = " + request.getHeader(name));
//            }
            System.out.println("请求头有问题");
            writeErrorResponse(response, "缺少或无效的认证信息，请重新登录");
            System.out.println("Authorization header = " + request.getHeader("authorization"));
            return false;
        }
        String token = header.substring(7); // 去掉 "Bearer "
        //令牌是否过期
        if (!JwtUtil.isTokenValid(token)) {
            Result.fail("登录令牌失效，请重新登录");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            System.out.println("过期不对");
            writeErrorResponse(response, "登录令牌失效，请重新登录");
            return false;
        }
        try {
             Claims claims = JwtUtil.parseToken(token);
            request.setAttribute("user",claims.get("user"));
            return true;
        }catch (ExpiredJwtException e){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            System.out.println("解析不对");
            return false;
        }
       // return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    //拦截所有controller的方法（完成之后）
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
//    }
//
//    // 最后执行
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
//    }
}
