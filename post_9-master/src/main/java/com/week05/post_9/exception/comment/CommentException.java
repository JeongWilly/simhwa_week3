package com.week05.post_9.exception.comment;

import com.week05.post_9.exception.BaseException;
import com.week05.post_9.exception.BaseExceptionType;

public class CommentException extends BaseException {

    private BaseExceptionType baseExceptionType;

    public CommentException(CommentExceptionType notPoundComment) {
        super();
    }

    @Override
    public BaseExceptionType getExceptionType() {
        return this.baseExceptionType;
    }
}