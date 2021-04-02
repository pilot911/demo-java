package ru.telenok.newspaper.core.converter.search;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.telenok.newspaper.common.dto.search.TagSearchDto;
import ru.telenok.newspaper.common.model.Tag;

@Component
public class TagSearchConverter implements Converter<Tag, TagSearchDto> {
    @Override
    public TagSearchDto convert(Tag tag) {

        TagSearchDto tagDto = new TagSearchDto();
        tagDto.setId(tag.getId());
        tagDto.setTitle(tag.getTitle());
        tagDto.setDescription(tag.getDescription());
        tagDto.setUrl(tag.getUrl());
        tagDto.setCode(tag.getCode());
        tagDto.setActive(tag.getActive());

        return tagDto;
    }
}
