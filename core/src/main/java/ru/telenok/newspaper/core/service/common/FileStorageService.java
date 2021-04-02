package ru.telenok.newspaper.core.service.common;

import org.springframework.web.multipart.MultipartFile;
import ru.telenok.newspaper.common.ImageConstant;
import ru.telenok.newspaper.common.model.File;

import java.io.IOException;
import java.nio.file.Path;

public interface FileStorageService {

    boolean canUpload(File file);

    boolean canUpload(MultipartFile file);

    Path storeMultipartFile(MultipartFile file, String filename) throws IOException;

    void delete(String path) throws IOException;

    void delete(Path path) throws IOException;

    Path relatedToRootDirectory(String file);

    String relatedToWebDirectory(String file);

    String relatedToWebImageDirectory(int width, int height, ImageConstant.Proportion proportion, String filename);

    Path relatedToImageDirectory(int width, int height, ImageConstant.Proportion proportion, String filename);

//    Path createTemporaryFileFromMultipart(MultipartFile multipartFile) throws IOException;

//    String webPath(String path);
//
//    String webPath(Path path);

    void delete(File file) throws IOException;

    String generateRandomFilename(String filename);
}
