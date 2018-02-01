package com.rz.sb;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.rz.sb.util.LoginFilter;

@Configuration
public class CorsConfig {
    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        return corsConfiguration;
    }
 
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }
    
    @Bean  
    public FilterRegistrationBean filterDemo4Registration() {  
        FilterRegistrationBean registration = new FilterRegistrationBean();  
        registration.setFilter(loginFilter());  
        registration.addUrlPatterns("/*");
        registration.setName("LoginFilter");  
        return registration;  
    }  
    
    @Bean  
    public Filter loginFilter() {  
        return new LoginFilter();  
    } 
}
