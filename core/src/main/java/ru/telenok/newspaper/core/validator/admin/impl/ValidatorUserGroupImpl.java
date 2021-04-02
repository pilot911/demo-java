package ru.telenok.newspaper.core.validator.admin.impl;

import org.springframework.stereotype.Component;
import ru.telenok.newspaper.common.dto.admin.UserGroupDto;
import ru.telenok.newspaper.core.validator.admin.ValidatorUserGroup;

import java.util.ArrayList;
import java.util.List;

@Component
public class ValidatorUserGroupImpl implements ValidatorUserGroup {

    final private int titleLengthMin = 5;
    final private int titleLengthMax = 10;

    final private int codeLengthMin = 5;
    final private int codeLengthMax = 10;

    @Override
    public List<String> validateForCreate(UserGroupDto dto) {

        final List<String> errors = new ArrayList<>();

        if (dto.getTitle() == null || dto.getTitle().isEmpty() ) {
            errors.add(Error.TITLE_EMPTY);
        } else {
            if (dto.getTitle().length() < titleLengthMin) {
                errors.add(Error.TITLE_TOO_SHORT);
            } else if (dto.getTitle().length() > titleLengthMax) {
                errors.add(Error.TITLE_TOO_LONG);
            }
        }

        if (dto.getCode() == null || dto.getCode().isEmpty() ) {
            errors.add(Error.CODE_EMPTY);
        } else {
            if (dto.getCode().length() < codeLengthMin) {
                errors.add(Error.CODE_TOO_SHORT);
            } else if (dto.getCode().length() > codeLengthMax) {
                errors.add(Error.CODE_TOO_LONG);
            }
        }

        return errors;
    }

    public List<String> validateForUpdate(UserGroupDto dto) {
        final List<String> errors = validateForCreate(dto);

        if (dto.getId() == 0) {
            errors.add(Error.ID_EMPTY);
        }

        return errors;
    }
}
