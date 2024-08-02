package org.acme.exception;

import java.util.Objects;

public class MyRuntimeException extends RuntimeException{
    private final long errorCode;
    private final String path;

    public MyRuntimeException(long errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.path = "";
    }

    public MyRuntimeException(String path, String message) {
        super(message);
        this.path = path;
        this.errorCode =999;
    }


    public long getErrorCode() {
        return this.errorCode;
    }


    public String getPath() {
        return path;
    }
}