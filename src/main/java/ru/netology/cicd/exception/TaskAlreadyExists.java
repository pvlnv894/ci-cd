package ru.netology.cicd.exception;

public class TaskAlreadyExists extends RuntimeException{
    public TaskAlreadyExists(String msg) {
        super(msg);
    }
}