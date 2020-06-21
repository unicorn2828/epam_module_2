package com.epam.esm.validation;

import com.epam.esm.dto.TagDto;
import com.epam.esm.exception.ServiceErrorCode;
import com.epam.esm.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TagValidator {
    static final Logger logger = LogManager.getLogger();
    private static ServiceErrorCode errorCode = null;

    public static boolean isTag(TagDto tagDto) {
        if (tagDto == null) {
            errorCode = ServiceErrorCode.TAG_WITH_SUCH_ID_NOT_EXIST;
            logger.error(errorCode.getErrorCode() + ":" + errorCode.getErrorMessage());
            throw new ServiceException(errorCode);
        }
        isName(tagDto.getName());
        return true;
    }

    public static boolean isTagWithName(TagDto tagDto) {
        if (tagDto == null) {
            errorCode = ServiceErrorCode.TAG_WITH_SUCH_NAME_NOT_EXISTS;
            logger.error(errorCode.getErrorCode() + ":" + errorCode.getErrorMessage());
            throw new ServiceException(errorCode);
        }
        isName(tagDto.getName());
        return true;
    }

    public static boolean isName(String tagName) {
        if (tagName == null) {
            errorCode = ServiceErrorCode.TAG_NAME_IS_NULL;
        } else if (tagName.isEmpty()) {
            errorCode = ServiceErrorCode.TAG_NAME_IS_EMPTY;
        } else if (tagName.length() > 30) {
            errorCode = ServiceErrorCode.TAG_NAME_MORE_30;
        }
        if (errorCode != null) {
            logger.error(errorCode.getErrorCode() + ":" + errorCode.getErrorMessage());
            throw new ServiceException(errorCode);
        }
        return true;
    }

    public static boolean isId(Long id) {
        if (id == null || id < 0) {
            errorCode = ServiceErrorCode.TAG_ID_LESS_THAN_0;
            logger.error(errorCode.getErrorCode() + ":" + errorCode.getErrorMessage());
            throw new ServiceException(errorCode);
        }
        return true;
    }
}