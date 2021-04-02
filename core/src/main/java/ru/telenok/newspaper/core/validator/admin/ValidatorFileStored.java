package ru.telenok.newspaper.core.validator.admin;

import ru.telenok.newspaper.common.dto.admin.FileStoredAdminDto;

import java.util.List;

public interface ValidatorFileStored {
    interface Error {
        String TITLE_EMPTY = "TITLE_EMPTY";
        String DESCRIPTION_EMPTY = "DESCRIPTION_EMPTY";
        String UPLOAD_EMPTY = "UPLOAD_EMPTY";
        String MIME_TYPE = "MIME_TYPE";
    }

    List<String> validateForSave(FileStoredAdminDto dto);
}
