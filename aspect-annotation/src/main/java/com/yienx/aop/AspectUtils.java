package com.yienx.aop;

import com.yienx.utils.JsonUtil;
import org.apache.commons.lang.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Aspect注解表示将该类作为一个切面
 */
@Component
@Aspect
public class AspectUtils {

    // private Logger logger = Logger.getLogger(this.getClass()); // 也可以
    private Logger logger = LoggerFactory.getLogger(AspectUtils.class);

    // @Pointcut("@annotation(com.yienx.aop.AssertOK)")  //表示所有带有AssertOK的注解
    // @Pointcut表示切入点
    @Pointcut("execution(* com.yienx..*.*(..))") //表示拦截所有com.yienx包及子包下的所有的方法
    public void point(){}

//    @Pointcut("execution(* com.yienx..*.*(..))")  //表示拦截所有com.yienx包及子包下的所有的方法
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

        long startTime = System.currentTimeMillis();
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
            long exctime = System.currentTimeMillis() - startTime;
            logger.info("执行时间：" + exctime + "毫秒");
        }

        return result;
    }

    /**
     * 拦截所有接口要执行的方法，记录执行方法的出入参
     * @param pjp
     * @return
     * 需要加注解使用
     */
    @Around(value = "point()")
    public Object doLog(ProceedingJoinPoint pjp) {
        // String className = pjp.getTarget().getClass().getSimpleName();
        String className = pjp.getTarget().getClass().getName();
        String methodName = pjp.getSignature().getName();// 方法名称
        Object[] method_args = pjp.getArgs(); // 获取方法的参数值数组
        String[] paramNames = new String[method_args.length];
        for (int i = 0; i < paramNames.length; i++) {
            paramNames[i] = "arg" + (i + 1);
        }
        Object result = null;// 方法执行结果
        try {
            result = pjp.proceed();// 执行目标对象的业务方法
        } catch (Throwable e) {
            logger.error("AspectUtils.doLog error", e);
        } finally {
            try {
                if (result == null) {
                    logger.info("className=" + className + ";methodName=" + methodName + ";params=" + getParam(paramNames, method_args) + ";result={}");
                } else {
                    logger.info("className=" + className + ";methodName=" + methodName + ";params=" + getParam(paramNames, method_args) + ";result="
                            + JsonUtil.write2JsonStr(result));
                }
            }catch (Exception ex)
            {}
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
        String className = pjp.getTarget().getClass().getName();
        String methodName = pjp.getSignature().getName();

        long startTime = System.currentTimeMillis();
        // 目标方法的返回值
        Object result = null;
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

    /**
     * 打印方法参数值 基本类型直接打印，非基本类型需要重写toString方法
     *
     * @param paramsArgsName
     *            方法参数名数组
     * @param paramsArgsValue
     *            方法参数值数组
     */
    private String getParam(String[] paramsArgsName, Object[] paramsArgsValue) {
        if (ArrayUtils.isEmpty(paramsArgsName) || ArrayUtils.isEmpty(paramsArgsValue)) {
            return "{}";
        }
        Map<String, Object> paramsMap= new LinkedHashMap<String, Object>();
//        JSONObject params = new JSONObject();
        for (int i = 0; i < paramsArgsName.length; i++) {
            // 参数名
            String name = paramsArgsName[i];
            // 参数值
            Object value = paramsArgsValue[i];
            if(value instanceof HttpServletRequest) {
                continue;
            }
            paramsMap.put(name, value);
            // if(isPrimite(value.getClass())){
            // params.put(name, value);
            // }else {
            // if (value instanceof Set) {
            //
            // } else if (value instanceof List) {
            //
            // } else if (value.getClass().isArray() ) { //判断是否为数组
            //
            // }
            // JsonSerializableTool.toJson(value);
            // }
        }
        return JsonUtil.write2JsonStr(paramsMap);
    }


}
