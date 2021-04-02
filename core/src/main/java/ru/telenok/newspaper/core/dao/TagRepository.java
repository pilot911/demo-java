package ru.telenok.newspaper.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.telenok.newspaper.common.model.Tag;

import java.util.Collection;
import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findByIdIn(Collection<Long> ids);
}
