package com.github.rabbitmq.rabbitMq4;

public class WorkerThread implements Runnable {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Thread(new WorkerThread()).start();
		new Thread(new WorkerThread()).start();
		new Thread(new WorkerThread()).start();
	}

	@Override
	public void run() {
		try {
			ReceiveLogsDirect.main(new String[]{"error","warning","info"});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
