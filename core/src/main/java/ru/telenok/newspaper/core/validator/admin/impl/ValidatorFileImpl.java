package ru.telenok.newspaper.core.validator.admin.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import ru.telenok.newspaper.common.dto.admin.ArticleAdminDto;
import ru.telenok.newspaper.common.dto.admin.FileAdminDto;
import ru.telenok.newspaper.core.validator.admin.ValidatorArticle;
import ru.telenok.newspaper.core.validator.admin.ValidatorFile;

import java.util.ArrayList;
import java.util.List;

@Component
public class ValidatorFileImpl implements ValidatorFile {

    public List<String> validateForSave(FileAdminDto dto) {
        final List<String> errors = new ArrayList<>();

        if (StringUtils.isBlank(dto.getTitle())) {
            errors.add(Error.TITLE_EMPTY);
        }

        if (StringUtils.isBlank(dto.getDescription())) {
            errors.add(Error.DESCRIPTION_EMPTY);
        }

        return errors;
    }
}
