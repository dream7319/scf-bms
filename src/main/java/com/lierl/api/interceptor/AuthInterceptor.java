package com.lierl.api.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;

/**
 * Created by lierl on 2017/7/16.
 */
public class AuthInterceptor implements HandlerInterceptor {

    private final static Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）

        String uri = request.getRequestURI();
        if(uri.endsWith("/error") || uri.endsWith("/api/login")){
            return true;
        }

        String authorization=request.getHeader("Authorization");
        Cookie[] cookies=request.getCookies();
        String value=null;
        if(cookies!=null){
            for(Cookie cookie:cookies){
                if("token".equals(cookie.getName())){
                    value= URLDecoder.decode(cookie.getValue(),"UTF-8");
//                    logger.info(value);
                }
            }
        }




        return true;// 只有返回true才会继续向下执行，返回false取消当前请求
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView mv) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        //在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
    }
}
