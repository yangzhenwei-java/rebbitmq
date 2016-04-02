package com.github.rabbitmq.rabbitMq5;

public class WorkerThread implements Runnable {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		new Thread(new WorkerThread()).start();
//		new Thread(new WorkerThread()).start();
		new Thread(new WorkerThread()).start();
	}

	@Override
	public void run() {
		try {
			ReceiveLogsTopic.main(new String[]{"kern.*"});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
