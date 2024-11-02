package com.example.controls.tda.Queue;
import com.example.controls.exception.ListEmptyException;
import com.example.controls.tda.list.LinkedList;
public class Queue<E> {
    private LinkedList<E> list;

    public Queue() {
        this.list = new LinkedList<>();
    }

    public Boolean isEmpty() {
        return this.list.isEmpty();
    }

    public Integer getSize() {
        return this.list.getSize();
    }

    public void enqueue(E info) {
        this.list.add(info);
    }

    public E dequeue() throws ListEmptyException {
        if (isEmpty()) {
            throw new ListEmptyException("La cola esta vacia");
        } else {
            E info = this.list.getFirst();
            this.list.deleteHeader();
            return info;
        }
    }

    public E peek() throws ListEmptyException {
        if (isEmpty()) {
            throw new ListEmptyException("La cola esta vacia");
        } else {
            return this.list.getFirst();
        }
    }
}