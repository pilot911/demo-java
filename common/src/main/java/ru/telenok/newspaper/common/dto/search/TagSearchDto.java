package ru.telenok.newspaper.common.dto.search;

import lombok.Data;

@Data
public class TagSearchDto {
    private Long id;
    private String title;
    private String description;
    private String url;
    private String code;
    private Boolean active;
}
