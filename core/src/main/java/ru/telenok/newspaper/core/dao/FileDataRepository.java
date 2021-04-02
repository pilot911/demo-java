package ru.telenok.newspaper.core.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.telenok.newspaper.common.model.Article;
import ru.telenok.newspaper.common.model.FileData;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

//@Repository
public interface FileDataRepository extends JpaRepository<FileData, Long> {

    @Query("SELECT f FROM FileData f " +
            "LEFT JOIN FETCH f.file  " +
            "WHERE f.id IN (?1)")
    List<FileData> findByIdIn(Collection<Long> ids);

    @Query("SELECT f FROM FileData f " +
            "LEFT JOIN FETCH f.file  " +
            "WHERE f.id = ?1")
    Optional<FileData> findById(Long id);

    @Query(value = "SELECT f FROM FileData f " +
            "LEFT JOIN FETCH f.file",
            countQuery = "SELECT COUNT(f) FROM FileData f")
    Page<FileData> findAll(Pageable pageable);
}
