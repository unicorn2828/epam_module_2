package com.epam.esm.repository;

import com.epam.esm.model.Certificate;
import com.epam.esm.model.Certificates;

import java.util.List;

public interface ICertificateRepository {

    boolean delete(long id);

    Certificate findById(long id);

    Certificates findAllCertificates();

    Certificates findAllCertificatesIncludeTags();

    List<Certificate> findCertificatesByTagId(long id);

    Certificate updateCertificateWithoutTags(Certificate certificate);

    Certificate updateCertificateWithTags(Certificate certificate);

    Certificate createCertificateWithTags(Certificate certificate);

    Certificate createCertificateWithoutTags(Certificate certificate);

    void setTagRepository(ITagRepository tagRepository);
}