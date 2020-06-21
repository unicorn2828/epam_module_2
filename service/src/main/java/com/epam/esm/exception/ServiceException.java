package com.epam.esm.exception;

public class ServiceException extends ServiceAbstractException {
    protected String errorCode;
    protected String errorMessage;

    public ServiceException() {
    }

    public ServiceException(final Throwable e) {
        super(e);
    }

    public ServiceException(final ServiceErrorCode errorCode, final Throwable e) {
        super(errorCode.getErrorCode(), e);
    }

    public ServiceException(final ServiceErrorCode errorCode) {
        super(errorCode.getErrorCode(), errorCode.getErrorMessage());
        this.errorMessage = errorCode.getErrorMessage();
        this.errorCode = errorCode.getErrorCode();
    }
}