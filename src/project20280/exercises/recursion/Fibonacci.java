package project20280.exercises.recursion;

import java.util.concurrent.ConcurrentHashMap;

public class Fibonacci {

	private Long result;
	private ConcurrentHashMap<Integer, Long> memo;
	
	private Long compute(Integer n) {
		if (n <= 0) return Long.valueOf((long) 0);
		if (n == 1) return Long.valueOf((long) 1);
		
		Long val = memo.get(n);
		if (val != null) return val;
		else {
			val = compute(n - 1) + compute(n - 2);
			memo.put(n, val);
			return val;
		}
	}
	
	private Long computeNoMemo(Integer n) {
		if (n <= 0) return Long.valueOf((long) 0);
		if (n == 1) return Long.valueOf((long) 1);
		
		return computeNoMemo(n - 1) + computeNoMemo(n - 2);
	}
	
	public Fibonacci(Integer n) {
		this(n, true);
	}
	
	public Fibonacci(Integer n, boolean memoisation) {
		if (memoisation) {
			memo = new ConcurrentHashMap<Integer, Long>();
			this.result = compute(n);
		}
		else {
			this.result = computeNoMemo(n);
		}
	}
	
	public Long result() {
		return this.result;
	}
	
	public static void main(String[] args) {
		Fibonacci f = new Fibonacci(19);
		System.out.println(f.result());
	}
}
