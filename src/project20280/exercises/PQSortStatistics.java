package project20280.exercises;

import java.util.Random;

import project20280.exercises.LinkedBinaryTreeStatistics.Timer;
import project20280.priorityqueue.HeapPriorityQueue;
import project20280.tree.LinkedBinaryTree;

public class PQSortStatistics {

	private static final Random RNG = new Random();
	
	public static Integer[] makeRandomArr(int n) {
		Integer[] arr = new Integer[n];
		for (int  i = 0; i < n; i++) {
			arr[i] = RNG.nextInt();
		}
		return arr;
	}
	
	public static void main(String[] args) {
		
		int N_START = 400000;
		int N_END = 1000000;
		int N_STEP = 100000;
		int ITERATIONS_PER_STEP = 1;
		
		double result;
		double total;
		
		//System.out.println("n,t");
		
		for (int n = N_START; n <= N_END; n += N_STEP) {

			
			total = 0;
			
			for (int i = 0; i < ITERATIONS_PER_STEP; i++) {

				Integer[] rArray = makeRandomArr(n);
				Runnable worker = () -> {
					HeapPriorityQueue.PQSort(rArray);
				};
				
				result = Timer.measure(worker);
				total += result;
			}
			
			System.out.println(n + "," + total / ITERATIONS_PER_STEP);
		}
		
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
