package com.epam.esm.mapper;

import com.epam.esm.dto.CertificateDto;
import com.epam.esm.dto.CertificatesDto;
import com.epam.esm.dto.TagDto;
import com.epam.esm.dto.TagsDto;
import com.epam.esm.model.Certificate;
import com.epam.esm.model.Certificates;
import com.epam.esm.model.Tag;
import com.epam.esm.model.Tags;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class DtoMapperTest {
    private DtoMapper dtoMapper = new DtoMapper();

    @Test
    void toTagDto() {
        TagDto actual = dtoMapper.toTagDto(new Tag());
        Assertions.assertTrue(actual instanceof TagDto);
    }

    @Test
    void toTag() {
        Tag actual = dtoMapper.toTag(new TagDto());
        Assertions.assertTrue(actual instanceof Tag);
    }

    @Test
    void toTagsDto() {
        Tags tags = new Tags();
        tags.setTags(new ArrayList<>());
        TagsDto actual = dtoMapper.toTagsDto(tags);
        Assertions.assertTrue(actual instanceof TagsDto);
    }

    @Test
    void toTags() {
        TagsDto tagsDto = new TagsDto();
        tagsDto.setTags(new ArrayList<>());
        Tags actual = dtoMapper.toTags(tagsDto);
        Assertions.assertTrue(actual instanceof Tags);
    }

    @Test
    void toCertificateDto() {
        CertificateDto actual = dtoMapper.toCertificateDto(new Certificate());
        Assertions.assertTrue(actual instanceof CertificateDto);
    }

    @Test
    void toCertificate() {
        Certificate actual = dtoMapper.toCertificate(new CertificateDto());
        Assertions.assertTrue(actual instanceof Certificate);
    }

    @Test
    void toCertificatesDto() {
        Certificates certificates = new Certificates();
        certificates.setCertificates(new ArrayList<>());
        CertificatesDto actual = dtoMapper.toCertificatesDto(certificates);
        Assertions.assertTrue(actual instanceof CertificatesDto);
    }

    @Test
    void toCertificates() {
        CertificatesDto certificatesDto = new CertificatesDto();
        certificatesDto.setCertificates(new ArrayList<>());
        Certificates actual = dtoMapper.toCertificates(certificatesDto);
        Assertions.assertTrue(actual instanceof Certificates);
    }
}