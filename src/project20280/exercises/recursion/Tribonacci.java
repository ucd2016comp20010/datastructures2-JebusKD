package project20280.exercises.recursion;

public class Tribonacci {

	public static Long compute(int n) {
		if (n <= 1) return Long.valueOf((long) 0);
		if (n == 2) return Long.valueOf((long) 1);
		return compute(n - 1) + compute(n - 2) + compute(n - 3);
	}
	
	public static void main(String[] args) {
		System.out.println(compute(9));
	}
}
