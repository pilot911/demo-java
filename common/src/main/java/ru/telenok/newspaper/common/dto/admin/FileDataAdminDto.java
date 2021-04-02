package ru.telenok.newspaper.common.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FileDataAdminDto {
    private long id;
    private String description;
    private int sort;
    private FileAdminDto file;
}
