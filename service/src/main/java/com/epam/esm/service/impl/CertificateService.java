package com.epam.esm.service.impl;

import com.epam.esm.dto.CertificateDto;
import com.epam.esm.dto.CertificatesDto;
import com.epam.esm.exception.ServiceErrorCode;
import com.epam.esm.exception.ServiceException;
import com.epam.esm.mapper.DtoMapper;
import com.epam.esm.model.Certificate;
import com.epam.esm.repository.ICertificateRepository;
import com.epam.esm.service.ICertificateService;
import com.epam.esm.validation.CertificateValidator;
import com.epam.esm.validation.TagValidator;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CertificateService implements ICertificateService {
    static final Logger logger = LogManager.getLogger();
    private final ICertificateRepository repository;
    private final DtoMapper dtoMapper;

    @Override
    public CertificateDto findCertificateById(long id) {
        CertificateValidator.isId(id);
        CertificateDto certificateDto = dtoMapper.toCertificateDto(repository.findById(id));
        CertificateValidator.isCertificate(certificateDto);
        return certificateDto;
    }

    @Override
    public CertificatesDto findAllCertificates() {
        CertificatesDto certificatesDto = new CertificatesDto();
        certificatesDto.setCertificates(dtoMapper.toCertificatesDto(repository.findAllCertificates()).getCertificates());
        return certificatesDto;
    }

    @Override
    public CertificatesDto findAllCertificatesIncludeTags() {
        CertificatesDto certificatesDto = new CertificatesDto();
        certificatesDto.setCertificates(dtoMapper.toCertificatesDto(repository.findAllCertificatesIncludeTags()).getCertificates());
        return certificatesDto;
    }

    @Override
    public void removeCertificateById(long id) {
        CertificateValidator.isId(id);
        boolean isDelete = repository.delete(id);
        if (!isDelete) {
            ServiceErrorCode errorCode = ServiceErrorCode.CERTIFICATE_NOT_EXIST;
            logger.error(errorCode.getErrorCode());
            throw new ServiceException(errorCode);
        }
        repository.delete(id);
    }

    @Override
    public CertificateDto saveCertificate(CertificateDto certificateDto) {
        certificateDto.setId(null);
        certificateDto.setCreationDate(LocalDate.now());
        certificateDto.setModificationDate(LocalDate.now());
        CertificateValidator.isCertificate(certificateDto);
        Certificate certificate;
        if (certificateDto.getTags() != null) {
            certificateDto.getTags().forEach(tag -> tag.setId(null));
            certificateDto.getTags().forEach(tag -> tag.setCertificates(null));
            certificateDto.getTags().forEach(TagValidator::isTag);
            certificate = repository.createCertificateWithTags(dtoMapper.toCertificate(certificateDto));
        } else {
            certificate = repository.createCertificateWithoutTags(dtoMapper.toCertificate(certificateDto));
        }
        CertificateDto createdCertificateDto = dtoMapper.toCertificateDto(certificate);
        CertificateValidator.isCertificate(createdCertificateDto);
        return createdCertificateDto;
    }

    @Override
    public CertificateDto updateCertificate(CertificateDto certificateDto) {
        CertificateValidator.isId(certificateDto.getId());
        certificateDto.setModificationDate(LocalDate.now());
        CertificateValidator.isCertificate(certificateDto);
        Certificate certificate;
        if (certificateDto.getTags() != null) {
            certificateDto.getTags().forEach(TagValidator::isTag);
            certificate = repository.updateCertificateWithTags(dtoMapper.toCertificate(certificateDto));
        } else {
            certificate = repository.updateCertificateWithoutTags(dtoMapper.toCertificate(certificateDto));
        }
        CertificateDto updatedCertificateDto = dtoMapper.toCertificateDto(certificate);
        CertificateValidator.isCertificate(updatedCertificateDto);
        return updatedCertificateDto;
    }
}