package com.epam.esm.exception;

import com.epam.esm.exception.data.ErrorCode;
import com.epam.esm.exception.data.ErrorMessage;

public enum ServiceErrorCode {
    TAG_IS_NULL(ErrorCode.ERROR_000001, ErrorMessage.ERROR_000001),
    TAG_ID_LESS_THAN_0(ErrorCode.ERROR_000002, ErrorMessage.ERROR_000002),
    TAG_NAME_IS_NULL(ErrorCode.ERROR_000003, ErrorMessage.ERROR_000003),
    TAG_NAME_IS_EMPTY(ErrorCode.ERROR_000004, ErrorMessage.ERROR_000004),
    TAG_NAME_MORE_30(ErrorCode.ERROR_000005, ErrorMessage.ERROR_000005),
    TAG_WITH_SUCH_ID_NOT_EXIST(ErrorCode.ERROR_000007, ErrorMessage.ERROR_000007),
    TAG_WITH_SUCH_NAME_NOT_EXISTS(ErrorCode.ERROR_000008, ErrorMessage.ERROR_000008),

    CERTIFICATE_NOT_EXIST(ErrorCode.ERROR_000020, ErrorMessage.ERROR_000020),
    CERTIFICATE_IS_EMPTY(ErrorCode.ERROR_000021, ErrorMessage.ERROR_000021),
    CERTIFICATE_ID_LESS_THAN_0(ErrorCode.ERROR_000022, ErrorMessage.ERROR_000022),
    CERTIFICATE_NAME_IS_NULL(ErrorCode.ERROR_000024, ErrorMessage.ERROR_000024),
    CERTIFICATE_NAME_IS_EMPTY(ErrorCode.ERROR_000025, ErrorMessage.ERROR_000025),
    CERTIFICATE_NAME_MORE_30(ErrorCode.ERROR_000026, ErrorMessage.ERROR_000026),
    CERTIFICATE_DESCRIPTION_IS_NULL(ErrorCode.ERROR_000027, ErrorMessage.ERROR_000027),
    CERTIFICATE_DESCRIPTION_IS_EMPTY(ErrorCode.ERROR_000028, ErrorMessage.ERROR_000028),
    CERTIFICATE_DESCRIPTION_MORE_500(ErrorCode.ERROR_000029, ErrorMessage.ERROR_000029),
    CERTIFICATE_PRICE_LESS_THAN_0(ErrorCode.ERROR_000030, ErrorMessage.ERROR_000030),
    CERTIFICATE_DATE_ERROR(ErrorCode.ERROR_000031, ErrorMessage.ERROR_000031),
    CERTIFICATE_DATE_CREATION_AFTER_TODAY(ErrorCode.ERROR_000032, ErrorMessage.ERROR_000032),
    CERTIFICATE_PRICE_LONG_SO_MUCH(ErrorCode.ERROR_000033, ErrorMessage.ERROR_000033),
    DATE_MODIFICATION_AFTER_TODAY(ErrorCode.ERROR_000034, ErrorMessage.ERROR_000034),
    DATE_MODIFICATION_EARLIER_THAN_NOW(ErrorCode.ERROR_000035, ErrorMessage.ERROR_000035),
    DURATION_DAIS_LESS_THAN_1(ErrorCode.ERROR_000036, ErrorMessage.ERROR_000036),
    DURATIONS_MORE_THAN_365(ErrorCode.ERROR_000038, ErrorMessage.ERROR_000038);

    private String errorCode;
    private String errorMessage;

    ServiceErrorCode(String errorId, String errorMessage) {
        this.errorCode = "ErrorCode: "+ errorId;
        this.errorMessage = "ErrorMessage: " + errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
