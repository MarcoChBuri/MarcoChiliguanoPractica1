package com.example.controls.exception;


public class ListEmptyException extends Exception {
    public ListEmptyException() {
    }
 
    public ListEmptyException(String msg) {
       super(msg);
    }
 }