package ru.telenok.newspaper.core.service.admin.impl;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.telenok.newspaper.common.dto.admin.FileAdminDto;
import ru.telenok.newspaper.common.exception.ListException;
import ru.telenok.newspaper.common.model.File;
import ru.telenok.newspaper.common.model.Tag;
import ru.telenok.newspaper.core.converter.admin.FileDtoAdminConveter;
import ru.telenok.newspaper.core.dao.FileRepository;
import ru.telenok.newspaper.core.service.admin.FileServiceAdmin;
import ru.telenok.newspaper.core.service.common.FileStorageService;
import ru.telenok.newspaper.core.validator.admin.ValidatorFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

@Service
@AllArgsConstructor
public class FileServiceAdminImpl implements FileServiceAdmin {

    private FileRepository fileRepository;

    @Override
    public Optional<File> findById(Long id) {
        return fileRepository.findById(id);
    }

    @Override
    public List<File> findByIds(Collection<Long> ids) {
        return fileRepository.findByIdIn(ids);
    }

    @Override
    public Slice<File> findAll(Pageable pageable) {
        return fileRepository.findAll(pageable);
    }

    @Override
    public Slice<File> findAllByText(String text, Pageable pageable) {
        return fileRepository.findAllByText(text, pageable);
    }

    @Override
    public File save(File file) {
        final File fileFromDB = findById(file.getId()).orElse(new File());
        fileFromDB.merge(file);
        return fileRepository.save(file);
    }
}
