package project20280.exercises.job;

import java.util.Comparator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

import project20280.exercises.job.Job.JobComparator;

public class PriorityJobScheduler {
	private ExecutorService priorityJobPoolExecutor;
	private ExecutorService priorityJobScheduler
						= Executors.newSingleThreadExecutor();
	private PriorityBlockingQueue<Job> priorityQueue;
	
	public PriorityJobScheduler(Integer poolSize, Integer queueSize) {
		priorityJobPoolExecutor = Executors.newFixedThreadPool(poolSize);
		priorityQueue = new PriorityBlockingQueue<Job>(
						queueSize,
						new JobComparator());
		priorityJobScheduler.execute(() -> {
			while (true) {
				try {
					priorityJobPoolExecutor.execute(priorityQueue.take());
				} catch (InterruptedException e) {
					// exception needs special handling
				break;
				}
			}
		});
	}
	public void scheduleJob(Job job) {
		priorityQueue.add(job);
	}
}
