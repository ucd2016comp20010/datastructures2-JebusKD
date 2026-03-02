package project20280.exercises;

class Leetcode215Solution {
	class Solution {
	    private static void sortedInsert(int num, MyQueue elements, int desiredSize) {
	        if (elements.isEmpty()) elements.addFirst(num);
	        else if (elements.size < desiredSize) {
	            if (elements.getLast() <= num) {
	                elements.addLast(num);
	            }
	            else if (elements.getFirst() <= num) {
	                for (int i = elements.size()-2; i >= 0; i--) {
	                    if (elements.get(i) <= num && elements.get(i+1) >= num) {
	                        elements.insertAfter(i, num);
	                        break;
	                    }
	                }
	            }
	            else {
	                elements.addFirst(num);
	            }
	        }
	        else {
	            if (elements.getLast() <= num) {
	                elements.removeFirst();
	                elements.addLast(num);
	            }
	            else if (elements.getFirst() <= num) {
	                for (int i = elements.size()-2; i >= 0; i--) {
	                    if (elements.get(i) < num && elements.get(i+1) > num) {
	                        elements.insertAfter(i, num);
	                        break;
	                    }
	                }
	                elements.removeFirst();
	            }
	        }
	    }

	    public int findKthLargest(int[] nums, int k) {
	        MyQueue topElements = new MyQueue(k);

	        for (int i = 0; i < nums.length; i++) {
	            sortedInsert(nums[i], topElements, k);
	        }
	        return topElements.getFirst();
	    }

	    private static class MyQueue {
	        private static final int MULT_FACTOR = 2;
	        private Integer[] members;
	        private int capacity;

	        private int size;
	        private int start;
	        private int end;

	        public MyQueue(int capacity) {
	            this.capacity = MULT_FACTOR * capacity;
	            members = new Integer[this.capacity];
	            start = capacity / MULT_FACTOR;
	            end = start;
	            size = 0;
	        }

	        public int size() {
	            return this.size;
	        }

	        public void addFirst(Integer e) {
	            if (start <= 0) rebuild();

	            members[--start] = e;
	            size++;
	        }

	        public void addLast(Integer e) {
	            if (end >= capacity) rebuild();

	            members[end++] = e;
	            size++;
	        }

	        public boolean isEmpty() {
	            return size == 0;
	        }

	        public void insertAfter(int i, Integer e) {
	            splitShift(i);
	            members[start + i + 1] = e;
	        }

	        public Integer removeFirst() {
	            size--;
	            return members[start++];
	        }

	        public Integer removeLast() {
	            size--;
	            return members[--end];
	        }

	        public Integer get(int i) {
	            if (i < 0 || size <= i) throw new IllegalArgumentException("Accessing out of bounds entity " + i);
	            return members[start + i];
	        }

	        public Integer getLast() {
	            return members[end - 1];
	        }

	        public Integer getFirst() {
	            return members[start];
	        }

	        private void splitShift(int index) {
	            if (start <= 0) rebuild();

	            for (int i = 0; i <= index; i++) {
	                members[start + (i-1)] = members[start + i];
	            }
	            members[start + index] = null;
	            start--;
	            size++;
	        }

	        public String toString() {
	            String s = "[";
	            if (!isEmpty()) {
	                s = s.concat(getFirst().toString());
	            }
	            for (int i = 1; i < size; i++) {
	                if (get(i) == null)
	                    s = s.concat(", null");
	                else
	                    s = s.concat(", " + get(i).toString());
	                
	            }
	            return s.concat("]");
	        }

	        private void rebuild() {
	            int newArrayCapacity = capacity * MULT_FACTOR;
	            Integer[] newArray = new Integer[newArrayCapacity];
	            int newArrayStart = capacity / MULT_FACTOR;
	           
	            for (int i = start, j = newArrayStart; i < end; i++, j++) {
	                newArray[j] = members[i];
	                
	            }

	            

	            this.members = newArray;
	            capacity = newArrayCapacity;
	            start = newArrayStart;
	            end = start+size;
	        }
	    }
	}
}
