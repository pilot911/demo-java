package ru.telenok.newspaper.core.converter.admin;

import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.telenok.newspaper.common.dto.admin.FileDataAdminDto;
import ru.telenok.newspaper.common.model.FileData;

@AllArgsConstructor
@Component
public class FileDataDtoAdminConveter implements Converter<FileDataAdminDto, FileData> {

    private FileDtoAdminConveter fileDtoAdminConveter;

    public FileData convert(FileDataAdminDto dto) {

        FileData model = new FileData();
        model.setId(dto.getId());
        model.setDescription(dto.getDescription());
        model.setSort(dto.getSort());
        model.setFile(fileDtoAdminConveter.convert(dto.getFile()));

        return model;
    }
}
