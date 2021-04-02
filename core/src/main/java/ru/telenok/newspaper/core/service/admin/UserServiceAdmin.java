package ru.telenok.newspaper.core.service.admin;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import ru.telenok.newspaper.common.model.User;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface UserServiceAdmin {

    Optional<User> findById(Long id);

    Set<User> findByIds(Collection<Long> ids);

    Optional<User> findByUsernameWithGroups(String username);

    Set<User> find(Collection<Long> ids, Boolean active);

    Slice<User> findAllWithGroups(Pageable pageable);

    User save(User article);
}
