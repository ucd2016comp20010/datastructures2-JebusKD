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
		
	}
	
	public static void main(String[] args) {
		
	}
}
