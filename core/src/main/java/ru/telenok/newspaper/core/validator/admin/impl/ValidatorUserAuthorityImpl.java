package ru.telenok.newspaper.core.validator.admin.impl;

import org.springframework.stereotype.Component;
import ru.telenok.newspaper.common.dto.admin.UserAuthorityDto;
import ru.telenok.newspaper.core.validator.admin.ValidatorUserAuthority;

import java.util.ArrayList;
import java.util.List;

@Component
public class ValidatorUserAuthorityImpl implements ValidatorUserAuthority {

    final private int codeLengthMin = 5;
    final private int codeLengthMax = 10;

    @Override
    public List<String> validateForCreate(UserAuthorityDto dto) {

        final List<String> errors = new ArrayList<>();

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

    public List<String> validateForUpdate(UserAuthorityDto dto) {
        final List<String> errors = validateForCreate(dto);

        if (dto.getId() == 0) {
            errors.add(Error.ID_EMPTY);
        }

        return errors;
    }
}
