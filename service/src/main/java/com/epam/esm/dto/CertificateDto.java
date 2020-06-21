package com.epam.esm.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Component
public class CertificateDto {
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private Long id;
    private String certificateName;
    private String description;
    private BigDecimal price;
    @JsonFormat(pattern = DATE_FORMAT)
    private LocalDate creationDate;
    @JsonFormat(pattern = DATE_FORMAT)
    private LocalDate modificationDate;
    private int duration;
    private List<TagDto> tags;
}

