package com.epam.esm.repository;

import com.epam.esm.model.Tag;
import com.epam.esm.model.Tags;

import java.util.List;

public interface ITagRepository {

    Tags findAllTags();

    Tags findAllTagsIncludeCertificates();

    Tag create(Tag tag);

    boolean delete(long id);

    Tag findByIdIncludeCertificates(long id);

    Tag findById(long id);

    Tag findTagByName(String tagName);

    List<Tag> findTagsByCertificateId(long id);
}
