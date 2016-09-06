package cn.ds.interceptor;

import cn.ds.context.SystemContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PageInterceptor implements HandlerInterceptor {

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        //删除偏移量和条数
        SystemContext.deleteOffset();
        SystemContext.deletePageSize();
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //获取前台传入的分页数据
        String start = httpServletRequest.getParameter("start");
        String limit = httpServletRequest.getParameter("limit");
        if(!StringUtils.isEmpty(start)&&!StringUtils.isEmpty(limit)){
            SystemContext.setOffset(Integer.parseInt(start));
            SystemContext.setPageSize(Integer.parseInt(limit));
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }
}
