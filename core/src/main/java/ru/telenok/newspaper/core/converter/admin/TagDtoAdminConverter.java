package ru.telenok.newspaper.core.converter.admin;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.telenok.newspaper.common.dto.admin.TagAdminDto;
import ru.telenok.newspaper.common.model.Tag;

@Component
public class TagDtoAdminConverter implements Converter<TagAdminDto, Tag> {
    @Override
    public Tag convert(TagAdminDto tagDto) {
        Tag tag = new Tag();
        tag.setId(tagDto.getId());
        tag.setTitle(tagDto.getTitle());
        tag.setDescription(tagDto.getDescription());
        tag.setUrl(tagDto.getUrl());
        tag.setCode(tagDto.getCode());
        tag.setActive(tagDto.getActive());

        return tag;
    }
}
