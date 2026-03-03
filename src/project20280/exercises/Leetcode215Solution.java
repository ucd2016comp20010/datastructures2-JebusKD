package project20280.exercises;

class Leetcode215Solution {
	class Solution {
	    public int findKthLargest(int[] nums, int k) {
	        HighLoQueue q = new HighLoQueue(k, nums.length * 2);
	        for (int n : nums) {
	            q.add(n);
	        }
	        return q.getLow();
	    }

	    private static class HighLoQueue {
	        private int size;
	        private int[] members;
	        private int start;
	        private final int maxSize;

	        private static final int MULT_FACTOR = 2;

	        public HighLoQueue(int maxSize, int capacity) {
	            this.members = new int[capacity];
	            this.size = 0;
	            this.start = capacity / 2;
	            this.maxSize = maxSize;
	        }

	        public HighLoQueue(int maxSize) {
	            this(maxSize, 8 * MULT_FACTOR);
	        }

	        public boolean endReached() {
	            return (start + size) >= members.length;
	        }

	        public boolean startReached() {
	            return start <= 0;
	        }

	        public boolean isEmpty() {
	            return size == 0;
	        }

	        public void addLow(int e) {
	            if (maxSize == size) return;
	            if (startReached()) rebuild();

	            members[--start] = e;
	            size++;
	        }

	        public void addHigh(int e) {
	            if (maxSize == size) removeLow();
	            if (endReached()) rebuild();

	            members[start + (size++)] = e;
	        }

	        public int removeLow() {
	            size--;
	            return members[start++];
	        }

	        public int removeHigh() {
	            return members[start+(--size)];
	        }

	        public int get(int i) {
	            if (i >= size || i < 0) throw new IllegalArgumentException("Accessing out of bounds " + i);
	            return members[start + i];
	        }

	        public int getLow() {
	            if (size == 0) throw new IllegalArgumentException("Empty array");
	            return members[start];
	        }

	        public int getHigh() {
	            if (size == 0) throw new IllegalArgumentException("Empty array");
	            return members[start + size - 1];
	        }

	        public void set(int i, int e) {
	            if (i >= size || i < 0) throw new IllegalArgumentException("Accessing out of bounds " + i);
	            members[start + i] = e;
	        }

	        private void shoveLeft(int i) {
	            if (startReached()) rebuild();

	            /*for (int j = 0; j <= i; j++) {
	                members[start+j-1] = members[start+j];
	            }*/
	            // Had to use arraycopy because leetcode time limit kept exceeding
	            System.arraycopy(members, start,
	                             members, start-1,
	                             i+1);
	            start--;
	            size++;
	        }

	        public void insertAfter(int i, int e) {
	            if (i + 1 == size) {
	                addHigh(e);
	                return;
	            }
	            if (i < 0) {
	                addLow(e);
	                return;
	            }
	            shoveLeft(i);
	            set(i+1, e);
	            if (maxSize < size) removeLow();
	        }

	        public void add(int e) {
	            if (isEmpty()) {
	                //System.out.println("empty");
	                addLow(e);
	            }
	            else if (e >= getHigh() ) {
	                //System.out.println("addHigh, e = " + e + ", high = " + getHigh());
	                addHigh(e);
	            }
	            else if (size < maxSize && e <= getLow()) {
	                //System.out.println("addLow, e = " + e + ", low = " + getLow());
	                addLow(e);
	            }
	            else if (e > getLow() ) binarySearchAdd(e);
	        }

	        private void linearSearchAdd(int e) {
	            //System.out.println("searchadd...");
	            for (int i = size-1; i >= 0; i--) {
	                if (e >= get(i)) {
	                    insertAfter(i, e);
	                    return;
	                }
	            }
	            throw new IllegalArgumentException("uh oh");
	        }

	        private void binarySearchAdd(int e) {
	            //System.out.println(this);
	            int searchStart = 0;
	            int searchEnd = size;
	            int i;
	            do {
	                i = (searchStart+searchEnd) / 2;
	                if (get(i) <= e && e <= get(i+1)) {
	                    insertAfter(i, e);
	                    return;
	                }
	                else if (get(i) <= e) {
	                    searchStart = i+1;
	                }
	                else {
	                    searchEnd = i;
	                }
	            }
	            while (searchStart != searchEnd);
	            insertAfter(searchStart, e);
	        }

	        private void rebuild() {
	            System.out.println("Rebuild called");
	            int newArrayCap = members.length * MULT_FACTOR;
	            int[] newArray = new int[newArrayCap];
	            int newStart = newArrayCap / 4;

	            for (int i = 0; i < size; i++) {
	                newArray[newStart + i] = get(i);
	            }

	            this.members = newArray;
	            start = newStart;
	        }

	        public String toString() {
	            String s = "[";
	            if (!isEmpty()) s = s.concat("" + get(0));
	            for (int i = 1; i < size; i++) {
	                s = s.concat(", " + get(i));
	            }
	            return s.concat("]");
	        }
	    }
	}
}
