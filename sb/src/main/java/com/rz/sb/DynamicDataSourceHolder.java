package com.rz.sb;

import java.util.ArrayList;
import java.util.List;

public class DynamicDataSourceHolder {

	private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();
	public static List<String> dataSourceIds = new ArrayList<>();

	public static void setDataSource(String name) {
		THREAD_LOCAL.set(name);
	}

	public static String getDataSource() {
		return THREAD_LOCAL.get();
	}

	public static void removeDataSource() {
		THREAD_LOCAL.remove();
	}
	
	public static boolean containsDataSource(String dataSourceId){
        return dataSourceIds.contains(dataSourceId);
   }
}
