package ru.telenok.newspaper.core.service.admin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import ru.telenok.newspaper.common.model.Tag;
import ru.telenok.newspaper.core.dao.TagRepository;
import ru.telenok.newspaper.core.service.admin.TagServiceAdmin;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class TagServiceAdminImpl implements TagServiceAdmin {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public Optional<Tag> findById(Long id) {
        return tagRepository.findById(id);
    }

    @Override
    public List<Tag> findByIds(Collection<Long> id) {
        return tagRepository.findByIdIn(id);
    }

    @Override
    public Slice<Tag> findAll(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Override
    public Tag save(Tag tag) {
        return tagRepository.saveAndFlush(tag);
    }


}
