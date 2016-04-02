package com.github.rabbitmq.rabbitMq;

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
			Worker.mainWorker(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
