package com.epam.esm.mapper;

import com.epam.esm.dto.CertificateDto;
import com.epam.esm.dto.CertificatesDto;
import com.epam.esm.dto.TagDto;
import com.epam.esm.dto.TagsDto;
import com.epam.esm.model.Certificate;
import com.epam.esm.model.Certificates;
import com.epam.esm.model.Tag;
import com.epam.esm.model.Tags;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DtoMapper {
    private ModelMapper mapper = new ModelMapper();

    public Certificate toCertificate(CertificateDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Certificate.class);
    }

    public CertificateDto toCertificateDto(Certificate entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, CertificateDto.class);
    }

    public Tag toTag(TagDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Tag.class);
    }

    public TagDto toTagDto(Tag entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, TagDto.class);
    }

    public CertificatesDto toCertificatesDto(Certificates certificateList) {
        CertificatesDto certificatesDto = new CertificatesDto();
        certificatesDto.setCertificates(certificateList.getCertificates().stream()
                .map(certificate -> mapper.map(certificate, CertificateDto.class))
                .collect(Collectors.toList()));
        return certificatesDto;
    }
    public Certificates toCertificates(CertificatesDto dtoList) {
        Certificates certificates = new Certificates();
        certificates.setCertificates(dtoList.getCertificates().stream()
                .map(certificate -> mapper.map(certificate, Certificate.class))
                .collect(Collectors.toList()));
        return certificates;
    }
    public Tags toTags(TagsDto dtoList) {
        Tags tags = new Tags();
        tags.setTags(dtoList.getTags().stream()
                .map(tag -> mapper.map(tag, Tag.class))
                .collect(Collectors.toList()));
        return tags;
    }

    public TagsDto toTagsDto(Tags tagList) {
        TagsDto tagsDto = new TagsDto();
        tagsDto.setTags(tagList.getTags().stream()
                .map(tag -> mapper.map(tag, TagDto.class))
                .collect(Collectors.toList()));
        return tagsDto;
    }


}