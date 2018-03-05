package com.util.interceptor;

import com.common.Constants;
import com.vo.LoginUser;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MyInterceptor extends HandlerInterceptorAdapter {
    @Override
    public void afterCompletion(HttpServletRequest arg0,
                                HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        // TODO Auto-generated method stub
    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
                           Object arg2, ModelAndView arg3) throws Exception {
        // TODO Auto-generated method stub  
    }

    @Override
    public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
                             Object arg2) throws Exception {
        boolean flag = false;
        String url = arg0.getRequestURL().toString();
        if (url.contains("/login")) {
            flag = true;
        } else {
            HttpSession session = arg0.getSession(true);
            LoginUser user = (LoginUser) session.getAttribute(Constants.SESSION_NAME);
            if (user == null || "".equals(user.toString())) {
                if (arg0.getHeader("x-requested-with") != null && arg0.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
                    flag = true;
                } else {
                    arg1.sendRedirect("/login");
                }
            } else {
                flag = true;
            }
        }
        return flag;
    }
}
