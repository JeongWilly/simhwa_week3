package com.week05.post_9.exception;


public abstract class BaseException extends RuntimeException{
    public abstract BaseExceptionType getExceptionType();
}