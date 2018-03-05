package com.util.config;

import com.common.Constants;
import com.util.exception.LoginException;
import com.vo.LoginUser;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * session 参数获取
 *
 * @author 潘根山
 * @create 2018-02-06 13:56
 * @since 1.0.0
 */
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Class clazz = parameter.getParameterType();
        return clazz == LoginUser.class;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        HttpSession session = request.getSession();
        LoginUser loginUser = (LoginUser) session.getAttribute(Constants.SESSION_NAME);
        if (loginUser == null) {
            throw new LoginException();
        }
        return loginUser;
    }
}