package project20280.exercises;

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
		int N_END = 10000;
		
	}
	
	public static void main(String[] args) {
		mainInorder();
	}
	
	private static class Stopwatch {
		
		private long startTime;
		private long endTime;
		private long duration;
		
		public Stopwatch() {
			this.reset();
		}
		
		public void reset() {
			this.startTime = -1;
			this.endTime = -1;
			this.duration = -1;
		}
		
		public void start() {
			this.startTime = System.currentTimeMillis();
		}
		
		public void stop() {
			this.endTime = System.currentTimeMillis();
			this.calculateDuration();
		}
		
		public void calculateDuration() {
			this.duration = this.endTime - this.startTime;
		}
		
		public long duration() {
			return this.duration;
		}
		
	}
}
