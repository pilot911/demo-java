package ru.telenok.newspaper.core.service.admin;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import ru.telenok.newspaper.common.dto.admin.FileAdminDto;
import ru.telenok.newspaper.common.exception.ListException;
import ru.telenok.newspaper.common.model.Article;
import ru.telenok.newspaper.common.model.File;
import ru.telenok.newspaper.common.model.Tag;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface FileServiceAdmin {

    Optional<File> findById(Long id);

    List<File> findByIds(Collection<Long> ids);

    Slice<File> findAll(Pageable pageable);

    Slice<File> findAllByText(String text, Pageable pageable);

    File save(File file);
}
