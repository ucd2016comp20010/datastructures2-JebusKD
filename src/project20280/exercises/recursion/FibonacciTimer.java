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
	
	public static void main_timetest() {
		int iterations = 100;
		
		String topFormat = "%-5s|%16s|%16s";
		String bar = "";
		for (int i = 0; i < 39; i++) {
			bar = bar.concat("-");
		}
		String standardFormat = "%-5s|%16.3f|%16.3f";
		
		System.out.println(String.format(topFormat, "n", "Memoisation ", "No Memoisation "));
		System.out.println(bar);
		
		// n = 45 for 1 minute time no memo (n = 44 : 45 seconds)
		/* With memoisation, causes a stack overflow error at around n = 17700,
		 	taking ~4 ms.
		 */
		FibonacciTimer ftm;
		FibonacciTimer ftn;
		for (int i = 1; i < 2000000; i+=100) {
			ftm = new FibonacciTimer(i, true, iterations);
			//ftn = new FibonacciTimer(i, false, iterations);
			
			System.out.println(String.format(standardFormat, i, ftm.getAvgDuration(), null/*ftn.getAvgDuration()*/));
		}
	}
	
	public static void main_recursive_calls() {
		int n = 45;
		
		// n = 45
		// Memo : 88 recursive calls
		// No memo : 3672623804 recursive calls
		Fibonacci f = new Fibonacci(n, false);
		System.out.println("Calls: " + f.getRecursiveCalls());
	}
	
	public static void main(String[] args) {
		main_recursive_calls();
		
		//main_timetest();
	}
}
