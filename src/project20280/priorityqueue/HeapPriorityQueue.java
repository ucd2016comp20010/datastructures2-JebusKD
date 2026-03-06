package project20280.priorityqueue;

/*
 */

import project20280.interfaces.Entry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;


/**
 * An implementation of a priority queue using an array-based heap.
 */

public class HeapPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {

    protected ArrayList<Entry<K, V>> heap = new ArrayList<>();

    /**
     * Creates an empty priority queue based on the natural ordering of its keys.
     */
    public HeapPriorityQueue() {
        super();
    }

    /**
     * Creates an empty priority queue using the given comparator to order keys.
     *
     * @param comp comparator defining the order of keys in the priority queue
     */
    public HeapPriorityQueue(Comparator<K> comp) {
        super(comp);
    }

    /**
     * Creates a priority queue initialized with the respective key-value pairs. The
     * two arrays given will be paired element-by-element. They are presumed to have
     * the same length. (If not, entries will be created only up to the length of
     * the shorter of the arrays)
     *
     * @param keys   an array of the initial keys for the priority queue
     * @param values an array of the initial values for the priority queue
     */
    public HeapPriorityQueue(K[] keys, V[] values) {
    	super();
    	// Unconditionally add all key/value pairs to the new heap.
    	for (int i = 0; i < keys.length && i < values.length; i++) {
    		heap.add(new PQEntry<K, V>(keys[i], values[i]));
    	}
    	// Now everything is in, we use heapify to restore heap structure.
    	heapify();
    }
    
    /**
     * Creates a priority queue initialized with the respective key-value pairs. The
     * two arrays given will be paired element-by-element. They are presumed to have
     * the same length. (If not, entries will be created only up to the length of
     * the shorter of the arrays). The queue uses the given comparator to order keys.
     *
     * @param keys   an array of the initial keys for the priority queue
     * @param values an array of the initial values for the priority queue
     * @param comp comparator defining the order of keys in the priority queue
     */
    public HeapPriorityQueue(K[] keys, V[] values, Comparator<K> comp) {
    	super(comp);
    	// Unconditionally add all key/value pairs to the new heap.
    	for (int i = 0; i < keys.length && i < values.length; i++) {
    		heap.add(new PQEntry<K, V>(keys[i], values[i]));
    	}
    	// Now everything is in, we use heapify to restore heap structure.
    	heapify();
    }

    // protected utilities
    protected int parent(int j) {
    	if (j < 0 || j >= size()) throw new IllegalArgumentException("Referring to out of bounds index " + j);
    	else if (j == 0) return -1; // If j is the root, has no parent.
        else return (j - 1) / 2; // floor( (f(p) - 1) / 2 )
    }

    protected int left(int j) {
    	if (j < 0 || j >= size()) throw new IllegalArgumentException("Referring to out of bounds index " + j);
        return (2 * j) + 1; // f(p) = 2f(q) + 1
    }

    protected int right(int j) {
    	if (j < 0 || j >= size()) throw new IllegalArgumentException("Referring to out of bounds index " + j);
        return (2 * j) + 2; // f(p) = 2f(q) + 2
    }

    protected boolean hasLeft(int j) {
        int p = left(j);
        return p < size() && heap.get(p) != null;
    }

    protected boolean hasRight(int j) {
    	int p = right(j);
        return p < size() && heap.get(p) != null;
    }

    /**
     * Exchanges the entries at indices i and j of the array list.
     */
    protected void swap(int i, int j) {
    	int s = size();
    	if (i < 0 || i >= s) throw new IllegalArgumentException("Referring to out of bounds index " + i);
    	if (j < 0 || j >= s) throw new IllegalArgumentException("Referring to out of bounds index " + j);
        
    	if (i == j) return; // If we swap an element with itself, do nothing.
    	Entry<K,V> held = heap.get(i);
    	heap.set(i, heap.get(j));
    	heap.set(j, held);
    }

    /**
     * Moves the entry at index j higher, if necessary, to restore the heap
     * property.
     */
    protected void upheap(int j) {
    	int parent = parent(j);
    	
    	/* If parent < 0, then j is root and has no parent.
    	 	We compare the k_j and k_p, if k_j <= k_p these two
    	 	elements are in correct heap ordering.
    	 */
    	if (parent >= 0 && compare(heap.get(j), heap.get(parent)) < 0) {
    		/* Not in the correct order? Swap this item with the parent,
    		 	and see does this item (now in the parent's position) still
    		 	violates heap ordering recursively.
    		 */
    		swap(j, parent);
    		upheap(parent);
    	}
    }

    /**
     * Moves the entry at index j lower, if necessary, to restore the heap property.
     */
    protected void downheap(int j) {
    	
        if (hasLeft(j)) {
        	
        	Entry<K,V> i = heap.get(j);
        	
        	int lPos = left(j);
        	Entry<K,V> l = heap.get(lPos);
        	
        	if (hasRight(j)) {
        		
        		int rPos = right(j);
        		Entry<K,V> r = heap.get(rPos);
        		
        		// We intentionally consider the smaller of the two first.
        		if (compare(l, r) <= 0) {
        			// consider left first
        			if (compare(i, l) > 0) {
        				swap(j, lPos);
        				downheap(lPos);
        			}
        			else if (compare(i, r) > 0) {
        				swap(j, rPos);
        				downheap(rPos);
        			}
        			
        		}
        		else { // Consider right first
        			if (compare(i, r) > 0) {
        				swap(j, rPos);
        				downheap(rPos);
        			}
        			else if (compare(i, l) > 0) {
        				swap(j, lPos);
        				downheap(lPos);
        			}
        		}
        		
        	}
        	else {
        		
        		// If we just have a left node, see if a swap should be done.
        		// A left node and no right node indicates we've reached the bottom
        		// of the tree (presuming structure is not violated), so no need
        		// to downheap further.
        		if (compare(i, l) > 0) {
    				swap(j, lPos);
    			}
        	}
        	
        }
        else if (hasRight(j)) {
        	// If j has right but no left, something's gone wrong.
        	throw new IllegalStateException("Correct binary tree structure has been violated - something has gone seriously wrong.");
        }
    }

    /**
     * Performs a bottom-up construction of the heap in linear time.
     */
    protected void heapify() {
        for (int i = size()-1; i >= 0; i--) {
        	downheap(i);
        }
    }

    // public methods

    /**
     * Returns the number of items in the priority queue.
     *
     * @return number of items
     */
    @Override
    public int size() {
        return heap.size();
    }

    /**
     * Returns (but does not remove) an entry with minimal key.
     *
     * @return entry having a minimal key (or null if empty)
     */
    @Override
    public Entry<K, V> min() {
    	if (size() == 0) return null;
    	else return heap.get(0);
    }

    /**
     * Inserts a key-value pair and return the entry created.
     *
     * @param key   the key of the new entry
     * @param value the associated value of the new entry
     * @return the entry storing the new key-value pair
     * @throws IllegalArgumentException if the key is unacceptable for this queue
     */
    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
    	// Check to see if the key is valid.
    	if (!checkKey(key)) throw new IllegalArgumentException("Key is unacceptable for this queue.");
        
    	// Create a new entry with our key-value pair.
    	PQEntry<K, V> newEntry = new PQEntry<K, V>(key, value);
    	// Get the size, which would correspond to the initial position of our newly-added entry.
        int pos = size();
        // Append the entry to the heap.
        heap.add(newEntry);
        // Use uphead to move the new entry into place.
        upheap(pos);
        // Return the new entry storing the key-value pair.
        return newEntry;
    }

    /**
     * Removes and returns an entry with minimal key.
     *
     * @return the removed entry (or null if empty)
     */
    @Override
    public Entry<K, V> removeMin() {
    	int s = size();
    	// If the size is 0, we return null as the heap is empty.
    	if (s == 0) return null;
    	// Swap the last item into position
    	swap(0, s-1);
    	// Remove the last entry in the heap, which was previously
    	// the smallest (root) before the swap.
    	Entry<K, V> removedEntry = heap.removeLast();
    	
    	// Restore the heap structure if there's anything left.
    	if (size() > 0) downheap(0);
    	return removedEntry;
    }

    public String toString() {
        return heap.toString();
    }

    /**
     * Used for debugging purposes only
     */
    private void sanityCheck() {
        for (int j = 0; j < heap.size(); j++) {
            int left = left(j);
            int right = right(j);
            //System.out.println("-> " +left + ", " + j + ", " + right);
            Entry<K, V> e_left, e_right;
            e_left = left < heap.size() ? heap.get(left) : null;
            e_right = right < heap.size() ? heap.get(right) : null;
            if (left < heap.size() && compare(heap.get(left), heap.get(j)) < 0) {
                System.out.println("Invalid left child relationship");
                System.out.println("=> " + e_left + ", " + heap.get(j) + ", " + e_right);
            }
            if (right < heap.size() && compare(heap.get(right), heap.get(j)) < 0) {
                System.out.println("Invalid right child relationship");
                System.out.println("=> " + e_left + ", " + heap.get(j) + ", " + e_right);
            }
        }
    }
    
    public static <V> V[] PQSort(V[] values, Comparator<V> comparator) {
    	HeapPriorityQueue<V, V> queue = new HeapPriorityQueue<V, V>(comparator);
    	for (int i = 0; i < values.length; i++) {
    		queue.insert(values[i], values[i]);
    	}
    	
    	@SuppressWarnings("unchecked")
		V[] arr = (V[])(new Object[queue.size()]);
    	for (int i = 0; queue.size() > 0; i++) {
    		arr[i] = queue.removeMin().getValue();
    	}
    	return arr;
    }
    
    public static <V> V[] PQSort(V[] values) {
    	HeapPriorityQueue<V, V> queue = new HeapPriorityQueue<V, V>();
    	for (int i = 0; i < values.length; i++) {
    		queue.insert(values[i], values[i]);
    	}
    	
    	@SuppressWarnings("unchecked")
		V[] arr = (V[])(new Object[queue.size()]);
    	for (int i = 0; queue.size() > 0; i++) {
    		arr[i] = queue.removeMin().getValue();
    	}
    	return arr;
    }
    
    public static <V> V[] heapsort(V[] values, Comparator<V> comparator) {
    	HeapPriorityQueue<V, V> queue = new HeapPriorityQueue<V, V>(values, values, comparator);
    	@SuppressWarnings("unchecked")
		V[] arr = (V[])(new Object[queue.size()]);
    	for (int i = 0; queue.size() > 0; i++) {
    		arr[i] = queue.removeMin().getValue();
    	}
    	return arr;
    }
    
    public static <V> V[] heapsort(V[] values) {
    	HeapPriorityQueue<V, V> queue = new HeapPriorityQueue<V, V>(values, values);
    	@SuppressWarnings("unchecked")
		V[] arr = (V[])(new Object[queue.size()]);
    	for (int i = 0; queue.size() > 0; i++) {
    		arr[i] = queue.removeMin().getValue();
    	}
    	return arr;
    }

    public static void main(String[] args) {
        Integer[] rands = new Integer[]{35, 26, 15, 24, 33, 4, 12, 1, 23, 21, 2, 5};
        HeapPriorityQueue<Integer, Integer> pq = new HeapPriorityQueue<>(rands, rands);

        System.out.println("elements: " + Arrays.toString(rands));
        System.out.println("after adding elements: " + pq);

        System.out.println("min element: " + pq.min());

        pq.removeMin();
        System.out.println("after removeMin: " + pq);
        // [             1,
        //        2,            4,
        //   23,     21,      5, 12,
        // 24, 26, 35, 33, 15]
        
        
        System.out.println("Heapsort: " + Arrays.toString(heapsort(rands)));
        System.out.println("PQSort: " + Arrays.toString(PQSort(rands)));
    }
    
    
}
