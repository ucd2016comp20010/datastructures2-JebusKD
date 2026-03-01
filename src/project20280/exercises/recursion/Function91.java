package project20280.exercises.recursion;

public class Function91 {

	public static Long compute(Long n) {
		if (n > 100) return n - 10;
		else return compute(compute(n + 11));
	}
	
	public static void main(String args[]) {
		
		System.out.println(compute(Long.valueOf(87)));
	}
	
}
