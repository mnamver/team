package com.betbull.exception;

public class TeamException extends Exception {
    private String message;
    private int errorCode;

    public TeamException(String message) {
        super();
        this.message = message;
    }

    public TeamException(String message , int errorCode){
        super();
        this.message = message;
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
