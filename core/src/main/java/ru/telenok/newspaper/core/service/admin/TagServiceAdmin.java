package ru.telenok.newspaper.core.service.admin;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import ru.telenok.newspaper.common.model.Article;
import ru.telenok.newspaper.common.model.Tag;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface TagServiceAdmin {

    Optional<Tag> findById(Long id);

    List<Tag> findByIds(Collection<Long> ids);

    Slice<Tag> findAll(Pageable pageable);

    Tag save(Tag tag);
}
