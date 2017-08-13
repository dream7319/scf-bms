package com.lierl.api.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by lierl on 2017/8/12.
 */
//@ControllerAdvice
public class GlobalExceptionHandler {
    public static final String DEFAULT_ERROR_VIEW = "/error/500.html";

    @ExceptionHandler(value = Exception.class)
    public void defaultErrorHandler(HttpServletResponse response) throws Exception {
        response.setStatus(500);
        response.sendRedirect(DEFAULT_ERROR_VIEW);
        response.getWriter().write("服务器异常");
    }
}
