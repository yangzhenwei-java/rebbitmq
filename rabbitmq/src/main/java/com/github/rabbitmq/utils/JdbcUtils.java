package com.github.rabbitmq.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Jdbc工具类
 * @author Administrator
 *
 */
/*
 * 从配置文件获取四大参数
 * 通过四大参数生成Connection
 */
public class JdbcUtils {
	// 把dbcofnig.proerties文件中的参数加载到prop这个对象中去！
	// 必须是getConnection()方法被调用之前完成！
	private static Properties prop = new Properties();
	static {
		// 把配置文件放到src下，eclipse会把这个文件copy一份到bin下
		// ClassLoader加载的是bin下的！
		InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream("dbconfig.properties");
		try {
			prop.load(in);
		} catch (IOException e) {
			throw new RuntimeException("dbconfig.properties文件不存在！", e);
		}
		try {
			Class.forName(prop.getProperty("driverClassName"));
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("driverClassName有问题，可能是没导包，要不你写错了！", e);
		}
	}
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"));
	}
}
