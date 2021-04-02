package ru.telenok.newspaper.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.telenok.newspaper.common.model.UserGroup;

import java.util.Collection;
import java.util.List;

//@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {

    @Query("SELECT g FROM UserGroup g " +
            "LEFT JOIN FETCH g.authorities a " +
            "WHERE g.id IN (?1)")
    List<UserGroup> findByIdIn(Collection<Long> ids);
}

