package ru.telenok.newspaper.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.telenok.newspaper.common.model.UserAuthority;
import ru.telenok.newspaper.common.model.UserGroup;

import java.util.Collection;
import java.util.List;

//@Repository
public interface UserAuthorityRepository extends JpaRepository<UserAuthority, Long> {

    List<UserAuthority> findByIdIn(Collection<Long> ids);
}

