package ru.telenok.newspaper.common.dto.admin;

import lombok.Data;

@Data
public class TagAdminDto {
    private long id;
    private String title;
    private String description;
    private String url;
    private String code;
    private Boolean active;
}
