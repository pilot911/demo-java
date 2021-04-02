package ru.telenok.newspaper.core.validator.admin;

import ru.telenok.newspaper.common.dto.admin.UserAuthorityDto;

import java.util.List;

public interface ValidatorUserAuthority {
    interface Error {
        String ID_EMPTY = "ID_EMPTY";

        String CODE_EMPTY = "CODE_EMPTY";
        String CODE_TOO_SHORT = "CODE_TOO_SHORT";
        String CODE_TOO_LONG = "CODE_TOO_LONG";
    }

    List<String> validateForCreate(UserAuthorityDto dto);

    List<String> validateForUpdate(UserAuthorityDto dto);
}
