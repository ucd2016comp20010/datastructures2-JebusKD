package project20280.exercises.leetcode215;

/*
 	Much more optimised, using array instead
 	of linked binary list data structure
 	for the heap, beats around 94% on
 	runtime and 30% on memory.
 */

class Solution3 {
	class Solution {
		int[] members;
		int size,
			cachePos,
			cachePos2,
			cacheInt;
		
	    public int findKthLargest(int[] nums, int k) {
	        members = new int[k];
	        size = 0;
	        for (int n : nums) {	
	        	if (size < k) {
	        		members[size] = n;
	        		upheap(size++);
	        	}
	        	else if (n > members[0]) {
	        		members[0] = n;
	        		downheap(0);
	        	}
	        }
	        cacheInt = members[0];
	        members = null;
	        return cacheInt;
	    }
	    
	    public void upheap(int pos) {
	    	if (pos > 0) {
	    		cachePos = (pos-1)/2;
	    		if (members[pos] < members[cachePos]) {
	    			cacheInt = members[pos];
	    			members[pos] = members[cachePos];
	    			members[cachePos] = cacheInt;
	    			upheap(cachePos);
	    		}
	    	}
	    }
	    
	    public void downheap(int pos) {
	    	if ((cachePos = (2 * pos) + 1) < size) {
	    		if ((cachePos2 = cachePos + 1) < size) {
	    			if (members[cachePos] < members[cachePos2]) {
	    				if (members[pos] > members[cachePos]) {
	    					cacheInt = members[pos];
	    	    			members[pos] = members[cachePos];
	    	    			members[cachePos] = cacheInt;
	    	    			downheap(cachePos);
	    				}
	    			} else if (members[pos] > members[cachePos2]) {
						cacheInt = members[pos];
		    			members[pos] = members[cachePos2];
		    			members[cachePos2] = cacheInt;
		    			downheap(cachePos2);
					}
	    		} else if (members[pos] > members[cachePos]) {
					cacheInt = members[pos];
	    			members[pos] = members[cachePos];
	    			members[cachePos] = cacheInt;
	    			downheap(cachePos);
				}
	    	}
	    }
	    
	    
	}
}