package com.github.rabbitmq.rabbitMq;




import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class Worker {
//	private final static String QUEUE_NAME = "hello";
//	private final static String QUEUE_NAME = "yangzhenwei";
	private final static String QUEUE_NAME = "duanhuifen";
	public static void mainWorker(String[] argv) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		// 设置rabbitMQ是持久化的  即使MQ重新启动也没事
		boolean durable = true;
		channel.queueDeclare(QUEUE_NAME, durable, false, false, null);
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
		QueueingConsumer consumer = new QueueingConsumer(channel);
		// 公平的调度   当一个任务完成  应答回来时 在给其分配别的任务
		int prefetchCount = 1;
		channel.basicQos(prefetchCount);
		// 为false 是加入应答机制
		boolean autoAck = false;
		channel.basicConsume(QUEUE_NAME, autoAck, consumer);
//		channel.basicConsume(QUEUE_NAME, true, consumer);
		int i=0;
		while (true) {
			i++;
		    QueueingConsumer.Delivery delivery = consumer.nextDelivery();
		    String message = new String(delivery.getBody());
		    if("Thread-2".equals(Thread.currentThread().getName())&&(i==1||i==4)){
		    	System.out.println("message:线程2 是处于无应答="+message);
		    	Thread.sleep(100000);
//		    	channel.close();
		    }
		    System.out.println(Thread.currentThread().getName()  +" [x] Received '" + message + "'");        
		    doWork(message);
		    System.out.println(" [x] Done");
		    // 回复应答
		    channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
		}
	}
	private static void doWork(String task) throws InterruptedException {
	    for (char ch: task.toCharArray()) {
	        if (ch == '.') Thread.sleep(1000);
	    }
	}
	
	public static void main(String[] args) {
		try {
			mainWorker(args);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}