package com.keji50.k5.service.system.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 系统业务性能表现切面
 *
 * @author chao.li
 * @version
 * @since Ver 1.1
 * @Date 2015年12月10日 上午11:00:23
 *
 * @see
 */
public class BusinessAspect {

    private static final Logger logger = LoggerFactory.getLogger(BusinessAspect.class);

    public Object doAround(ProceedingJoinPoint joinPoint) {
        long start = System.currentTimeMillis();
        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            return null;
        } finally {
            logger.info("method[{}] costs {}", joinPoint.getSignature().getName(), System.currentTimeMillis() - start);
        }
    }
}
