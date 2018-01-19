package com.rz.sb.util.ds;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

public class DynamicDataSourceRegister implements ImportBeanDefinitionRegistrar, EnvironmentAware {

	private DataSource defaultDataSource;
	private static Map<String, DataSource> customDataSources = new LinkedHashMap<>();
	
	public void setEnvironment(Environment env) {
		String prefix = "spring.datasource";
		RelaxedPropertyResolver rpr = new RelaxedPropertyResolver(env, prefix + ".");
		String names = rpr.getProperty("names");
		String[] arr = names.split(",");
		for (int i = 0; i < arr.length; i++) {
			String ds = arr[i];
			Map<String, Object> dsMap = rpr.getSubProperties(ds + ".");
			DataSource d = buildDataSource(dsMap);
			customDataSources.put(ds, d);
			if(i == 0){
				defaultDataSource = d;
			}
		}
	}

	public DataSource buildDataSource(Map<String, Object> dsMap) {
        try {
            String driverClassName = dsMap.get("driver-class-name").toString();
            String url = dsMap.get("url").toString();
            String username = dsMap.get("username").toString();
            String password = dsMap.get("password").toString();
            DataSourceBuilder factory = DataSourceBuilder.create().driverClassName(driverClassName).url(url)
                    .username(username).password(password);
            return factory.build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

	@Override
	public void registerBeanDefinitions(AnnotationMetadata arg0,
			BeanDefinitionRegistry registry) {
		Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        targetDataSources.put("dataSource", defaultDataSource);
        DynamicDataSourceHolder.dataSourceIds.add("dataSource");
        targetDataSources.putAll(customDataSources);
        for (String key : customDataSources.keySet()) {
        	DynamicDataSourceHolder.dataSourceIds.add(key);
        }
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(DynamicDataSource.class);
        beanDefinition.setSynthetic(true);
        MutablePropertyValues mpv = beanDefinition.getPropertyValues();
        mpv.addPropertyValue("defaultTargetDataSource", defaultDataSource);
        mpv.addPropertyValue("targetDataSources", targetDataSources);
        registry.registerBeanDefinition("dataSource", beanDefinition);
	}
}
