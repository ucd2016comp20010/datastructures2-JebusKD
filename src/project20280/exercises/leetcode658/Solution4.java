package project20280.exercises.leetcode658;

import java.util.ArrayList;
import java.util.List;

/* Non-heap solution that beats 56% on runtime and  29% memory */
class Solution4 {
	class Solution {
	    int winstart,
	    i;

	    public List<Integer> findClosestElements(int[] arr, int k, int x) {
	        winstart = 0;
	        for (i = k; i < arr.length && ((arr[i] == arr[winstart]) || (Math.abs(arr[i] - x) < Math.abs(arr[winstart] - x))); i++)
	            winstart++;
	        
	        ArrayList<Integer> res = new ArrayList<Integer>(k);
	        for (i = 0; i < k; i++)
	            res.addLast(arr[winstart+i]);
	        
	        return res;
	    }
	}
}
