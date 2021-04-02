package ru.telenok.newspaper.core.service.search;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import ru.telenok.newspaper.common.model.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleServiceSearch {

    Optional<Article> findById(Long id);

    Slice<Article> findAll(Pageable pageable);

    List<Article> findByMustAndShouldTags(List<String> tagCodesMustBe, Pageable pageable);
}
