package com.epam.esm.validation;

import com.epam.esm.dto.CertificateDto;
import com.epam.esm.exception.ServiceException;
import org.junit.gen5.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.gen5.api.Assertions.assertTrue;

class CertificateValidatorTest {
    private static final String TEST = "test";

    @Test
    void isCertificate() {
        CertificateDto certificate = new CertificateDto();
        certificate.setCertificateName(TEST);
        certificate.setDescription(TEST);
        certificate.setPrice(new BigDecimal(0));
        certificate.setCreationDate(LocalDate.now());
        certificate.setModificationDate(LocalDate.now());
        certificate.setDuration(60);
        boolean actual = CertificateValidator.isCertificate(certificate);
        assertTrue(actual);
    }

    @Test
    void isCertificateNegativeTestOneFieldNull() {
        CertificateDto certificate = new CertificateDto();
        certificate.setCertificateName(null);
        certificate.setDescription(TEST);
        certificate.setPrice(new BigDecimal(0));
        certificate.setCreationDate(LocalDate.now());
        certificate.setModificationDate(LocalDate.now());
        certificate.setDuration(60);
        Assertions.assertThrows(ServiceException.class, () -> {
            CertificateValidator.isCertificate(certificate);
        });
    }

    @Test
    void isCertificateNegativeNull() {
        Assertions.assertThrows(ServiceException.class, () -> {
            CertificateValidator.isCertificate(null);
        });
    }
}