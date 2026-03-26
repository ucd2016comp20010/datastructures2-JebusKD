package project20280.exercises.leetcode658;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Solution3 {
	class Solution {
		
		int[] members;
		int size,
			cachePos,
			cachePos2,
			cacheInt,
			x,
			a,
			b,
			c;
		
		public List<Integer> findClosestElements(int[] arr, int k, int x) {
	        this.x = x;
	        
	        members = new int[k];
	        size = 0;
	        
	        for (int n : arr) {	
	        	if (size < k) {
	        		members[size] = n;
	        		upheap(size++);
	        	}
	        	else if (Math.abs(n - x) < Math.abs(members[0] - x)) {
	        		members[0] = n;
	        		downheap(0);
	        	}
	        	else {
	        		break;
	        	}
	        	/*
	        	System.out.println();
	        	for (int g : members) {
	        		System.out.print(g + " ");
	        	}*/
	        }
	        
	        ArrayList<Integer> result = new ArrayList<Integer>(k);
	        while (size > 0) {
	        	if (result.isEmpty() || result.getFirst() >= members[0])
	        		result.addFirst(members[0]);
	        	else
	        		result.addLast(members[0]);
	        	
	        	cacheInt = members[size-1];
	        	members[--size] = members[0];
	        	members[0] = cacheInt;
	        	downheap(0);
	        	/*
	        	System.out.println();
	        	for (int g : members) {
	        		System.out.print(g + " ");
	        	}
				*/
	        }
	        result.sort(Comparator.naturalOrder());
	        return result;
	    }
		
		public void upheap(int pos) {
	    	if (pos > 0) {
	    		cachePos = (pos-1)/2;
	    		if (members[pos] != members[cachePos]) {
		    		a = Math.abs(members[cachePos] - x);
		    		b = Math.abs(members[pos] - x);
		    		if (a < b || (a == b && members[cachePos] < members[pos])) {
		    			cacheInt = members[pos];
		    			members[pos] = members[cachePos];
		    			members[cachePos] = cacheInt;
		    			upheap(cachePos);
		    		}
	    		}
	    	}
	    }
		
		public void downheap(int pos) {
	    	if ((cachePos = (2 * pos) + 1) < size) {
	    		a = Math.abs(members[cachePos] - x);
	    		c = Math.abs(members[pos] - x);
	    		if ((cachePos2 = cachePos + 1) < size) {
	    			
	    			
	    			b = Math.abs(members[cachePos2] - x);
	    			
	    			
	    			if (a < b || (a == b && members[cachePos] < members[cachePos2])) {
	    				if (a < c || (c == a && members[pos] > members[cachePos])) {
	    					cacheInt = members[pos];
	    	    			members[pos] = members[cachePos];
	    	    			members[cachePos] = cacheInt;
	    	    			downheap(cachePos);
	    				}
	    			} else if (b < c || (c == b && members[pos] > members[cachePos2])) {
						cacheInt = members[pos];
		    			members[pos] = members[cachePos2];
		    			members[cachePos2] = cacheInt;
		    			downheap(cachePos2);
					}
	    		} else if (a < c || (a == c && members[pos] > members[cachePos])) {
					cacheInt = members[pos];
	    			members[pos] = members[cachePos];
	    			members[cachePos] = cacheInt;
	    			downheap(cachePos);
				}
	    	}
	    }
		
	}
}
