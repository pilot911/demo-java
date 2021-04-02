package ru.telenok.newspaper.core.validator.admin;

import ru.telenok.newspaper.common.dto.admin.UserDto;

import java.util.List;

public interface ValidatorUser {
    interface Error {
        String ID_EMPTY = "ID_EMPTY";

        String USERNAME_EMPTY = "USERNAME_EMPTY";
        String USERNAME_TOO_SHORT = "USERNAME_TOO_SHORT";
        String USERNAME_TOO_LONG = "USERNAME_TOO_LONG";

        String PASSWORD_EMPTY = "PASSWORD_EMPTY";
        String PASSWORD_TOO_SHORT = "PASSWORD_TOO_SHORT";
        String PASSWORD_TOO_LONG = "PASSWORD_TOO_LONG";
    }

    List<String> validateForCreate(UserDto dto);

    List<String> validateForUpdate(UserDto dto);
}
