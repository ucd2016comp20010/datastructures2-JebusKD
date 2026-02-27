package project20280.exercises;

import java.util.ArrayList;

import project20280.interfaces.Position;
import project20280.tree.LinkedBinaryTree;

public class LinkedBinaryTreeStatistics {
	
	
	
	
	public static void mainDiamHeight() {
		
		int N_START = 50;
		int N_END = 5000;
		int N_STEP = 50;
		int ITERATIONS_PER_STEP = 100;
	
		LinkedBinaryTree<Integer> bt;
		double diameter, height;
		
		for (int n = N_START; n <= N_END; n += N_STEP) {
			
			diameter = 0;
			height = 0;
			
			for (int i = 0; i < ITERATIONS_PER_STEP; i++) {
				
				bt = LinkedBinaryTree.makeRandom(n);
				diameter += bt.diameter();
				height += bt.height();
				
			}
			
			diameter /= ITERATIONS_PER_STEP;
			height /= ITERATIONS_PER_STEP;
			
			//System.out.println("n = " + n + ", diameter = " + diameter + ", height = " + height);
			System.out.println(n + "," + diameter + "," + height);
		}
		
	}
	
	public static void mainInorder() {
		
		int N_START = 10;
		int N_END = 1000;
		int N_STEP = 1;
		int ITERATIONS_PER_STEP = 100;
		
		double result;
		double total;
		
		System.out.println("n,t");
		
		for (int n = N_START; n <= N_END; n += N_STEP) {

			
			total = 0;
			
			for (int i = 0; i < ITERATIONS_PER_STEP; i++) {

				LinkedBinaryTree<Integer> rTree = LinkedBinaryTree.makeRandom(n);
				Runnable worker = () -> {
					System.out.println(rTree.inorder());
				};
				
				result = Timer.measure(worker);
				total += result;
			}
			
			System.out.println(n + "," + total);
		}
		
	}
	
	public static void main(String[] args) {
		mainInorder();
	}
	
	class Timer {
		/*
		* This is a class for timing the execution of a function.
		* Runs the function at least MIN_REPEATS times, never more
		* than MAX_REPEATS times and stops when the execution time
		* exceeds MIN_SECONDS.
		*/
		public static int MIN_REPEATS = 10; // repeat the execution at least this number of times
		public static int MAX_REPEATS = 10000; // repeat the execution at least this number of times
		public static long MIN_SECONDS = (long) (3 * 1e9); // repeat execution of the work
		// until at least this amount of time
		public static double measure(Runnable worker) {
		long n_repeats = 0;
		long start = System.nanoTime();
		while (true) {
		worker.run(); // do work
		++n_repeats;
		if ( (n_repeats > Timer.MIN_REPEATS && // has to run a minimum number of times
		n_repeats <= Timer.MAX_REPEATS) ||
		(n_repeats % Timer.MIN_REPEATS == 0 && // check the time every min_repeat times because this is expensive
		(System.nanoTime() - start) > Timer.MIN_SECONDS) ) {
		break;
		}
		}
		long elapsed = System.nanoTime() - start;
		// System.out.println("# elapsed: " + elapsed + ", " + elapsed/1e9 + " -> " + "n_repeats:" + " " + n_repeats);
		return 1e-9 * elapsed / n_repeats;
		}
	}

}
