package com.week05.post_9.exception.post;


import com.week05.post_9.exception.BaseException;
import com.week05.post_9.exception.BaseExceptionType;

public class PostException extends BaseException {

    private BaseExceptionType baseExceptionType;


    public PostException(BaseExceptionType baseExceptionType) {
        this.baseExceptionType = baseExceptionType;
    }

    @Override
    public BaseExceptionType getExceptionType() {
        return this.baseExceptionType;
    }
}