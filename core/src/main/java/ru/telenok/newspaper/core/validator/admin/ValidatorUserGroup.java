package ru.telenok.newspaper.core.validator.admin;

import ru.telenok.newspaper.common.dto.admin.UserGroupDto;

import java.util.List;

public interface ValidatorUserGroup {
    interface Error {
        String ID_EMPTY = "ID_EMPTY";

        String TITLE_EMPTY      = "TITLE_EMPTY";
        String TITLE_TOO_SHORT  = "TITLE_TOO_SHORT";
        String TITLE_TOO_LONG   = "TITLE_TOO_LONG";

        String CODE_EMPTY       = "CODE_EMPTY";
        String CODE_TOO_SHORT   = "CODE_TOO_SHORT";
        String CODE_TOO_LONG    = "CODE_TOO_LONG";
    }

    List<String> validateForCreate(UserGroupDto dto);

    List<String> validateForUpdate(UserGroupDto dto);
}
