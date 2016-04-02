package com.github.rabbitmq.rabbitMq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;


public class NewTask {
//	private final static String QUEUE_NAME = "hello";
//	private final static String QUEUE_NAME = "yangzhenwei";
	private final static String QUEUE_NAME = "duanhuifen";
	public static void mainTask(String[] argv) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		// 设置rabbitMQ是持久化的  即使MQ重新启动也没事
		boolean durable = true;
		channel.queueDeclare(QUEUE_NAME, durable, false, false, null);
		String message = getMessage(argv);
		//消息持久化   MessageProperties.PERSISTENT_TEXT_PLAIN
		channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
		System.out.println(" [x] Sent '" + message + "'");
		channel.close();
		connection.close();
	}
	private static String getMessage(String[] strings){
	    if (strings.length < 1)
	        return "Hello World!";
	    return joinStrings(strings, " ");
	}

	private static String joinStrings(String[] strings, String delimiter) {
	    int length = strings.length;
	    if (length == 0) return "";
	    StringBuilder words = new StringBuilder(strings[0]);
	    for (int i = 1; i < length; i++) {
	        words.append(delimiter).append(strings[i]);
	    }
	    return words.toString();
	}
	public static void main(String[] args) {
		try {
			mainTask(args);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}