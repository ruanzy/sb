package com.rz.sb.util.ds;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(-1)
@Component
public class DataSourceAspect {
	
    @Before("@annotation(TargetDataSource)")
    public void before(JoinPoint joinPoint) {
        Object target = joinPoint.getTarget();
        String method = joinPoint.getSignature().getName();
        Class<?> clazz = target.getClass();
        Class<?>[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameterTypes();
        try {
            Method m = clazz.getMethod(method, parameterTypes);
            //如果方法上存在切换数据源的注解，则根据注解内容进行数据源切换
            if (m != null && m.isAnnotationPresent(TargetDataSource.class)) {
                TargetDataSource data = m.getAnnotation(TargetDataSource.class);
                String dataSourceName = data.value();
                DynamicDataSourceHolder.setDataSource(dataSourceName);
                String debug = String.format("Use DataSource : %s > %s", dataSourceName, joinPoint.getSignature());
                System.out.println(debug);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //执行完切面后，将线程共享中的数据源名称清空
    @Before("@annotation(TargetDataSource)")
    public void after(JoinPoint joinPoint){
        DynamicDataSourceHolder.removeDataSource();
    }
}
