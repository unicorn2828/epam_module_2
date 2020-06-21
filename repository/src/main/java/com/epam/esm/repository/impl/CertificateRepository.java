package com.epam.esm.repository.impl;

import com.epam.esm.mapper.CertificateRowMapper;
import com.epam.esm.model.Certificate;
import com.epam.esm.model.Certificates;
import com.epam.esm.model.Tag;
import com.epam.esm.repository.ICertificateRepository;
import com.epam.esm.repository.ITagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;

import static com.epam.esm.repository.data.CertificateRepositoryData.*;

@Repository("certificateRepository")
@RequiredArgsConstructor
public class CertificateRepository implements ICertificateRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final CertificateRowMapper mapper;
    private final JdbcTemplate jdbcTemplate;
    private final Certificates certificates;
    private ITagRepository tagRepository;
    private final KeyHolder keyHolder;

    @Override
    public Certificate findById(long id) {
        try {
            Certificate certificate = jdbcTemplate.queryForObject(SQL_FIND_CERTIFICATE_BY_ID,
                    new Object[]{id}, mapper);
            certificate.setTags(tagRepository.findTagsByCertificateId(certificate.getId()));
            return certificate;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Certificates findAllCertificates() {
        List<Certificate> certificateList = jdbcTemplate.query(SQL_FIND_ALL_CERTIFICATES, mapper);
        certificates.setCertificates(certificateList);
        return certificates;
    }

    @Override
    public Certificates findAllCertificatesIncludeTags() {
        List<Certificate> certificateList = jdbcTemplate.query(SQL_FIND_ALL_CERTIFICATES, mapper);
        certificateList.forEach(certificate -> certificate.setTags(tagRepository.
                findTagsByCertificateId(certificate.getId())));
        certificates.setCertificates(certificateList);
        return certificates;
    }

    @Override
    public Certificate createCertificateWithoutTags(Certificate certificate) {
        long certificateId = save(certificate);
        return findById(certificateId);
    }

    @Override
    @Transactional
    public Certificate createCertificateWithTags(Certificate certificate) {
        long certificateId = save(certificate);
        for (Tag tag : certificate.getTags()) {
            long tagId = tagRepository.create(tag).getId();
            jdbcTemplate.update(ADD_TAG_CERTIFICATE, tagId, certificateId);
        }
        return findById(certificateId);
    }

    @Override
    public boolean delete(long id) {
        boolean status = false;
        if (findById(id) != null) {
            jdbcTemplate.update(SQL_DELETE_CERTIFICATE, id);
            status = true;
        }
        return status;
    }

    @Override
    public List<Certificate> findCertificatesByTagId(long id) {
        SqlParameterSource params = new MapSqlParameterSource(TAG_ID, id);
        return namedParameterJdbcTemplate.query(SQL_FIND_CERTIFICATES_BY_TAG_ID, params, mapper);
    }

    @Override
    @Transactional
    public Certificate updateCertificateWithTags(Certificate certificate) {
        Certificate oldCertificate = findById(certificate.getId());
        if (oldCertificate == null) {
            return null;
        }
        oldCertificate.getTags().forEach(tag -> jdbcTemplate.update(SQL_DELETE_TAG_FROM_TAG_CERTIFICATE, tag.getId()));
        jdbcTemplate.update(SQL_UPDATE_CERTIFICATE,
                certificate.getCertificateName(),
                certificate.getDescription(),
                certificate.getPrice(),
                certificate.getCreationDate(),
                certificate.getModificationDate(),
                certificate.getDuration(),
                certificate.getId());
        long certificateId = certificate.getId();
        for (Tag tag : certificate.getTags()) {
            long tagId = tagRepository.create(tag).getId();
            jdbcTemplate.update(ADD_TAG_CERTIFICATE, tagId, certificateId);
        }
        return findById(certificate.getId());
    }

    @Override
    @Transactional
    public Certificate updateCertificateWithoutTags(Certificate certificate) {
        Certificate oldCertificate = findById(certificate.getId());
        if (oldCertificate == null) {
            return null;
        }
        oldCertificate.getTags().forEach(tag -> tagRepository.delete(tag.getId()));
        jdbcTemplate.update(SQL_UPDATE_CERTIFICATE,
                certificate.getCertificateName(),
                certificate.getDescription(),
                certificate.getPrice(),
                certificate.getCreationDate(),
                certificate.getModificationDate(),
                certificate.getDuration(),
                certificate.getId());
        return findById(certificate.getId());
    }

    @Override
    public void setTagRepository(ITagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    private long save(Certificate certificate) {
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(SQL_CREATE_CERTIFICATE, new String[]{ID});
            ps.setString(1, certificate.getCertificateName());
            ps.setString(2, certificate.getDescription());
            ps.setBigDecimal(3, certificate.getPrice());
            ps.setTimestamp(4, Timestamp.valueOf(certificate.getCreationDate().atStartOfDay()));
            ps.setTimestamp(5, Timestamp.valueOf(certificate.getModificationDate().atStartOfDay()));
            ps.setInt(6, certificate.getDuration());
            return ps;
        }, keyHolder);
        long certificateId = (long) keyHolder.getKey();
        return certificateId;
    }
}