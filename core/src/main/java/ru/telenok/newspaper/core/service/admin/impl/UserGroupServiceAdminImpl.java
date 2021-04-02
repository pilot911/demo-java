package ru.telenok.newspaper.core.service.admin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import ru.telenok.newspaper.common.model.User;
import ru.telenok.newspaper.common.model.UserGroup;
import ru.telenok.newspaper.core.dao.UserGroupRepository;
import ru.telenok.newspaper.core.service.admin.UserGroupServiceAdmin;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserGroupServiceAdminImpl implements UserGroupServiceAdmin {

    @Autowired
    private UserGroupRepository userGroupRepository;

    @Override
    public Optional<UserGroup> findById(Long id) {
        return userGroupRepository.findById(id);
    }

    @Override
    public List<UserGroup> findByIds(Collection<Long> id) {
        return userGroupRepository.findByIdIn(id);
    }

    @Override
    public List<UserGroup> find(Collection<Long> ids, Boolean active) {
        return userGroupRepository.findByIdIn(ids);
    }

    @Override
    public Slice<UserGroup> findAll(Pageable pageable) {
        return userGroupRepository.findAll(pageable);
    }

    @Override
    public UserGroup save(UserGroup userGroup) {
        return userGroupRepository.saveAndFlush(userGroup);
    }
}
