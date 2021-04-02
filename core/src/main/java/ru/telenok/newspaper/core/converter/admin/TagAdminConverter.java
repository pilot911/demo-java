package ru.telenok.newspaper.core.converter.admin;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.telenok.newspaper.common.dto.admin.TagAdminDto;
import ru.telenok.newspaper.common.model.Tag;

@Component
public class TagAdminConverter implements Converter<Tag, TagAdminDto> {
    @Override
    public TagAdminDto convert(Tag tag) {

        TagAdminDto tagDto = new TagAdminDto();
        tagDto.setId(tag.getId());
        tagDto.setTitle(tag.getTitle());
        tagDto.setDescription(tag.getDescription());
        tagDto.setUrl(tag.getUrl());
        tagDto.setCode(tag.getCode());
        tagDto.setActive(tag.getActive());

        return tagDto;
    }
}
