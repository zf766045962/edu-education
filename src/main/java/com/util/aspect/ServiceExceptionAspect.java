package com.util.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * service 层异常统一处理
 *
 * @author pgs
 * @version 1.0
 */
@Aspect
public class ServiceExceptionAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceExceptionAspect.class);

    @Pointcut("@within(org.springframework.stereotype.Service) && execution(public * *(..))")
    private void servicePointcut() {

    }

    @AfterThrowing(pointcut = "servicePointcut()", throwing = "e")
    public void handle(JoinPoint point, Exception e) {
        e.printStackTrace();
        LOGGER.info("异常信息:{},发生原因:{}", new Object[]{e.getMessage(), e.getStackTrace()});
    }
}