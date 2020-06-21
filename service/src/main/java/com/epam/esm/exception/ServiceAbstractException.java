package com.epam.esm.exception;

public abstract class ServiceAbstractException extends RuntimeException {
    protected String errorCode;
    protected String errorMessage;

    public ServiceAbstractException() {
        super();
    }

    public ServiceAbstractException(final Throwable e) {
        super(e);
    }

    public ServiceAbstractException(final String errorCode, final Throwable e) {
        super(errorCode, e);
    }

    public ServiceAbstractException(final String errorCode, final String errorMessage) {
        super(errorCode);
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
    public String getErrorMessage(){
        return errorMessage;
    }
}