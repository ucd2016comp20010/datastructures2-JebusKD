package project20280.exercises.job;

public class Job implements Runnable {
	private String jobName;
	private JobPriority jobPriority;
	@Override
	public void run() {
		System.out.println("Job:" + jobName +
						" Priority:" + jobPriority);
		// do some work
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // to simulate actual execution time
	}
	// standard setters and getters
	public Job(String name, JobPriority priority) {
		this.jobName = name;
		this.jobPriority = priority;
	}
	
	public JobPriority getJobPriority() {
		return this.jobPriority;
	}
	
	public String getName() {
		return this.jobName;
	}
}