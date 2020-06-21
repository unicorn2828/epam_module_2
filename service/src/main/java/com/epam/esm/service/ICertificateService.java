package com.epam.esm.service;

import com.epam.esm.dto.CertificateDto;
import com.epam.esm.dto.CertificatesDto;

/**
 * The ICertificateService interface
 *
 * @author Vitaly Kononov
 */
public interface ICertificateService {

    /**
     * This method removes a certificate from database by id of the certificate
     *
     * @param id - id of certificate
     * @return {link void}
     */
    void removeCertificateById(long id);

    /**
     * This method finds all certificates in database
     *
     * @return {link} CertificatesDTO
     */
    CertificatesDto findAllCertificates();

    /**
     * This method finds all certificates include tags in database
     *
     * @return {link} CertificatesDTO
     */
    CertificatesDto findAllCertificatesIncludeTags();

    /**
     * This method finds a certificate in database by id of the certificate
     *
     * @param id - id of certificate
     * @return {link} CertificateDTO
     */
    CertificateDto findCertificateById(long id);

    /**
     * THis method creates a new certificate
     *
     * @param certificateDto - the new certificate to save
     * @return {link void}
     */
    CertificateDto saveCertificate(CertificateDto certificateDto);

    /**
     * This method updates a certificate
     *
     * @param certificateDto - the certificate to update
     * @return {link void}
     */
    CertificateDto updateCertificate(CertificateDto certificateDto);
}
