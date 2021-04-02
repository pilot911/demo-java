package ru.telenok.newspaper.common.dto.search;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ArticleSearchDto {
    private Long id;
    private String title;
    private String url;
    private String contentShort;
    private String content;
    private Boolean active;
    private String startedAt;
    private TagSearchDto tag;
    private List<TagSearchDto> tags = new ArrayList<>();
}
