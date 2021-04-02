package ru.telenok.newspaper.core.service.search.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import ru.telenok.newspaper.common.model.Tag;
import ru.telenok.newspaper.core.dao.TagRepository;
import ru.telenok.newspaper.core.service.search.TagServiceSearch;

import java.util.List;
import java.util.Optional;

@Service
public class TagServiceSearchImpl implements TagServiceSearch {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public Optional<Tag> findById(Long id) {
        return tagRepository.findById(id);
    }

    @Override
    public List<Tag> findByIds(List<Long> id) {
        return tagRepository.findByIdIn(id);
    }

    @Override
    public Slice<Tag> findAll(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }
}
