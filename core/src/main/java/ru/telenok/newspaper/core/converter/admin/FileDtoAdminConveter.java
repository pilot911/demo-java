package ru.telenok.newspaper.core.converter.admin;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.telenok.newspaper.common.dto.admin.FileAdminDto;
import ru.telenok.newspaper.common.model.File;
import ru.telenok.newspaper.common.model.embedded.FileInfo;
import ru.telenok.newspaper.core.service.common.FileStorageService;
import ru.telenok.newspaper.core.service.common.ImageAndFileProcessing;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class FileDtoAdminConveter implements Converter<FileAdminDto, File> {

    public File convert(FileAdminDto dto) {

        File model = new File();
        model.setId(dto.getId());
        model.setTitle(dto.getTitle());
        model.setDescription(dto.getDescription());
        model.setFileInfo(new FileInfo());

        return model;
    }
}
