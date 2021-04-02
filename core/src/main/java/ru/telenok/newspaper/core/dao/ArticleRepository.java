package ru.telenok.newspaper.core.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.telenok.newspaper.common.model.Article;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

//@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("SELECT a FROM Article a " +
            "LEFT JOIN FETCH a.user " +
            "LEFT JOIN FETCH a.files " +
            "LEFT JOIN FETCH a.tags " +
            "LEFT JOIN FETCH a.tag " +
            "WHERE a.id IN (?1)")
    List<Article> findByIdIn(Collection<Long> ids);

    @Query("SELECT a FROM Article a " +
            "LEFT JOIN FETCH a.user " +
            "LEFT JOIN FETCH a.files " +
            "LEFT JOIN FETCH a.tags " +
            "LEFT JOIN FETCH a.tag " +
            "WHERE a.id = ?1")
    Optional<Article> findById(Long id);

    @Query(value = "SELECT a FROM Article a " +
            "LEFT JOIN FETCH a.user " +
            "LEFT JOIN FETCH a.files " +
            "LEFT JOIN FETCH a.tags " +
            "LEFT JOIN FETCH a.tag",
            countQuery = "SELECT COUNT(a) FROM Article a")
    Page<Article> findAll(Pageable pageable);
}
