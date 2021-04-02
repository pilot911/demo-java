package ru.telenok.newspaper.core.validator.admin.impl;

import org.springframework.stereotype.Component;
import ru.telenok.newspaper.common.dto.admin.UserDto;
import ru.telenok.newspaper.core.validator.admin.ValidatorUser;

import java.util.ArrayList;
import java.util.List;

@Component
public class ValidatorUserImpl implements ValidatorUser {

    final private int usernameLengthMin = 5;
    final private int usernameLengthMax = 10;

    final private int passwordLengthMin = 5;
    final private int passwordLengthMax = 10;

    @Override
    public List<String> validateForCreate(UserDto dto) {

        final List<String> errors = new ArrayList<>();

        if (dto.getUsername() == null || dto.getUsername().isEmpty() ) {
            errors.add(Error.USERNAME_EMPTY);
        } else {
            if (dto.getUsername().length() < usernameLengthMin) {
                errors.add(Error.USERNAME_TOO_SHORT);
            } else if (dto.getUsername().length() > usernameLengthMax) {
                errors.add(Error.USERNAME_TOO_LONG);
            }
        }

        if (dto.getPassword() == null || dto.getPassword().isEmpty() ) {
            errors.add(Error.PASSWORD_EMPTY);
        } else {
            if (dto.getPassword().length() < passwordLengthMin) {
                errors.add(Error.PASSWORD_TOO_SHORT);
            } else if (dto.getPassword().length() > passwordLengthMax) {
                errors.add(Error.PASSWORD_TOO_LONG);
            }
        }

        return errors;
    }

    public List<String> validateForUpdate(UserDto dto) {
        final List<String> errors = validateForCreate(dto);

        if (dto.getId() == 0) {
            errors.add(Error.ID_EMPTY);
        }

        return errors;
    }
}
