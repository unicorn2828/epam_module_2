package com.epam.esm.model;

import lombok.Data;

import java.util.List;

@Data
public class Tag {
    private long id;
    private String name;
    private List<Certificate> certificates;
}
