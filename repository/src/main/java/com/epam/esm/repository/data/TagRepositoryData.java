package com.epam.esm.repository.data;

public class TagRepositoryData {
    public static final String ID = "id";
    public static final String SQL_FIND_ALL_TAGS = "SELECT * FROM tag ORDER BY id";
    public static final String SQL_DELETE_TAG = "DELETE FROM tag WHERE id = ?";
    public static final String SQL_CREATE_TAG = "INSERT INTO tag (tag_name) VALUES (?)";
    public static final String SQL_FIND_TAG_BY_ID = "SELECT * FROM tag WHERE id = ?";
    public static final String SQL_FIND_TAG_BY_NAME = "SELECT * FROM tag WHERE tag_name = ?";
    public static final String SQL_FIND_TAGS_BY_CERTIFICATE_ID =
            "SELECT tag.id, tag.tag_name FROM tag INNER JOIN tag_certificate ON tag.id = tag_id WHERE certificate_id = :certificate_id";
    public static final String CERTIFICATE_ID = "certificate_id";
    public static final String TAG_NAME = "tag_name";
}
