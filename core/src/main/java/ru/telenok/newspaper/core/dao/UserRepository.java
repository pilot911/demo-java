package ru.telenok.newspaper.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.telenok.newspaper.common.model.User;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

//@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u " +
            "LEFT JOIN FETCH u.groups g " +
//            "LEFT JOIN FETCH g.authorities " +
            "WHERE u.id = :id")
    Optional<User> findById(Long id);

    @Query("SELECT u FROM User u " +
            "LEFT JOIN FETCH u.groups g " +
//            "LEFT JOIN FETCH u.articles a " +wda
//            "LEFT JOIN FETCH g.authorities " +
            "WHERE u.id IN (:ids)")
    Set<User> findByIdIn(Collection<Long> ids);

    @Query("SELECT u FROM User u " +
            "LEFT JOIN FETCH u.groups g " +
            "LEFT JOIN g.authorities a " +
            "WHERE u.username = ?1")
    Optional<User> findByUsernameWithGroups(String username);
}
