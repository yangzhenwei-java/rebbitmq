package com.github.rabbitmq.rabbitMq4;



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
		
		String[] threadC ={"1t.readC","2t.readC","3t.readC","4t.readC","5t.readC","6t.readC","7t.readC","8t.readC","9t.readC"};
		new Thread(new TaskThread(threadC)).start();
	}
	public String[] argv;
	@Override
	public void run() {
		
		try {
			EmitLogDirect.main(new String[]{"error",argv[0]});
			EmitLogDirect.main(new String[]{"error",argv[1]});
			EmitLogDirect.main(new String[]{"error",argv[2]});
			EmitLogDirect.main(new String[]{"warning",argv[3]});
			EmitLogDirect.main(new String[]{"error",argv[4]});
			EmitLogDirect.main(new String[]{"warning",argv[5]});
			EmitLogDirect.main(new String[]{"info",argv[6]});
			EmitLogDirect.main(new String[]{"error",argv[7]});
			EmitLogDirect.main(new String[]{"error",argv[8]});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public TaskThread(String[] argv) {
		super();
		this.argv = argv;
	}

}
