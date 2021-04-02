package ru.telenok.newspaper.core.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.telenok.newspaper.common.model.Article;
import ru.telenok.newspaper.common.model.File;
import ru.telenok.newspaper.common.model.Tag;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

//@Repository
public interface FileRepository extends JpaRepository<File, Long> {

    @Query("SELECT f FROM File f " +
            "LEFT JOIN FETCH f.user " +
            "LEFT JOIN FETCH f.tags " +
            "WHERE f.fileInfo.uploadedName = ?1")
    List<File> findByIdIn(Collection<Long> ids);

    @Query("SELECT f FROM File f " +
            "LEFT JOIN FETCH f.user " +
            "LEFT JOIN FETCH f.tags " +
            "WHERE f.id = ?1")
    Optional<File> findById(Long id);

    @Query(value = "SELECT f FROM File f " +
            "LEFT JOIN FETCH f.user " +
            "LEFT JOIN FETCH f.tags",
            countQuery = "SELECT COUNT(f) FROM File f")
    Page<File> findAll(Pageable pageable);

    @Query(value = "SELECT f FROM File f " +
            "WHERE f.title LIKE CONCAT('%',:text,'%') OR f.description LIKE CONCAT('%',:text,'%')",
            countQuery = "SELECT COUNT(f) FROM File f")
    Page<File> findAllByText(String text, Pageable pageable);
}
