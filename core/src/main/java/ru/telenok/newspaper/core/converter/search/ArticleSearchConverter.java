package ru.telenok.newspaper.core.converter.search;

import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.telenok.newspaper.common.dto.search.ArticleSearchDto;
import ru.telenok.newspaper.common.model.Article;

import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ArticleSearchConverter implements Converter<Article, ArticleSearchDto> {

    private TagSearchConverter tagConverter;
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy MM dd HH mm ss");

    @Override
    public ArticleSearchDto convert(Article article) {

        ArticleSearchDto articleDto = new ArticleSearchDto();
        articleDto.setTitle(article.getTitle());
        articleDto.setContent(article.getContent());
        articleDto.setContentShort(article.getContentShort());
        articleDto.setUrl(article.getUrl());
        articleDto.setStartedAt(article.getStartAt() == null ? "" : article.getStartAt().format(DATE_TIME_FORMATTER));
//        articleDto.setTag(tagConverter.convert(article.getTag()));
//        articleDto.setTags(article.getTags().stream().map(tagConverter::convert).collect(Collectors.toList()));

        return articleDto;
    }
}
