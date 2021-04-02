package ru.telenok.newspaper.core.validator.admin;

import ru.telenok.newspaper.common.dto.admin.TagAdminDto;

import java.util.List;

public interface ValidatorTag {
    interface Error {
        String TITLE_EMPTY = "TITLE_EMPTY";
        String URL_EMPTY = "URL_EMPTY";
    }

    List<String> validateForSave(TagAdminDto dto);
}
