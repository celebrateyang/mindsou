package com.mindsou.interceptors;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 竹庆 on 2016/9/18.
 */
public class BaseDataBeforeViewHandler implements BeforeViewHandler {
    @Override
    public void beforeView(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) throws Exception {
        final String fullPath = request.getServerName() + ":"
                + request.getServerPort() + request.getContextPath() + "/";
        final String basePath = request.getContextPath();
        modelAndView.addObject("fullPath",fullPath);
        modelAndView.addObject("basePath",basePath);
        //为js文件添加参数，防止缓存
        String mindVersion = request.getServletContext().getInitParameter("mindVersion");
        if(StringUtils.isEmpty(mindVersion)) {
            modelAndView.addObject("mathRandom", Math.random());
        }
        else {
            modelAndView.addObject("mathRandom", mindVersion);
        }
    }
}
