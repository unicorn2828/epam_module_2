package com.epam.esm.validation;

import com.epam.esm.dto.CertificateDto;
import com.epam.esm.exception.ServiceErrorCode;
import com.epam.esm.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CertificateValidator {
    static final Logger logger = LogManager.getLogger();
    private static ServiceErrorCode errorCode = null;

    public static boolean isCertificate(CertificateDto certificateDto) {
        if (certificateDto == null) {
            errorCode = ServiceErrorCode.CERTIFICATE_NOT_EXIST;
        } else if (certificateDto.getCertificateName() == null || certificateDto.getCertificateName().isEmpty()) {
            errorCode = ServiceErrorCode.CERTIFICATE_NAME_IS_EMPTY;
        } else if (certificateDto.getCertificateName().length() > 30) {
            errorCode = ServiceErrorCode.CERTIFICATE_NAME_MORE_30;
        } else if (certificateDto.getDescription() == null || certificateDto.getDescription().isEmpty()) {
            errorCode = ServiceErrorCode.CERTIFICATE_DESCRIPTION_IS_EMPTY;
        } else if (certificateDto.getDescription().length() > 500) {
            errorCode = ServiceErrorCode.CERTIFICATE_DESCRIPTION_MORE_500;
        } else if (certificateDto.getPrice().compareTo(new BigDecimal(0)) < 0) {
            errorCode = ServiceErrorCode.CERTIFICATE_PRICE_LESS_THAN_0;
        } else if (certificateDto.getCreationDate().isAfter(LocalDate.now()) ||
                certificateDto.getCreationDate().isAfter(certificateDto.getModificationDate())) {
            errorCode = ServiceErrorCode.CERTIFICATE_DATE_CREATION_AFTER_TODAY;
        } else if (certificateDto.getModificationDate().isAfter(LocalDate.now())) {
            errorCode = ServiceErrorCode.DATE_MODIFICATION_AFTER_TODAY;
        } else if (certificateDto.getDuration() > 365) {
            errorCode = ServiceErrorCode.DURATIONS_MORE_THAN_365;
        } else if (certificateDto.getDuration() < 1) {
            errorCode = ServiceErrorCode.DURATION_DAIS_LESS_THAN_1;
        } else if (certificateDto.getTags() != null) {
            if (!certificateDto.getTags().stream().allMatch(TagValidator::isTag)) {
                errorCode = ServiceErrorCode.TAG_IS_NULL;
            }
        }
        if (errorCode != null) {
            logger.error(errorCode.getErrorCode() + ":" + errorCode.getErrorMessage());
            throw new ServiceException(errorCode);
        }
        return true;
    }

    public static boolean isId(Long id) throws ServiceException{
        if (id == null || id < 0) {
            errorCode = ServiceErrorCode.CERTIFICATE_ID_LESS_THAN_0;
            logger.error(errorCode.getErrorCode() + ":" + errorCode.getErrorMessage());
            throw new ServiceException(errorCode);
        }

        return true;
    }
}