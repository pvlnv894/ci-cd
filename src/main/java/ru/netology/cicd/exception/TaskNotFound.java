package ru.netology.cicd.exception;

public class TaskNotFound extends RuntimeException{
    public TaskNotFound(String msg) {
        super(msg);
    }
}