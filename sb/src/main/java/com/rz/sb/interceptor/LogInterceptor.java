package com.rz.sb.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.MethodParameter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.rz.sb.Log;

public class LogInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        Log log = method.getAnnotation(Log.class);
        if (log == null) {
            return true;
        }
        String op = log.value();
        String m = method.getName();
        String bean = handlerMethod.getBean().getClass().toString();
        MethodParameter[] mps = handlerMethod.getMethodParameters();
        String ip = getIpAddr(request);
        System.out.println(op + "===" + bean + "===" + m + "===" + ip);
        for(MethodParameter mp : mps){
            System.out.println(mp.getParameterName() + " --  "+ mp.getParameterType());
        }
		return true;
	}
	
	private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
