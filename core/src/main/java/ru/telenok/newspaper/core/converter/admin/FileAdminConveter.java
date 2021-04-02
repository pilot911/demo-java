package ru.telenok.newspaper.core.converter.admin;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.telenok.newspaper.common.ImageConstant;
import ru.telenok.newspaper.common.dto.admin.FileAdminDto;
import ru.telenok.newspaper.common.model.File;
import ru.telenok.newspaper.common.model.embedded.FileInfo;
import ru.telenok.newspaper.core.service.common.FileStorageService;
import ru.telenok.newspaper.core.service.common.ImageAndFileProcessing;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class FileAdminConveter implements Converter<File, FileAdminDto> {

    private FileStorageService fileStorageService;
    private ImageAndFileProcessing imageAndFileProcessing;

    public FileAdminDto convert(File entity) {

        FileAdminDto.FileAdminDtoBuilder fileAdminDtoBuilder = FileAdminDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription());

        if (entity.getFileInfo() != null) {

            final FileInfo fileInfo = entity.getFileInfo();

            fileAdminDtoBuilder
                    .uploadedName(fileInfo.getUploadedName())
                    .uploadedExtension(fileInfo.getUploadedExtension())
                    .uploadedMimeType(fileInfo.getUploadedMimeType())
                    .uploadedHeight(fileInfo.getUploadedHeight())
                    .uploadedWidth(fileInfo.getUploadedWidth())
                    .uploadedSize(fileInfo.getUploadedSize());
//
//            if (fileInfo.getUploadedName() != null) {
//
//                final Path path = fileStorageService.relatedToRootDirectory(fileInfo.getUploadedName());
//
//                if (imageAndFileProcessing.isImage(path.toFile())) {
//                    fileAdminDtoBuilder
//                            .uploadedWebPath(fileStorageService.relatedToWebImageDirectory(200, 200,
//                                    ImageConstant.Proportion.STRONG, fileInfo.getUploadedName()));
//
//                } else {
//                    fileAdminDtoBuilder
//                            .uploadedWebPath(fileStorageService.relatedToWebDirectory(fileInfo.getUploadedName()));
//                }
//            }
        }

        return fileAdminDtoBuilder.build();
    }
}
