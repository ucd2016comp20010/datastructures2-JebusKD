package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class CircularlyLinkedList<E> implements List<E>, Iterable<E> {

    private class Node<T> {
        private final T data;
        private Node<T> next;

        public Node(T e, Node<T> n) {
            data = e;
            next = n;
        }

        public T getData() {
            return data;
        }

        public void setNext(Node<T> n) {
            next = n;
        }

        public Node<T> getNext() {
            return next;
        }
    }

    private Node<E> tail = null;
    private int size = 0;

    public CircularlyLinkedList() {

    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int i) {
        if (this.isEmpty()) return null;
        
        Node<E> curNode = this.tail;
        for (int j = 0; j < i; j++) {
        	curNode = curNode.getNext();
        }
        return curNode.getData();
    }

    /**
     * Inserts the given element at the specified index of the list, shifting all
     * subsequent elements in the list one position further to make room.
     *
     * @param i the index at which the new element should be stored
     * @param e the new element to be stored
     */
    @Override
    public void add(int i, E e) {
        if (this.isEmpty()) {
        	Node<E> selfReferenceNode = new Node<E>(e, null);
        	selfReferenceNode.setNext(selfReferenceNode);
        	this.tail = selfReferenceNode;
        }
        else {
        	Node<E> curNode = this.tail;
        	int targetIndex;
        	if (i == 0) targetIndex = this.size;
        	else targetIndex = i % this.size;
        	for (int j = 1; j < i % this.size; j++) {
        		curNode = curNode.getNext();
        	}
        	Node<E> newNode = new Node<E>(e, curNode.getNext());
        	curNode.setNext(newNode);
        }
        size++;
    }

    @Override
    public E remove(int i) {
    	if (this.isEmpty()) return null;
    	else if (this.size == 1) {
    		Node<E> target = this.tail;
    		this.tail = null;
    		size--;
    		return target.getData();
    	}
        else {
        	Node<E> curNode = this.tail;
        	int targetIndex;
        	if (i == 0) targetIndex = this.size;
        	else targetIndex = i % this.size;
        	for (int j = 1; j < targetIndex; j++) {
        		curNode = curNode.getNext();
        	}
        	Node<E> target = curNode.getNext(),
        			postNode = target.getNext();
        	curNode.setNext(postNode);
        	size--;
        	return target.getData();
        }
    }

    public void rotate() {
        if (!this.isEmpty()) this.tail = this.tail.getNext();
    }

    private class CircularlyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) tail;

        @Override
        public boolean hasNext() {
            return curr != tail;
        }

        @Override
        public E next() {
            E res = curr.data;
            curr = curr.next;
            return res;
        }

    }

    @Override
    public Iterator<E> iterator() {
        return new CircularlyLinkedListIterator<E>();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E removeFirst() {
        if (this.isEmpty()) return null;
        else if (this.size == 1) {
        	size--;
        	Node<E> target = this.tail;
        	this.tail = null;
        	return target.getData();
        }
        else {
        	curNode.setNext(target.getNext());
        	this.tail.setNext(target.getNext());
        	size--;
        	return target.getData();
        }
    }

    @Override
    public E removeLast() {
        if (this.isEmpty()) return null;
        else if (this.size == 1) {
        	size--;
        	Node<E> target = this.tail;
        	this.tail = null;
        	return target.getData();
        }
        else {
        	Node<E> curNode = this.tail,
        			target = curNode.getNext();
        	while (target.getNext() != this.tail) {
        		curNode = target;
        		target = target.getNext();
        	}
        	size--;
        	curNode.setNext(this.tail);
        	return target.getData();
        			
        }
        	
    }

    @Override
    public void addFirst(E e) {
        if (this.isEmpty()) {
        	Node<E> selfReferentialNode = new Node<E>(e, null);
        	selfReferentialNode.setNext(selfReferentialNode);
        	this.tail = selfReferentialNode;
        }
        else {
        	Node<E> newNode = new Node<E>(e, this.tail.getNext());
        	this.tail.setNext(newNode);
        }
        size++;
    }

    @Override
    public void addLast(E e) {
        if (this.isEmpty()) {
        	Node<E> selfReferentialNode = new Node<E>(e, null);
        	selfReferentialNode.setNext(selfReferentialNode);
        	this.tail = selfReferentialNode;
        }
        else {
        	Node<E> newNode = new Node<E>(e, this.tail.getNext());
        	this.tail.setNext(newNode);
        	this.tail = newNode;
        }
        size++;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = tail;
        do {
            curr = curr.next;
            sb.append(curr.data);
            if (curr != tail) {
                sb.append(", ");
            }
        } while (curr != tail);
        sb.append("]");
        return sb.toString();
    }


    public static void main(String[] args) {
        CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<Integer>();
        for (int i = 10; i < 20; ++i) {
            ll.addLast(i);
        }

        System.out.println(ll);

        ll.removeFirst();
        System.out.println(ll);

        ll.removeLast();
        System.out.println(ll);

        ll.rotate();
        System.out.println(ll);

        ll.removeFirst();
        ll.rotate();
        System.out.println(ll);

        ll.removeLast();
        ll.rotate();
        System.out.println(ll);

        for (Integer e : ll) {
            System.out.println("value: " + e);
        }

    }
}
