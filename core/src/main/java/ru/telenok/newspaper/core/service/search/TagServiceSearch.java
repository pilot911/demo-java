package ru.telenok.newspaper.core.service.search;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import ru.telenok.newspaper.common.model.Tag;

import java.util.List;
import java.util.Optional;

public interface TagServiceSearch {

    Optional<Tag> findById(Long id);

    List<Tag> findByIds(List<Long> ids);

    Slice<Tag> findAll(Pageable pageable);
}
