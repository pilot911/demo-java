package ru.telenok.newspaper.common.dto.search;

import lombok.Data;

import java.nio.file.Path;

@Data
public class FileInfoDto {

    private final String name;
    private final String mimeType;
    private final String extension;
    private final String webPath;
    private final Path path;
    private final long size;
    private final int width;
    private final int height;

    public FileInfoDto(String name, String mimeType, String extension, Path path, String webPath, long size, int width, int height) {
        this.name = name;
        this.mimeType = mimeType;
        this.extension = extension;
        this.path = path;
        this.webPath = webPath;
        this.size = size;
        this.width = width;
        this.height = height;
    }
}
