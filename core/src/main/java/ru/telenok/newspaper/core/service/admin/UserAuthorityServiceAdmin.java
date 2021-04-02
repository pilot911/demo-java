package ru.telenok.newspaper.core.service.admin;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import ru.telenok.newspaper.common.model.UserAuthority;
import ru.telenok.newspaper.common.model.UserGroup;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserAuthorityServiceAdmin {

    Optional<UserAuthority> findById(Long id);

    Slice<UserAuthority> findAll(Pageable pageable);

    List<UserAuthority> findByIds(Collection<Long> ids);

    UserAuthority save(UserAuthority article);
}
