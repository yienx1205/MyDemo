package com.yienx.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author wangyanbo29
 * @Date 2023/8/14
 * @Description
 */
@Aspect
public class AspectUtil {

    private static final Logger logger = LoggerFactory.getLogger(AspectUtil.class);

    // @Pointcut("@annotation(com.mb.aop.AssertOK)")  //表示所有带有AssertOK(自定义注解)的注解
    // public void point(){
    //
    // }

    /**
     * 拦截所有接口要执行的方法,记录执行方法的时间
     *
     * @param point
     * @return
     * @throws Throwable
     */
    public Object doTime(ProceedingJoinPoint point) {
        String className = point.getTarget().getClass().getName();
        String methodName = point.getSignature().getName();
        long start = System.currentTimeMillis();
        Object result = null;// 方法执行结果
        try {
            result = point.proceed();// 执行目标对象的业务方法
        } catch (Throwable e) {
            logger.error("AspectUtils.doTime error", e);
            System.out.println("AspectUtils.doTime error" + e);
        } finally {
            logger.error("执行" + className + "." + methodName + "耗时：" + (System.currentTimeMillis()-start) + "ms");
            System.out.println("执行" + className + "." + methodName + "耗时：" + (System.currentTimeMillis()-start) + "ms");
        }
        return result;
    }

}
