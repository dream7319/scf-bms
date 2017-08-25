package com.lierl.api.interceptor;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.lierl.api.common.Constants;
import com.lierl.api.entity.User;
import com.lierl.api.service.IResourceService;
import com.lierl.api.util.Utils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.util.List;

/**
 * Created by lierl on 2017/7/16.
 */
public class AuthInterceptor implements HandlerInterceptor {

    private final static Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

    @Autowired
    private IResourceService resourceService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）

        String uri = request.getRequestURI();
        int status = response.getStatus();

        System.out.println(uri+"<=====>"+status);

        List<String> uris = Lists.newArrayList();
        uris.add("/api/login");
        uris.add("/error");

        if(uris.contains(uri)){
            return true;
        }

        if(status == 404) Utils.write(response,404);

        if(status == 500) Utils.write(response,500);

        Cookie[] cookies=request.getCookies();
        String value=null;
        if(cookies!=null){
            for(Cookie cookie:cookies){
                if("token".equals(cookie.getName())){
                    value= URLDecoder.decode(cookie.getValue(),"UTF-8");
                }
            }
        }

        if(StringUtils.isEmpty(value)) Utils.write(response,201);
        User user = JSON.parseObject(value, User.class);
        if(user == null) Utils.write(response,201);

        User u = (User)request.getSession().getAttribute(Constants.CURRENT_USER);
        if (u == null) Utils.write(response,201);

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
