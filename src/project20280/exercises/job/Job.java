package project20280.exercises.job;

public class Job implements Runnable {
	private String jobName;
	private JobPriority jobPriority;
	@Override
	public void run() {
		System.out.println("Job:" + jobName +
						" Priority:" + jobPriority);
		// do some work
		Thread.sleep(1000); // to simulate actual execution time
	}
	// standard setters and getters
	public Job(String name, JobPriority priority) {
		this.jobName = name;
		this.jobPriority = priority;
	}
}