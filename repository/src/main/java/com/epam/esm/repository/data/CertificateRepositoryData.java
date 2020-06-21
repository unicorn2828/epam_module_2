package com.epam.esm.repository.data;

public class CertificateRepositoryData {
    public static final String ID = "id";
    public static final String SQL_DELETE_TAG_FROM_TAG_CERTIFICATE = "DELETE FROM tag_certificate WHERE tag_id = ?";
    public static final String SQL_FIND_ALL_CERTIFICATES =
            "SELECT * FROM certificate ORDER BY id";
    public static final String SQL_FIND_CERTIFICATE_BY_ID =
            "SELECT * FROM certificate WHERE id = ?";
    public static final String SQL_UPDATE_CERTIFICATE =
            "UPDATE certificate SET certificate_name = ?, description = ?, price = ?, creationdate = ?, modificationdate = ?, duration = ? WHERE id =  ?";
    public static final String SQL_CREATE_CERTIFICATE =
            "INSERT INTO certificate (certificate_name, description, price, creationdate, modificationdate, duration) VALUES (?, ?, ?, ?, ?, ?)";
    public static final String SQL_DELETE_CERTIFICATE =
            "DELETE FROM certificate WHERE id = ?";
    public static final String SQL_FIND_CERTIFICATES_BY_TAG_ID =
            "SELECT * FROM certificate INNER JOIN tag_certificate ON certificate.id = certificate_id WHERE tag_id = :tag_id";
    public static final String TAG_ID = "tag_id";
    public static final String ADD_TAG_CERTIFICATE = "INSERT INTO tag_certificate (tag_id, certificate_id) VALUES (?, ?)  ON CONFLICT (tag_id, certificate_id) DO NOTHING";
}
