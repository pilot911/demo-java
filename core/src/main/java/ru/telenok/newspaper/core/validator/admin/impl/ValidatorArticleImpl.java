package ru.telenok.newspaper.core.validator.admin.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import ru.telenok.newspaper.common.dto.admin.ArticleAdminDto;
import ru.telenok.newspaper.core.validator.admin.ValidatorArticle;

import java.util.ArrayList;
import java.util.List;

@Component
public class ValidatorArticleImpl implements ValidatorArticle {

    public List<String> validateForSave(ArticleAdminDto dto) {
        final List<String> errors = new ArrayList<>();

        if (StringUtils.isBlank(dto.getTitle())) {
            errors.add(Error.TITLE_EMPTY);
        }

        if (StringUtils.isBlank(dto.getUrl())) {
            errors.add(Error.URL_EMPTY);
        }

        if (StringUtils.isBlank(dto.getContentShort())) {
            errors.add(Error.CONTENT_SHORT_EMPTY);
        }

        if (StringUtils.isBlank(dto.getContent())) {
            errors.add(Error.CONTENT_EMPTY);
        }

        return errors;
    }
}
