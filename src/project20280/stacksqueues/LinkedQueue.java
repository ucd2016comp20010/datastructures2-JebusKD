package project20280.stacksqueues;

import project20280.interfaces.Queue;
import project20280.list.DoublyLinkedList;

public class LinkedQueue<E> implements Queue<E> {

    private DoublyLinkedList<E> ll;

    public static void main(String[] args) {
    }

    public LinkedQueue() {
        this.ll = new DoublyLinkedList<E>();
    }

    @Override
    public int size() {
        return ll.size();
    }

    @Override
    public boolean isEmpty() {
        return ll.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        ll.addLast(e);
    }

    @Override
    public E first() {
        // TODO
        return null;
    }

    @Override
    public E dequeue() {
        // TODO
        return null;
    }

    public String toString() {
        return ll.toString();
    }
}
