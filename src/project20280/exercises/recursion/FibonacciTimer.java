package project20280.exercises.recursion;

public class FibonacciTimer {

	private long startTime;
	private long endTime;
	private long duration;
	private double durationAverage;
	private int n;
	private boolean memo;
	private int iterations;
	
	public FibonacciTimer(int n, boolean memo, int iterations) {
		this.n = n;
		this.memo = memo;
		this.iterations = iterations;
		
		this.startTime = System.currentTimeMillis();
		
		for (int i = 0; i < this.iterations; i++) {
			new Fibonacci(this.n, this.memo);
		}
		
		this.endTime = System.currentTimeMillis();
		
		this.duration = this.endTime - this.startTime;
		this.durationAverage = this.duration / (double) this.iterations;
		
	}
	
	public double getAvgDuration() {
		return this.durationAverage;
	}
	
	public static void main(String[] args) {
		
	}
}
