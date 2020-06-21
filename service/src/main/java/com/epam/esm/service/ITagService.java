package com.epam.esm.service;

import com.epam.esm.dto.TagDto;
import com.epam.esm.dto.TagsDto;

/**
 * The ITagService interface
 *
 * @author Vitaly Kononov
 */
public interface ITagService {

    /**
     * This method finds a tag in database by id
     *
     * @param id - id of tag
     * @return {link} TagDTO
     */
    TagDto findTagById(long id);
    /**
     * This method finds a tag in database by id
     *
     * @param id - id of tag
     * @return {link} TagDTO
     */
    TagDto findTagByIdIncludeCertificates(long id);

    /**
     * This method finds all tags in database
     *
     * @return {link} TagsDTO
     */
    TagsDto findAllTags();

    /**
     * This method removes a tag from database by id
     *
     * @param id - id of tag
     * @return {link void}
     */
    void removeTagById(long id);

    /**
     * THis method creates a new tag
     *
     * @param tagDto - the new tag to save
     * @return {link void}
     */
    TagDto saveTag(TagDto tagDto);

    /**
     * This method finds a tag in database by name
     *
     * @param tagName - name of tag
     * @return {link} TagDTO
     */
    TagDto findTagByName(String tagName);
}