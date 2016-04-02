package com.github.rabbitmq.rabbitMq;

public class TaskThread implements Runnable{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] threadA ={"1threadA","2threadA","3threadA","4threadA","5threadA","6threadA","7threadA","8threadA","9threadA"};
		new Thread(new TaskThread(threadA)).start();
		
		String[] threadB ={"1threadB","2threadB","3threadB","4threadB","5threadB","6threadB","7threadB","8threadB","9threadB"};
		new Thread(new TaskThread(threadB)).start();
		
		String[] threadC ={"1t.readC","2t.readC","3t.readC","4t.readC","5t.readC","6t.readC","7t.readC","8t.readC","9t.readC"};
		new Thread(new TaskThread(threadC)).start();
	}
	public String[] argv;
	@Override
	public void run() {
		
		try {
			NewTask.mainTask(new String[]{argv[0]});
			NewTask.mainTask(new String[]{argv[1]});
			NewTask.mainTask(new String[]{argv[2]});
			NewTask.mainTask(new String[]{argv[3]});
			NewTask.mainTask(new String[]{argv[4]});
			NewTask.mainTask(new String[]{argv[5]});
			NewTask.mainTask(new String[]{argv[6]});
			NewTask.mainTask(new String[]{argv[7]});
			NewTask.mainTask(new String[]{argv[8]});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public TaskThread(String[] argv) {
		super();
		this.argv = argv;
	}

}
