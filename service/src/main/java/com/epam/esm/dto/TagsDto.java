package com.epam.esm.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class TagsDto {
    private List<TagDto> tags;
}
