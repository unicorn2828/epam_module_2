package com.epam.esm.model;


import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class Certificates {
    private List<Certificate> certificates;
}
