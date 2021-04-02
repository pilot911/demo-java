package ru.telenok.newspaper.core.service.admin;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import ru.telenok.newspaper.common.model.Article;
import ru.telenok.newspaper.common.model.Tag;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ArticleServiceAdmin {

    Optional<Article> findById(Long id);

    List<Article> findByIdIn(Collection<Long> ids);

    List<Article> find(Collection<Long> ids, Boolean active, Collection<Tag> tags);

    Slice<Article> findAll(Pageable pageable);

    Article save(Article article);
}
