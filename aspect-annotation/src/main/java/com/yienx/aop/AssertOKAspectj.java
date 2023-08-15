package com.yienx.aop;
import com.sun.istack.internal.logging.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class AssertOKAspectj {

    private Logger logger = Logger.getLogger(this.getClass());

    @Pointcut("@annotation(com.yienx.aop.AssertOK)")  //表示所有带有AssertOK的注解
    public void point(){

    }

//    @Pointcut("execution(* com.mb..*.*(..))")  //表示拦截所有com.mb包及子包下的所有的方法
//    public void pjp(){
//
//    }

    // @Around(value = "point()")
    public Object assertAround(ProceedingJoinPoint pjp){
        // 目标方法的返回值
        Object result = null;


        String className = pjp.getTarget().getClass().getName();
        // 先获取目标方法的签名，再获取目标方法的名
        String methodName = pjp.getSignature().getName();

        // 获取目标方法参数类型
        Class[] parameterTypes = ((MethodSignature) pjp.getSignature()).getParameterTypes();
        Object[] args = pjp.getArgs();  // 获取目标方法的入参
        for (int i = 0; i < args.length; i++) {
            logger.info("argsName: " + args[i]); //输出目标方法的参数
        }

        long starttime = System.currentTimeMillis();
        try {
            // 获取目标类的clazz
            Class<?> aClass = pjp.getTarget().getClass();
            // 获取目标方法
            Method method = aClass.getMethod(methodName, parameterTypes);
            // 获取方法上的注解
            AssertOK annotation = method.getAnnotation(AssertOK.class);
            annotation.isLogin();  //获取注解函数值

            result = pjp.proceed();  //执行目标方法


        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            long exctime = System.currentTimeMillis() - starttime;
            logger.info("执行时间：" + exctime + "毫秒");
        }

        return result;
    }

    /**
     * 方法执行时间
     * @param pjp
     * @return
     */
    @Around(value = "point()")
    public Object doTime(ProceedingJoinPoint pjp){
        // 目标方法的返回值
        Object result = null;

        String className = pjp.getTarget().getClass().getName();
        String methodName = pjp.getSignature().getName();


        long startTime = System.currentTimeMillis();
        try {
            result = pjp.proceed();  //执行目标方法
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            long excTime = System.currentTimeMillis() - startTime;
            logger.info("执行" + className + "." + methodName + "耗时：" + excTime + "ms");
        }

        return result;
    }

}
