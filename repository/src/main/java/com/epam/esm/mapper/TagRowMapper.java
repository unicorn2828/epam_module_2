package com.epam.esm.mapper;

import com.epam.esm.model.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class TagRowMapper implements RowMapper<Tag> {
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "tag_name";

    @Override
    public Tag mapRow(ResultSet rs, int rowNum) throws SQLException {
        Tag tag = new Tag();
        tag.setId(rs.getLong(TAG_ID));
        tag.setName(rs.getString(TAG_NAME));
        return tag;
    }
}