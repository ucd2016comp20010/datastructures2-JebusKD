package project20280.exercises.leetcode658;

import java.util.ArrayList;
import java.util.List;

/* This was done before knowing what
 	a heap is.
 	
 	Beats 30% on runtime and 80% on memory.
 */
class Leetcode658Solution {
	class Solution {
	    private static int binSearch(int[] arr, int x) {
	        int i = 0;
	        int start = 0, end = arr.length;
	        for (i = (start + end) / 2; start < end && arr[i] != x; i = (start + end) / 2) {
	            if (arr[i] > x) {
	                end = i;
	            }
	            else {
	                start = i + 1;
	            }
	        }
	        System.out.println(i);
	        return i;
	    }

	    private static int immediateCorrect(int[] arr, int c, int x) {
	        

	        if (c >= arr.length) {
	            c = arr.length-1;
	        }

	        int correctIndex = c;
	        
	        int a;
	        int b = arr[c];
	        if (c > 0) {
	            a = arr[c-1];
	            if (Math.abs(a - x) < Math.abs(b - x) || Math.abs(a - x) == Math.abs(b - x) && a < b) {
	                correctIndex = c - 1;
	            }
	        }

	        if (c < arr.length-1) {
	            a = arr[c+1];
	            if (Math.abs(a - x) < Math.abs(b - x) || Math.abs(a - x) == Math.abs(b - x) && a < b) {
	                correctIndex = c + 1;
	            }
	        }

	        return correctIndex;
	    }

	    public List<Integer> findClosestElements(int[] arr, int k, int x) {
	        ArrayList<Integer> closestElements = new ArrayList<Integer>(k);

	        int closestIndex = binSearch(arr, x);
	        

	        closestIndex = immediateCorrect(arr, closestIndex, x);
	        

	        closestElements.addFirst(arr[closestIndex]);
	        

	        int low = closestIndex - 1;
	        int high = closestIndex + 1;

	        int added = 1;
	        while (low >= 0 && high < arr.length && added < k) {
	            int a = arr[low];
	            int b = arr[high];
	            if (Math.abs(a - x) < Math.abs(b - x) || Math.abs(a - x) == Math.abs(b - x) && a < b) {
	                closestElements.addFirst(arr[low--]);
	            } else {
	                closestElements.addLast(arr[high++]);
	            }
	            added++;
	        }

	        while (low >= 0 && added < k) {
	            closestElements.addFirst(arr[low--]);
	            added++;
	        }

	        while (high < arr.length && added < k) {
	            closestElements.addLast(arr[high++]);
	            added++;
	        }

	        return closestElements;
	    }
	}
}
