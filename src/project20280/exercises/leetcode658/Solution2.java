package project20280.exercises.leetcode658;

import java.util.ArrayList;
import java.util.List;

/* Unfinished solution. */
public class Solution2 {
	class Solution {
		
		int[] minFirst,
		  	  maxFirst;
		int sizeMin,
			sizeMax,
			cachePos,
			cachePos2,
			cacheInt;
		
	    public List<Integer> findClosestElements(int[] arr, int k, int x) {
	    	minFirst = new int[k];
	    	maxFirst = new int[k];
	        sizeMin = 0;
	        for (int n : arr) {	
	        	if (sizeMin < k) {
	        		minFirst[sizeMin] = n;
	        		maxFirst[sizeMin] = n;
	        		upheapMinFirst(sizeMin);
	        		upheapMaxFirst(sizeMin++);
	        	}
	        	else {
	        		if (n > minFirst[0]) {
		        		minFirst[0] = n;
		        		downheapMinFirst(0);
	        		}
	        		if (n < maxFirst[0]) {
		        		maxFirst[0] = n;
		        		downheapMaxFirst(0);
	        		}
	        	}
	        }
	        sizeMax = sizeMin;

	        ArrayList<Integer> vals = new ArrayList<Integer>(2*k);
	        while (vals.size() < k) {
	        	int a = Math.abs(minFirst[0] - x);
	        	int b = Math.abs(maxFirst[0] - x);
	        	
	        	
	        }
	        return vals;
	    }
	    
	    public void upheapMinFirst(int pos) {
	    	if (pos > 0) {
	    		cachePos = (pos-1)/2;
	    		if (minFirst[pos] < minFirst[cachePos]) {
	    			cacheInt = minFirst[pos];
	    			minFirst[pos] = minFirst[cachePos];
	    			minFirst[cachePos] = cacheInt;
	    			upheapMinFirst(cachePos);
	    		}
	    	}
	    }
	    
	    public void downheapMinFirst(int pos) {
	    	if ((cachePos = (2 * pos) + 1) < sizeMin) {
	    		if ((cachePos2 = cachePos + 1) < sizeMin) {
	    			if (minFirst[cachePos] < minFirst[cachePos2]) {
	    				if (minFirst[pos] > minFirst[cachePos]) {
	    					cacheInt = minFirst[pos];
	    					minFirst[pos] = minFirst[cachePos];
	    	    			minFirst[cachePos] = cacheInt;
	    	    			downheapMinFirst(cachePos);
	    				}
	    			} else if (minFirst[pos] > minFirst[cachePos2]) {
						cacheInt = minFirst[pos];
						minFirst[pos] = minFirst[cachePos2];
						minFirst[cachePos2] = cacheInt;
		    			downheapMinFirst(cachePos2);
					}
	    		} else if (minFirst[pos] > minFirst[cachePos]) {
					cacheInt = minFirst[pos];
					minFirst[pos] = minFirst[cachePos];
	    			minFirst[cachePos] = cacheInt;
	    			downheapMinFirst(cachePos);
				}
	    	}
	    }
	    
	    public void upheapMaxFirst(int pos) {
	    	if (pos > 0) {
	    		cachePos = (pos-1)/2;
	    		if (maxFirst[pos] > maxFirst[cachePos]) {
	    			cacheInt = maxFirst[pos];
	    			maxFirst[pos] = maxFirst[cachePos];
	    			maxFirst[cachePos] = cacheInt;
	    			upheapMaxFirst(cachePos);
	    		}
	    	}
	    }
	    
	    public void downheapMaxFirst(int pos) {
	    	if ((cachePos = (2 * pos) + 1) < sizeMax) {
	    		if ((cachePos2 = cachePos + 1) < sizeMax) {
	    			if (maxFirst[cachePos] > maxFirst[cachePos2]) {
	    				if (maxFirst[pos] < maxFirst[cachePos]) {
	    					cacheInt = maxFirst[pos];
	    					maxFirst[pos] = maxFirst[cachePos];
	    					maxFirst[cachePos] = cacheInt;
	    	    			downheapMaxFirst(cachePos);
	    				}
	    			} else if (maxFirst[pos] < maxFirst[cachePos2]) {
						cacheInt = maxFirst[pos];
						maxFirst[pos] = maxFirst[cachePos2];
						maxFirst[cachePos2] = cacheInt;
		    			downheapMaxFirst(cachePos2);
					}
	    		} else if (maxFirst[pos] < maxFirst[cachePos]) {
					cacheInt = maxFirst[pos];
					maxFirst[pos] = maxFirst[cachePos];
					maxFirst[cachePos] = cacheInt;
	    			downheapMaxFirst(cachePos);
				}
	    	}
	    }
	}
}
