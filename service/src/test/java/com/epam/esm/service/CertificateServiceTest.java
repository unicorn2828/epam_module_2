package com.epam.esm.service;

import com.epam.esm.dto.CertificateDto;
import com.epam.esm.dto.CertificatesDto;
import com.epam.esm.service.impl.CertificateService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class CertificateServiceTest {
    private CertificateService service = Mockito.mock(CertificateService.class);
    private static int TEST_CERTIFICATE_ID = 1;

    @Test
    void findCertificateById() {
        Mockito.when(service.findCertificateById(TEST_CERTIFICATE_ID)).thenReturn(new CertificateDto());
        CertificateDto actual = service.findCertificateById(TEST_CERTIFICATE_ID);
        CertificateDto expected = new CertificateDto();
        Assertions.assertEquals(actual, expected);
    }

    @Test
    void findAllCertificates() {
        Mockito.when(service.findAllCertificates()).thenReturn(new CertificatesDto());
        CertificatesDto actual = service.findAllCertificates();
        CertificatesDto expected = new CertificatesDto();
        Assertions.assertEquals(actual, expected);
    }

    @Test
    void removeCertificateById() {
    }

    @Test
    void saveCertificate() {
    }

    @Test
    void updateCertificate() {
    }
}