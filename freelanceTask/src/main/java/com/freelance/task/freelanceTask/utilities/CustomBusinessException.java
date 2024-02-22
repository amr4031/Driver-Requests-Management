package com.freelance.task.freelanceTask.utilities;

import lombok.Getter;

public class CustomBusinessException extends RuntimeException {
    private final String message;
    @Getter
    private final Integer code;

    public CustomBusinessException(String message) {
        super(message);
        this.message = message;
        this.code = null; // Default code, or consider overloading the constructor to set it
    }

    public CustomBusinessException(String message, Integer code) {
        super(message);
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}