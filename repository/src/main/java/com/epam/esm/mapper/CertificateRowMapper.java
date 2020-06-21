package com.epam.esm.mapper;

import com.epam.esm.model.Certificate;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class CertificateRowMapper implements RowMapper<Certificate> {
    private static final String CERTIFICATE_ID = "id";
    private static final String CERTIFICATE_NAME = "certificate_name";
    private static final String CERTIFICATE_PRICE = "price";
    private static final String CERTIFICATE_CREATION_DATE = "creationdate";
    private static final String CERTIFICATE_MODIFICATION_DATE = "modificationdate";
    private static final String CERTIFICATE_DESCRIPTION = "description";
    private static final String CERTIFICATE_DURATION = "duration";

    @Override
    public Certificate mapRow(ResultSet rs, int rowNum) throws SQLException {
        Certificate certificate = new Certificate();
        certificate.setId(rs.getLong(CERTIFICATE_ID));
        certificate.setCertificateName(rs.getString(CERTIFICATE_NAME));
        certificate.setPrice(rs.getBigDecimal(CERTIFICATE_PRICE));
        certificate.setCreationDate(rs.getTimestamp(CERTIFICATE_CREATION_DATE).toLocalDateTime().toLocalDate());
        certificate.setModificationDate(rs.getTimestamp(CERTIFICATE_MODIFICATION_DATE).toLocalDateTime().toLocalDate());
        certificate.setDescription(rs.getString(CERTIFICATE_DESCRIPTION));
        certificate.setDuration(rs.getInt(CERTIFICATE_DURATION));
        return certificate;
    }
}