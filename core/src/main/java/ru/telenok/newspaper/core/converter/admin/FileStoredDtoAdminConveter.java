package ru.telenok.newspaper.core.converter.admin;

import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import ru.telenok.newspaper.common.dto.admin.FileAdminDto;
import ru.telenok.newspaper.common.dto.admin.FileStoredAdminDto;
import ru.telenok.newspaper.common.model.File;
import ru.telenok.newspaper.common.model.embedded.FileInfo;
import ru.telenok.newspaper.core.service.common.FileStorageService;
import ru.telenok.newspaper.core.service.common.ImageAndFileProcessing;
import ru.telenok.newspaper.core.service.common.impl.ImageAndFileProcessingImpl;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class FileStoredDtoAdminConveter {

    private ImageAndFileProcessing imageAndFileProcessing;
    private FileStorageService fileStorageService;

    public List<File> convert(FileStoredAdminDto dto) {

        final ArrayList<File> files = new ArrayList<>();

        final List<String> storedFiles = dto.getStoredFiles();

        if (CollectionUtils.isEmpty(storedFiles)) {
            File file = new File();
            file.setId(dto.getId());
            file.setTitle(dto.getTitle());
            file.setDescription(dto.getDescription());
            file.setFileInfo(new FileInfo());

            files.add(file);
        } else {
            storedFiles.stream()
                    .map(f -> f.replaceAll("[^a-zA-Z0-9\\.\\-]", ""))
                    .filter(org.apache.commons.lang3.StringUtils::isNoneBlank).forEach(filename -> {
                File file = new File();
                file.setId(dto.getId());
                file.setTitle(dto.getTitle());
                file.setDescription(dto.getDescription());
                file.setFileInfo(new FileInfo());

                Path path = fileStorageService.relatedToRootDirectory(filename);

                final java.io.File fileStorage = path.toFile();

                if (fileStorage.exists()) {

                    file.getFileInfo().setUploadedName(filename);
                    file.getFileInfo().setUploadedSize(fileStorage.length());
                    file.getFileInfo().setUploadedExtension(StringUtils.getFilenameExtension(filename));
                    file.getFileInfo().setUploadedMimeType(imageAndFileProcessing.getMimeType(fileStorage));

                    if (imageAndFileProcessing.isImage(file)) {
                        ImageAndFileProcessingImpl.Dimension dimension = imageAndFileProcessing.getDimension(fileStorage);
                        file.getFileInfo().setUploadedHeight(dimension.getHeight());
                        file.getFileInfo().setUploadedWidth(dimension.getWidth());
                    }

                    files.add(file);
                }
            });
        }

        return files;
    }
}
