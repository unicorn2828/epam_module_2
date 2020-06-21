package com.epam.esm.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class Certificate {
    private long id;
    private String certificateName;
    private String description;
    private BigDecimal price;
    private LocalDate creationDate;
    private LocalDate modificationDate;
    private int duration;
    private List<Tag> tags;
}
