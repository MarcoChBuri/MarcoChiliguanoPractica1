package com.example.controls.tda.Stack;


import com.example.controls.exception.ListEmptyException;
import com.example.controls.tda.list.LinkedList;

public class Stack<E> {
    private LinkedList<E> list;

    public Stack() {
        this.list = new LinkedList<>();
    }

    public Boolean isEmpty() {
        return this.list.isEmpty();
    }

    public Integer getSize() {
        return this.list.getSize();
    }

    public void push(E info) {
        this.list.add(info);
    }

    public E pop() throws ListEmptyException {
        if (isEmpty()) {
            throw new ListEmptyException("La pila esta vacia");
        } else {
            E info = this.list.getLast();
            this.list.deleteLast();
            return info;
        }
    }

    public E peek() throws ListEmptyException {
        if (isEmpty()) {
            throw new ListEmptyException("La pila esta vacia");
        } else {
            return this.list.getLast();
        }
    }
}