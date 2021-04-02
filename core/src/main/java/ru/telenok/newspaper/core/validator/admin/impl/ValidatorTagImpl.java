package ru.telenok.newspaper.core.validator.admin.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import ru.telenok.newspaper.common.dto.admin.TagAdminDto;
import ru.telenok.newspaper.core.validator.admin.ValidatorArticle;
import ru.telenok.newspaper.core.validator.admin.ValidatorTag;

import java.util.ArrayList;
import java.util.List;

@Component
public class ValidatorTagImpl implements ValidatorTag {

    @Override
    public List<String> validateForSave(TagAdminDto dto) {

        final List<String> errors = new ArrayList<>();

        if (StringUtils.isBlank(dto.getTitle())) {
            errors.add(Error.TITLE_EMPTY);
        }

        if (StringUtils.isBlank(dto.getUrl())) {
            errors.add(Error.URL_EMPTY);
        }

        return errors;
    }
}
