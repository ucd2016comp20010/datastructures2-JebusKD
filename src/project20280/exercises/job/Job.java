package project20280.exercises.job;

import java.util.Comparator;

public class Job implements Runnable, Comparable<Job> {
	private String jobName;
	private JobPriority jobPriority;
	private int expectedDuration;
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
		this.expectedDuration = -1;
	}
	
	public Job(String name, JobPriority priority, int expectedDuration) {
		this.jobName = name;
		this.jobPriority = priority;
		this.expectedDuration = expectedDuration;
	}
	
	public JobPriority getJobPriority() {
		return this.jobPriority;
	}
	
	public int getExpectedDuration() {
		return this.expectedDuration;
	}
	
	public String getName() {
		return this.jobName;
	}
	
	public int compareTo(Job o) {
		if (o == null) throw new NullPointerException("Object is null");
		if (o instanceof Job j) {
			int prioComp = this.jobPriority.comp(j.jobPriority);
			if (prioComp == 0) {
				return this.expectedDuration - j.expectedDuration;
			}
			else {
				return prioComp;
			}
		}
		else {
			throw new ClassCastException("Object not a job");
		}
	}
	
	public static class JobComparator implements Comparator<Job> {
		public int compare(Job j1, Job j2) {
			return j1.compareTo(j2);
		}
	}
	
}