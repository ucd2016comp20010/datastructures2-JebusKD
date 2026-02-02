package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class SinglyLinkedList<E> implements List<E>, Iterable<E>, Cloneable {

    private static class Node<E> {

        private final E element;            // reference to the element stored at this node

        /**
         * A reference to the subsequent node in the list
         */
        private Node<E> next;         // reference to the subsequent node in the list

        /**
         * Creates a node with the given element and next node.
         *
         * @param e the element to be stored
         * @param n reference to a node that should follow the new node
         */
        public Node(E e, Node<E> n) {
            this.element = e;
            this.next = n;
        }

        // Accessor methods

        /**
         * Returns the element stored at the node.
         *
         * @return the element stored at the node
         */
        public E getElement() {
            return this.element;
        }

        /**
         * Returns the node that follows this one (or null if no such node).
         *
         * @return the following node
         */
        public Node<E> getNext() {
            return this.next;
        }

        // Modifier methods

        /**
         * Sets the node's next reference to point to Node n.
         *
         * @param n the node that should follow this one
         */
        public void setNext(Node<E> n) {
            this.next = n;
        }
    } //----------- end of nested Node class -----------

    /**
     * The head node of the list
     */
    private Node<E> head = null;               // head node of the list (or null if empty)


    /**
     * Number of nodes in the list
     */
    private int size = 0;                      // number of nodes in the list

    public SinglyLinkedList() {
    }              // constructs an initially empty list

    //@Override
    public int size() {
        int i = 0;
        for (Node<E> curNode = this.head; curNode != null; i++) {
        	curNode = curNode.getNext();
        }
        return i;
    }

    //@Override
    public boolean isEmpty() {
        return this.head == null;
    }

    @Override
    public E get(int position) {
    	Node<E> curNode = head;
        for (int i = 0; i < position; i++) {
        	if (curNode == null) return null;
        	curNode = curNode.getNext();
        }
        return curNode.getElement();
    }

    @Override
    public void add(int position, E e) {
    	int size = this.size();
        if (position < size) {
        	if (position == 0) this.addFirst(e);
        	else if (position+1 == size) this.addLast(e);
        	else {
        		Node<E> curNode = this.head;
        		for (int i = 1; i < position; i++) {
        			curNode = curNode.getNext();
        		}
        		Node<E> postNode = curNode.getNext();
        		curNode.setNext(new Node<E>(e, postNode));
        	}
        }
    }


    @Override
    public void addFirst(E e) {
        Node<E> secondNode = this.head;
        this.head = new Node<E>(e, secondNode);
    }

    @Override
    public void addLast(E e) {
    	if (this.head == null) {
    		this.head = new Node<E>(e, null);
    	}
    	else {
    		Node<E> curNode = this.head;
    		while (curNode.getNext() != null) {
    			curNode = curNode.getNext();
    		}
    		curNode.setNext(new Node<E>(e, null));
    	}
    }

    @Override
    public E remove(int position) {
    	int size = this.size();
        if (position >= size) return null;
        else if (position == 0) return this.removeFirst();
        else if (position + 1 == size) return this.removeLast();
        else {
        	Node<E> curNode = this.head;;
        	for (int i = 1; i < position; i++) {
        		curNode = curNode.getNext();
        	}
        	Node<E> target = curNode.getNext(),
        			replacement = target.getNext();
        	curNode.setNext(replacement);
        	return target.getElement();
        }
    }

    @Override
    public E removeFirst() {
        if (this.head == null) return null;
        Node<E> oldHead = this.head,
        		newHead = oldHead.getNext();
        this.head = newHead;
        return oldHead.getElement();
        
    }

    @Override
    public E removeLast() {
        if (this.head == null) return null;
        else if (this.head.getNext() == null) {
        	Node<E> oldHead = this.head;
        	this.head = null;
        	return oldHead.getElement();
        }
        Node<E> curNode = this.head;
        while (curNode.getNext().getNext() != null) {
        	curNode = curNode.getNext();
        }
        Node<E> target = curNode.getNext();
        curNode.setNext(null);
        return target.getElement();
    }
    
    public void reverse() {
    	Node<E> curNode = this.head,
    			prevNode = null,
    			nextNode;
    	while (curNode != null) {
    		nextNode = curNode.getNext();
    		curNode.setNext(prevNode);
    		prevNode = curNode;
    		curNode = nextNode;
    	}
    	this.head = prevNode;
    }
    
    /* Creates a copy of the list, each element by reference, though */
    @Override
    public SinglyLinkedList<E> clone() {
    	SinglyLinkedList<E> clonedList = new SinglyLinkedList<E>();
    	
    	if (this.head != null) {
    		
    		clonedList.head = new Node<E>(this.head.getElement(), null);
    	
	    	Node<E> originalCurNode = this.head.getNext(),
	    			newNode,
	    			prevNode = clonedList.head;
	    	while (originalCurNode != null) {
	    		newNode = new Node<E>(originalCurNode.getElement(), null);
	    		prevNode.setNext(newNode);
	    		prevNode = newNode;
	    		originalCurNode = originalCurNode.getNext();
	    	}
    	}
    	else {
    		clonedList.head = null;
    	}
    	
    	return clonedList;
    }

    //@Override
    public Iterator<E> iterator() {
        return new SinglyLinkedListIterator<E>();
    }

    private class SinglyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) head;

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public E next() {
            E res = curr.getElement();
            curr = curr.next;
            return res;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = head;
        while (curr != null) {
            sb.append(curr.getElement());
            if (curr.getNext() != null)
                sb.append(", ");
            curr = curr.getNext();
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<Integer>();
        System.out.println("ll " + ll + " isEmpty: " + ll.isEmpty());
        //LinkedList<Integer> ll = new LinkedList<Integer>();

        ll.addFirst(0);
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addFirst(3);
        ll.addFirst(4);
        ll.addLast(-1);
        //ll.removeLast();
        //ll.removeFirst();
        //System.out.println("I accept your apology");
        //ll.add(3, 2);
        System.out.println(ll);
        ll.remove(5);
        System.out.println(ll);
        
        ll.reverse();
        System.out.println(ll);
        
        SinglyLinkedList<Integer> ll2 = ll.clone();
        System.out.println(ll2);

    }
}
