package com.github.rabbitmq.rabbitMq3;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import com.rabbitmq.client.QueueingConsumer;

public class ReceiveLogs {

	private static final String EXCHANGE_NAME = "logs";

	public static void main(String[] argv) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		// 声明路由类型
		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
		// 获取一个随机的 非持久的  失去连接自动删除的队列
		String queueName = channel.queueDeclare().getQueue();
		String queueNameTwo = channel.queueDeclare().getQueue();
		// 路由绑定到一个某个队列上
		channel.queueBind(queueName, EXCHANGE_NAME, "");
		channel.queueBind(queueNameTwo, EXCHANGE_NAME, "");
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(queueName, true, consumer);
		channel.basicConsume(queueNameTwo, true, consumer);
		while (true) {
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String message = new String(delivery.getBody());
			System.out.println(" [x] Received '" + message + "'");

		}
	}

}
