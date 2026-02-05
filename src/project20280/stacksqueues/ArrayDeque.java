package project20280.stacksqueues;

import project20280.interfaces.Deque;

public class ArrayDeque<E> implements Deque<E> {

	public static final int CAPACITY = 100;
	
	private E[] data;
	private int front;
	private int back;
	
	@SuppressWarnings("unchecked")
	public ArrayDeque(int capacity) {
		data = (E[]) new Object[capacity];
	}
	
	public ArrayDeque() {
		this(CAPACITY);
	}
	
	public void addFirst(E e) {
		
	}
	
	public void addLast(E e) {
		
	}
	
	public E removeFirst() {
		
	}
	
	public E removeLast() {
		
	}
	
	public E first() {
		
	}
	
	public E last() {
		
	}
	
	public E size() {
		return front - back;
	}
	
	public E isEmpty() {
		
	}
	
}
