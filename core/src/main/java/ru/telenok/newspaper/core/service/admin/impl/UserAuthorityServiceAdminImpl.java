package ru.telenok.newspaper.core.service.admin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import ru.telenok.newspaper.common.model.UserAuthority;
import ru.telenok.newspaper.core.dao.UserAuthorityRepository;
import ru.telenok.newspaper.core.service.admin.UserAuthorityServiceAdmin;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserAuthorityServiceAdminImpl implements UserAuthorityServiceAdmin {

    @Autowired
    private UserAuthorityRepository userAuthorityRepository;

    @Override
    public Optional<UserAuthority> findById(Long id) {
        return userAuthorityRepository.findById(id);
    }

    @Override
    public Slice<UserAuthority> findAll(Pageable pageable) {
        return userAuthorityRepository.findAll(pageable);
    }

    @Override
    public List<UserAuthority> findByIds(Collection<Long> ids) {
        return userAuthorityRepository.findByIdIn(ids);
    }

    @Override
    public UserAuthority save(UserAuthority UserAuthority) {
        return userAuthorityRepository.saveAndFlush(UserAuthority);
    }
}
