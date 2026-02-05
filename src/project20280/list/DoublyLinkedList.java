package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class DoublyLinkedList<E> implements List<E>, Iterable<E> {

    private static class Node<E> {
        private final E data;
        private Node<E> next;
        private Node<E> prev;

        public Node(E e, Node<E> p, Node<E> n) {
            data = e;
            prev = p;
            next = n;
        }

        public E getData() {
            return data;
        }

        public Node<E> getNext() {
            return next;
        }

        public Node<E> getPrev() {
            return prev;
        }

    }

    private final Node<E> head;
    private final Node<E> tail;
    private int size;

    public DoublyLinkedList() {
        head = new Node<E>(null, null, null);
        tail = new Node<E>(null, head, null);
        head.next = tail;
        this.size = 0;
    }

    private void addBetween(E e, Node<E> pred, Node<E> succ) {
        Node<E> newNode = new Node<E>(e, pred, succ);
        pred.next = newNode;
        succ.prev = newNode;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public E get(int i) {
        if (this.size() <= i) return null;
        Node<E> curNode = this.head.getNext();
        for (int j = 0; j < i; j++) {
        	curNode = curNode.getNext();
        }
        return curNode.getData();
    }

    @Override
    public void add(int i, E e) {
    	if (this.size() < i) return;
    	else if (i == 0) this.addFirst(e);
    	else {
	        Node<E> curNode = this.head.getNext();
	        for (int j = 1; j < i; j++) {
	        	curNode = curNode.getNext();
	        }
	        this.addBetween(e, curNode, curNode.getNext());
	        this.size++;
    	}
    }

    @Override
    public E remove(int i) {
    	if (this.size() <= i) return null;
    	else if (i == 0) {
    		return this.removeFirst();
    	}
    	else {
	        Node<E> curNode = this.head.getNext();
	        for (int j = 1; j < i; j++) {
	        	curNode = curNode.getNext();
	        }
	        Node<E> target = curNode.getNext(),
	        		prec = target.getNext();
	        curNode.next = prec;
	        prec.prev = curNode;
	        this.size--;
	        return target.getData();
    	}
    }

    private class DoublyLinkedListIterator implements Iterator<E> {
        Node<E> curr = head.next;

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
        return new DoublyLinkedListIterator();
    }

    private E remove(Node<E> n) {
    	Node<E> curNode;
        for (curNode = this.head; curNode.getNext() != n; curNode = curNode.getNext()) {
        	if (curNode.getNext() == null) return null;
        }
        Node<E> postNode = n.getNext();
        curNode.next = postNode;
        postNode.prev = curNode;
        return n.getData();
    }

    public E first() {
        if (isEmpty()) {
            return null;
        }
        return head.next.getData();
    }
    
    public E last() {
    	if (this.tail.getPrev() == this.head) return null;
    	else return this.tail.getPrev().getData();
    }
	
    @Override
    public E removeFirst() {
    	if (this.head.getNext() == this.tail) return null;
        Node<E> target = this.head.getNext(),
        		postNode = target.getNext();
        this.head.next = postNode;
        postNode.prev = this.head;
        this.size--;
        return target.getData();
    }

    @Override
    public E removeLast() {
        if (this.tail.getPrev() == this.head) return null;
        Node<E> target = this.tail.getPrev(),
        		precNode = target.getPrev();
        this.tail.prev = precNode;
        precNode.next = this.tail;
        this.size--;
        return target.getData();
    }

    @Override
    public void addLast(E e) {
        this.addBetween(e, this.tail.getPrev(), this.tail);
        this.size++;
    }

    @Override
    public void addFirst(E e) {
    	this.addBetween(e, this.head, this.head.getNext());
    	this.size++;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = head.next;
        while (curr != tail) {
            sb.append(curr.data);
            curr = curr.next;
            if (curr != tail) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> ll = new DoublyLinkedList<Integer>();
        ll.addFirst(0);
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addLast(-1);
        System.out.println(ll);

        ll.removeFirst();
        System.out.println(ll);

        ll.removeLast();
        System.out.println(ll);

        for (Integer e : ll) {
            System.out.println("value: " + e);
        }
    }
}