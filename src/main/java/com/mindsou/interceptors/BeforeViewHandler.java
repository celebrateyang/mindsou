package com.mindsou.interceptors;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 竹庆 on 2016/9/18.
 */
public interface BeforeViewHandler {
    /**
     * 在DispatchServlet渲染页面之前调用，为页面准备一些基础数据。
     * @param request
     * @param response
     * @param modelAndView
     * @throws Exception
     */
    void beforeView(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) throws Exception;
}
