package ru.telenok.newspaper.core.converter.admin;

import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.telenok.newspaper.common.dto.admin.ArticleAdminDto;
import ru.telenok.newspaper.common.model.Article;

import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ArticleAdminConverter implements Converter<Article, ArticleAdminDto> {

    private UserAdminConverter userConverter;
    private TagAdminConverter tagConverter;
    private FileDataAdminConveter fileDataAdminConveter;

    @Override
    public ArticleAdminDto convert(Article article) {

        ArticleAdminDto articleDto = new ArticleAdminDto();
        articleDto.setId(article.getId());
        articleDto.setTitle(article.getTitle());
        articleDto.setContent(article.getContent());
        articleDto.setContentShort(article.getContentShort());
        articleDto.setActive(article.getActive());
        articleDto.setUrl(article.getUrl());
        articleDto.setUser(userConverter.convert(article.getUser()));
        articleDto.setTag(tagConverter.convert(article.getTag()));
        articleDto.setTags(article.getTags().stream().map(tagConverter::convert).collect(Collectors.toSet()));
        articleDto.setFiles(article.getFiles().stream().map(fileDataAdminConveter::convert).collect(Collectors.toSet()));

        return articleDto;
    }
}
