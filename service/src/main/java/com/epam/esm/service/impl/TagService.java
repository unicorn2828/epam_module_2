package com.epam.esm.service.impl;

import com.epam.esm.dto.TagDto;
import com.epam.esm.dto.TagsDto;
import com.epam.esm.exception.ServiceErrorCode;
import com.epam.esm.exception.ServiceException;
import com.epam.esm.mapper.DtoMapper;
import com.epam.esm.model.Tag;
import com.epam.esm.repository.ITagRepository;
import com.epam.esm.service.ITagService;
import com.epam.esm.validation.TagValidator;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagService implements ITagService {
    static final Logger logger = LogManager.getLogger();
    private final ITagRepository repository;
    private final DtoMapper dtoMapper;

    @Override
    public TagDto findTagById(long id) {
        TagValidator.isId(id);
        TagDto tagDto = dtoMapper.toTagDto(repository.findById(id));
        TagValidator.isTag(tagDto);
        return tagDto;
    }

    @Override
    public TagDto findTagByIdIncludeCertificates(long id) {
        TagValidator.isId(id);
        TagDto tagDto = dtoMapper.toTagDto(repository.findByIdIncludeCertificates(id));
        TagValidator.isTag(tagDto);
        return tagDto;
    }

    @Override
    public TagsDto findAllTags() {
        TagsDto tagsDto = new TagsDto();
        tagsDto.setTags(dtoMapper.toTagsDto(repository.findAllTags()).getTags());
        return tagsDto;
    }

    @Override
    public TagDto saveTag(TagDto tagDto) {
        tagDto.setId(null);
        TagValidator.isTag(tagDto);
        Tag tag = repository.create(dtoMapper.toTag(tagDto));
        TagDto createdTagDto = dtoMapper.toTagDto(tag);
        TagValidator.isTag(createdTagDto);
        return createdTagDto;
    }

    @Override
    public TagDto findTagByName(String tagName) {
        TagValidator.isName(tagName);
        TagDto tagDto = dtoMapper.toTagDto(repository.findTagByName(tagName));
        TagValidator.isTagWithName(tagDto);
        return tagDto;
    }

    @Override
    public void removeTagById(long id) {
        TagValidator.isId(id);
        boolean isDelete = repository.delete(id);
        if (!isDelete) {
            ServiceErrorCode errorCode = ServiceErrorCode.TAG_WITH_SUCH_ID_NOT_EXIST;
            logger.error(errorCode.getErrorCode());
            throw new ServiceException(errorCode);
        }
    }
}