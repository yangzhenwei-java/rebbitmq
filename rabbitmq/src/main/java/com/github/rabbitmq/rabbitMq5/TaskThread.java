package com.github.rabbitmq.rabbitMq5;

public class TaskThread implements Runnable{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		String[] threadA ={"1threadA","2threadA","3threadA","4threadA","5threadA","6threadA","7threadA","8threadA","9threadA"};
//		new Thread(new TaskThread(threadA)).start();
//		
//		String[] threadB ={"1threadB","2threadB","3threadB","4threadB","5threadB","6threadB","7threadB","8threadB","9threadB"};
//		new Thread(new TaskThread(threadB)).start();
		
//		String[] threadC ={"1t.readC","2t.readC","3t.readC","4t.readC","5t.readC","6t.readC","7t.readC","8t.readC","9t.readC"};
//		new Thread(new TaskThread(threadC)).start();
		new Thread(new TaskThread()).start();
	}
	public String[] argv;
	@Override
	public void run() {
		
		try {
			EmitLogTopic.main(new String[]{"kern1.critical","A critical kernel error"});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public TaskThread(String[] argv) {
		super();
		this.argv = argv;
	}
	public TaskThread() {
		super();
	}

}
