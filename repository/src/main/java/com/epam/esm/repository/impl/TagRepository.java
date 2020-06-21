package com.epam.esm.repository.impl;

import com.epam.esm.mapper.TagRowMapper;
import com.epam.esm.model.Tag;
import com.epam.esm.model.Tags;
import com.epam.esm.repository.ICertificateRepository;
import com.epam.esm.repository.ITagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.sql.PreparedStatement;
import java.util.List;

import static com.epam.esm.repository.data.TagRepositoryData.*;

@Repository("tagRepository")
@RequiredArgsConstructor
public class TagRepository implements ITagRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final ICertificateRepository certificateRepository;
    private final JdbcTemplate jdbcTemplate;
    private final TagRowMapper mapper;
    private final Tags tags;

    @PostConstruct
    public void init() {
        certificateRepository.setTagRepository(this);
    }

    @Override
    @Transactional
    public Tag findByIdIncludeCertificates(long id) {
        try {
            Tag tag = jdbcTemplate.queryForObject(SQL_FIND_TAG_BY_ID, new Object[]{id}, mapper);
            tag.setCertificates(certificateRepository.findCertificatesByTagId(tag.getId()));
            return tag;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Tag findById(long id) {
        try {
            Tag tag = jdbcTemplate.queryForObject(SQL_FIND_TAG_BY_ID, new Object[]{id}, mapper);
            return tag;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Tag findTagByName(String tagName) {
        try {
            Tag tag = jdbcTemplate.queryForObject(SQL_FIND_TAG_BY_NAME, new Object[]{tagName}, mapper);
            return tag;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    @Transactional
    public Tags findAllTagsIncludeCertificates() {
        List<Tag> tagList = jdbcTemplate.query(SQL_FIND_ALL_TAGS, mapper);
        tagList.forEach(tag -> tag.setCertificates(certificateRepository.findCertificatesByTagId(tag.getId())));
        tags.setTags(tagList);
        return tags;
    }
    @Override
    public Tags findAllTags() {
        List<Tag> tagList = jdbcTemplate.query(SQL_FIND_ALL_TAGS, mapper);
        tags.setTags(tagList);
        return tags;
    }

    @Override
    public boolean delete(long id) {
        boolean status = false;
        if (findById(id) != null) {
            jdbcTemplate.update(SQL_DELETE_TAG, id);
            status = true;
        }
        return status;
    }

    @Override
    public Tag create(Tag tag) {
        Tag currentTag = findTagByName(tag.getName());
        if (currentTag != null) {
            return currentTag;
        }
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(SQL_CREATE_TAG, new String[]{ID});
            ps.setString(1, tag.getName());
            return ps;
        }, keyHolder);
        long id = (long) keyHolder.getKey();
        return findById(id);
    }

    @Override
    public List<Tag> findTagsByCertificateId(long id) {
        SqlParameterSource params = new MapSqlParameterSource(CERTIFICATE_ID, id);
        return namedParameterJdbcTemplate.query(SQL_FIND_TAGS_BY_CERTIFICATE_ID, params, mapper);
    }
}