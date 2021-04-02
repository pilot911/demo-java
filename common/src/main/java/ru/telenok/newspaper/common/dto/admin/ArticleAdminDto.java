package ru.telenok.newspaper.common.dto.admin;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class ArticleAdminDto {
    private long id;
    private String title;
    private String url;
    private String contentShort;
    private String content;
    private Boolean active;
    private UserDto user;
    private TagAdminDto tag;
    private Set<TagAdminDto> tags = new HashSet<>();
    private Set<FileDataAdminDto> files = new HashSet<>();
}
