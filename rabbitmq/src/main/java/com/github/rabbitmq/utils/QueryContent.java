package com.github.rabbitmq.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class QueryContent {

	private static Connection connection;

	// private static final int SIZE = 1024 * 1024 * 100;
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws Exception {

		File file = new File("E:\\shoprepository\\ebill_payment_virtue.sql");

		QueryContentFromFile(file);

	}

	private static void QueryContentFromFile(File file) throws Exception {

		// 用读取流关联源文件。
		FileInputStream fis = new FileInputStream(file);

		BufferedReader buf = new BufferedReader(new InputStreamReader(fis));
		// 定义一个1M的缓冲区。
		// byte[] buf = new byte[SIZE];

		// 创建目的。

		// FileOutputStream fos = null;

//		connection = JdbcUtils.getConnection();
        String url = "jdbc:mysql://localhost:3306/ebill_payment_virtue?user=root&password=root";   
        try {   
            Class.forName("com.mysql.jdbc.Driver");   
        } catch (ClassNotFoundException e1) {   
            e1.printStackTrace();   
        }  
        connection = DriverManager.getConnection(url);
//		connection.setAutoCommit(false);
		Statement createStatement = connection.createStatement();
//		int count = 0;
		String line = null;
		StringBuffer strbuf = new StringBuffer();
		boolean flag = true;
		while ((line = buf.readLine()) != null) {
			if("".equals(line)){
				continue ;
			}
			System.out.println(line);
			if(line.endsWith("*/;")||line.endsWith("*/")){
				flag  = true;
				continue;
			}else if(flag&&line.startsWith("/*")&&!line.endsWith("*/;")&&!line.endsWith("*/")){
				System.out.println(line+"开");
				flag = false;
				continue;
			}			
			if(!flag){
				continue;
			}
			if(!line.endsWith(";")){
				System.err.println(line);
				strbuf.append(line);
				continue;
			}else {
				strbuf.append(line);
			}

//			count++;
//			createStatement.addBatch(line);
//			if (count%10==0) {
				try {
					System.err.println(strbuf.toString()+"sssssssssssssssssssssssssssss");
					int i = createStatement.executeUpdate(strbuf.toString());
					strbuf = null;
					strbuf = new StringBuffer();
//					connection.commit();
					
					System.out.println(i);
//					createStatement.clearBatch();
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
//			}
				
		}
		createStatement.close();
		connection.close();
		buf.close();
		fis.close();

	}

}
