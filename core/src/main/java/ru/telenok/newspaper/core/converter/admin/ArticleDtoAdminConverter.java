package ru.telenok.newspaper.core.converter.admin;

import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.telenok.newspaper.common.dto.admin.ArticleAdminDto;
import ru.telenok.newspaper.common.model.Article;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ArticleDtoAdminConverter implements Converter<ArticleAdminDto, Article> {

    private TagDtoAdminConverter tagDtoConverter;
    private FileDataDtoAdminConveter fileDataDtoAdminConveter;

    @Override
    public Article convert(ArticleAdminDto articleDto) {

        Article article = new Article();
        article.setId(articleDto.getId());
        article.setTitle(articleDto.getTitle());
        article.setUrl(articleDto.getUrl());
        article.setContentShort(articleDto.getContentShort());
        article.setContent(articleDto.getContent());
        article.setActive(articleDto.getActive());
        article.setTag(tagDtoConverter.convert(articleDto.getTag()));
        article.setTags(articleDto.getTags().stream().map(tagDtoConverter::convert).collect(Collectors.toSet()));
        article.setFiles(articleDto.getFiles().stream().map(fileDataDtoAdminConveter::convert).collect(Collectors.toSet()));

        return article;
    }
}
