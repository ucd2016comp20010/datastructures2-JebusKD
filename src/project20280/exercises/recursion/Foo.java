package project20280.exercises.recursion;

public class Foo {

	public static void compute(int x) {
		if (x / 2 == 0) {
			System.out.print(x);
			return;
		}
		compute(x/2);
		System.out.print(x % 2);
	}
	
	public static void main(String args[]) {
		compute(2468);
		System.out.println();
	}
	
}
