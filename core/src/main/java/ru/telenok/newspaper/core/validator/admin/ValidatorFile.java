package ru.telenok.newspaper.core.validator.admin;

import ru.telenok.newspaper.common.dto.admin.ArticleAdminDto;
import ru.telenok.newspaper.common.dto.admin.FileAdminDto;

import java.util.List;

public interface ValidatorFile {
    interface Error {
        String TITLE_EMPTY = "TITLE_EMPTY";
        String DESCRIPTION_EMPTY = "DESCRIPTION_EMPTY";
        String UPLOAD = "UPLOAD";
        String MIME_TYPE = "MIME_TYPE";
    }

    List<String> validateForSave(FileAdminDto dto);
}
