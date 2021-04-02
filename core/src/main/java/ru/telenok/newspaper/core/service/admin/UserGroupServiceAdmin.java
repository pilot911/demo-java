package ru.telenok.newspaper.core.service.admin;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import ru.telenok.newspaper.common.model.UserGroup;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserGroupServiceAdmin {

    Optional<UserGroup> findById(Long id);

    List<UserGroup> findByIds(Collection<Long> ids);

    List<UserGroup> find(Collection<Long> ids, Boolean active);

    Slice<UserGroup> findAll(Pageable pageable);

    UserGroup save(UserGroup article);
}
