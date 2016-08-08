package com.tangmj.demo.datasource;

/**
 * 使用@ThreadLocal 保存dataSource的key值
 * @author Tangmj
 *
 */
public class DataSourceHandler {

	private static ThreadLocal<String> dataSourceKey = new ThreadLocal<String>();
	
	public static void setDataSuourceKey(String dataSource){
		dataSourceKey.set(dataSource);
	}
	
	public static String getDataSourceKey(){
		return dataSourceKey.get();
	}
}
