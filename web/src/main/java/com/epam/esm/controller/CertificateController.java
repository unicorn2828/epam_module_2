package com.epam.esm.controller;

import com.epam.esm.dto.CertificateDto;
import com.epam.esm.dto.CertificatesDto;
import com.epam.esm.service.ICertificateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/certificates", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class CertificateController {
    private final ICertificateService certificateService;

    /**
     * This method finds a certificate in database by id of the certificate
     *
     * @param id - id of certificate
     * @return {link} CertificateDTO
     */
    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CertificateDto findCertificateById(@PathVariable("id") final Long id) {
        return certificateService.findCertificateById(id);
    }

    /**
     * This method finds all certificates in database
     *
     * @return {link} CertificatesDTO
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CertificatesDto findAllCertificates(@RequestParam Map<String, String> allParams) {
        return certificateService.findAllCertificates();
    }

    /**
     * This method finds all certificates include tags in database
     *
     * @return {link} CertificatesDTO
     */
    @GetMapping(value = "/tags")
    @ResponseStatus(HttpStatus.OK)
    public CertificatesDto findAllCertificatesIncludeTags() {
        return certificateService.findAllCertificatesIncludeTags();
    }

    /**
     * This method removes a certificate from database by id of the certificate
     *
     * @param id - id of certificate
     * @return {link void}
     */
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void removeCertificateById(@PathVariable("id") final Long id) {
        certificateService.removeCertificateById(id);
    }

    /**
     * THis method creates a new certificate
     *
     * @param certificateDto - the new certificate to save
     * @return {link void}
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public CertificateDto createCertificate(@RequestBody final CertificateDto certificateDto) {
        return certificateService.saveCertificate(certificateDto);
    }

    /**
     * This method updates a certificate
     *
     * @param certificateDto - the certificate to update
     * @return {link void}
     */
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CertificateDto updateCertificate(@PathVariable("id") final Long id, @RequestBody(required = false) final CertificateDto certificateDto) {
        certificateDto.setId(id);
        return certificateService.updateCertificate(certificateDto);
    }
}