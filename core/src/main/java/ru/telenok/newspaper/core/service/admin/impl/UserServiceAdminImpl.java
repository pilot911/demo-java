package ru.telenok.newspaper.core.service.admin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import ru.telenok.newspaper.common.model.User;
import ru.telenok.newspaper.core.dao.UserRepository;
import ru.telenok.newspaper.core.service.admin.UserServiceAdmin;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceAdminImpl implements UserServiceAdmin {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Set<User> findByIds(Collection<Long> id) {
        return userRepository.findByIdIn(id);
    }

    @Override
    public Optional<User> findByUsernameWithGroups(String username) {
        return userRepository.findByUsernameWithGroups(username);
    }

    @Override
    public Set<User> find(Collection<Long> ids, Boolean active) {
        return userRepository.findByIdIn(ids);
    }

    @Override
    public Slice<User> findAllWithGroups(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User save(User user) {
        return userRepository.saveAndFlush(user);
    }
}
