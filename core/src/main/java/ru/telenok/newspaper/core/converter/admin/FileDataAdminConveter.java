package ru.telenok.newspaper.core.converter.admin;

import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.telenok.newspaper.common.dto.admin.FileDataAdminDto;
import ru.telenok.newspaper.common.model.FileData;
import ru.telenok.newspaper.core.service.common.FileStorageService;
import ru.telenok.newspaper.core.service.common.ImageAndFileProcessing;

@AllArgsConstructor
@Component
public class FileDataAdminConveter implements Converter<FileData, FileDataAdminDto> {

    private FileAdminConveter fileAdminConveter;

    public FileDataAdminDto convert(FileData model) {

        final FileDataAdminDto dto = new FileDataAdminDto();

        dto.setFile(fileAdminConveter.convert(model.getFile()));
        dto.setDescription(model.getDescription());
        dto.setSort(model.getSort());

        return dto;
    }
}
