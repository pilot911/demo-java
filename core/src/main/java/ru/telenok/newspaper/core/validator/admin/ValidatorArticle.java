package ru.telenok.newspaper.core.validator.admin;

import ru.telenok.newspaper.common.dto.admin.ArticleAdminDto;

import java.util.List;

public interface ValidatorArticle {
    interface Error {
        String TITLE_EMPTY = "TITLE_EMPTY";
        String URL_EMPTY = "URL_EMPTY";
        String CONTENT_EMPTY = "CONTENT_EMPTY";
        String CONTENT_SHORT_EMPTY = "CONTENT_SHORT_EMPTY";
    }

    List<String> validateForSave(ArticleAdminDto dto);
}
