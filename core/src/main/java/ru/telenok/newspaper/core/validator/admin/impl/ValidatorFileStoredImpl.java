package ru.telenok.newspaper.core.validator.admin.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;
import ru.telenok.newspaper.common.dto.admin.FileStoredAdminDto;
import ru.telenok.newspaper.core.validator.admin.ValidatorFileStored;

import java.util.ArrayList;
import java.util.List;

@Component
public class ValidatorFileStoredImpl implements ValidatorFileStored {

    public List<String> validateForSave(FileStoredAdminDto dto) {
        final List<String> errors = new ArrayList<>();

        if (StringUtils.isBlank(dto.getTitle())) {
            errors.add(Error.TITLE_EMPTY);
        }

        if (StringUtils.isBlank(dto.getDescription())) {
            errors.add(Error.DESCRIPTION_EMPTY);
        }

        if (dto.getId() == 0 && CollectionUtils.isEmpty(dto.getStoredFiles())) {
            errors.add(Error.UPLOAD_EMPTY);
        }

        return errors;
    }
}
