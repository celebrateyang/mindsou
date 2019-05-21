package com.mindsou.interceptors;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by 竹庆 on 2016/9/18.
 */
public class BeforeViewHandlerInterceptor extends HandlerInterceptorAdapter {
    private List<BeforeViewHandler> beforeViewHandlers;

    public List<BeforeViewHandler> getBeforeViewHandlers() {
        return beforeViewHandlers;
    }

    public void setBeforeViewHandlers(List<BeforeViewHandler> beforeViewHandlers) {
        this.beforeViewHandlers = beforeViewHandlers;
    }

    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        if (modelAndView != null && !isIncludeRequest(request) && isSupportedView(modelAndView))
        {
            for (final BeforeViewHandler beforeViewHandler : getBeforeViewHandlers())
            {
                beforeViewHandler.beforeView(request, response, modelAndView);
            }
        }
    }

    protected boolean isIncludeRequest(final HttpServletRequest request)
    {
//        if(request.getAttribute("javax.servlet.include.request_uri") != null) System.out.println("===>"+request.getAttribute("javax.servlet.include.request_uri"));
        return request.getAttribute("javax.servlet.include.request_uri") != null;
    }

    protected boolean isSupportedView(final ModelAndView modelAndView)
    {
        return modelAndView.getViewName() != null && !isRedirectView(modelAndView);
    }

    protected boolean isRedirectView(final ModelAndView modelAndView)
    {
        final String viewName = modelAndView.getViewName();
        return viewName != null && viewName.startsWith("redirect:");
    }
}
