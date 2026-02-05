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
		front = capacity/2;
		back = front;
	}
	
	public ArrayDeque() {
		this(CAPACITY);
	}
	
	public void addFirst(E e) {
		if (front == 0) throw new IllegalStateException("Front of array reached.");
		else {
			if (front == back) back--;
			data[front--] = e;
		}
	}
	
	public void addLast(E e) {
		if (back == data.length) throw new IllegalStateException("Back of array reached.");
		else {
			if (back == front) front++;
			data[back++] = e;
		}
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
