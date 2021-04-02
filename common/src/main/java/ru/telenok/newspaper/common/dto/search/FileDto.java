package ru.telenok.newspaper.common.dto.search;

import lombok.Data;
import org.springframework.lang.NonNull;

import javax.validation.constraints.Size;

@Data
public class FileDto {

    private Long id;
    @NonNull
    @Size(min = 5, max = 200)
    private String title;
    @Size(min = 5, max = 2000)
    private String description;
    private FileInfoDto fileInfoDto;

    public FileDto(Long id, String title, String description, FileInfoDto fileInfoDto) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.fileInfoDto = fileInfoDto;
    }
}
