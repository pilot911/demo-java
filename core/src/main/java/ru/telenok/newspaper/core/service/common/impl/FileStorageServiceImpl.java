package ru.telenok.newspaper.core.service.common.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.telenok.newspaper.common.FileConstant;
import ru.telenok.newspaper.common.ImageConstant;
import ru.telenok.newspaper.common.model.File;
import ru.telenok.newspaper.core.service.common.FileStorageService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class FileStorageServiceImpl implements FileStorageService {

    @Value("${news.rootDireсtory}")
    private String rootDireсtory;
//    @Value("${news.tmpDirectory}")
//    private String tmpDirectory;
    @Value("${news.imageResizedDirectory}")
    private String imageResizedDirectory;
    @Value("${news.webDocDirectory}")
    private String webDocDirectory;
    @Value("${news.webImageDirectory}")
    private String webImageDirectory;
    @Value("${news.baseHREF}")
    private String baseHREF;

    public boolean canUpload(File file) {
        return file != null
                && file.getFileInfo() != null
                && file.getFileInfo().getUploadedMimeType() != null
                && FileConstant.allowUploadMimeType.contains(file.getFileInfo().getUploadedMimeType().toLowerCase());
    }

    @Override
    public boolean canUpload(MultipartFile file) {
        return file != null && FileConstant.allowUploadMimeType.contains(file.getContentType());
    }

    @Override
    public Path storeMultipartFile(MultipartFile file, String filename) throws IOException {
        Path path = relatedToRootDirectory(filename);
        Files.createDirectories(path.getParent());
        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        return path;
    }

    @Override
    public void delete(String path) throws IOException {
        delete(Paths.get(path));
    }

    @Override
    public void delete(Path path) throws IOException {
        if (Files.exists(path)) {
            Files.delete(path);
        }
    }

    @Override
    public void delete(File file) throws IOException {
        if (file.getFileInfo() != null && file.getFileInfo().getUploadedName() != null) {
            delete(relatedToRootDirectory(file.getFileInfo().getUploadedName()));
        }
    }

    @Override
    public Path relatedToRootDirectory(String filename) {
        return relatedPath(filename, rootDireсtory);
    }

    @Override
    public String relatedToWebDirectory(String filename) {
        List<String> pathList = new ArrayList<>();
        pathList.add(baseHREF);
        pathList.add(webDocDirectory);
        pathList.add(filename);
        return String.join("/", pathList);
    }

    @Override
    public String relatedToWebImageDirectory(int width, int height, ImageConstant.Proportion proportion, String filename) {

        List<String> pathList = new ArrayList<>();
        pathList.add(baseHREF);
        pathList.add(webImageDirectory);
        pathList.add(String.valueOf(width));
        pathList.add(String.valueOf(height));
        pathList.add(proportion.toString());
        pathList.add(filename);

        return String.join("/", pathList);
    }

    @Override
    public Path relatedToImageDirectory(int width, int height, ImageConstant.Proportion proportion, String filename) {
        List<String> pathList = new ArrayList<>();
        pathList.add(imageResizedDirectory);
        pathList.add(String.valueOf(width));
        pathList.add(String.valueOf(height));
        pathList.add(proportion.toString());

        return relatedPath(filename, String.join(java.io.File.separator, pathList));
    }

    private Path relatedPath(String filename, String webDocDirectory) {
        if (filename != null && !filename.contains("/") && !filename.contains("\\")) {

            List<String> pathList = new ArrayList<>();
            pathList.add(webDocDirectory);
            pathList.addAll(getWithSubDirectoriesFilename(filename));

            Path path = Paths.get(String.join(java.io.File.separator, pathList));

            return path;
        }

        return null;
    }

//    @Override
//    public Path createTemporaryFileFromMultipart(MultipartFile multipartFile) throws IOException {
//
//        String filenameRandom = generateRandomFilename(multipartFile.getOriginalFilename());
//        List<String> pathList = Arrays.asList(
//                tmpDirectory,
//                generateRandomFilename(filenameRandom)
//        );
//
//        Path path = Paths.get(String.join(java.io.File.separator, pathList));
//
//        java.io.File convFile = path.toFile();
//        FileUtils.forceMkdirParent(convFile);
//        convFile.createNewFile();
//        FileOutputStream fos = new FileOutputStream(convFile);
//        fos.write(multipartFile.getBytes());
//        fos.close();
//
//        return path;
//    }

//    @Override
//    public String webPath(String filename) {
//        return String.join(java.io.File.separator, getWithSubDirectoriesFilename(filename));
//    }
//
//    @Override
//    public String webPath(Path path) {
//        String filepath = FilenameUtils.getBaseName(path.toFile().toString());
//        return String.join(java.io.File.separator, getWithSubDirectoriesFilename(filepath));
//    }

    @Override
    public String generateRandomFilename(String filename) {
        return RandomStringUtils.randomAlphabetic(12) + "." + StringUtils.getFilenameExtension(filename);
    }

    private List<String> getWithSubDirectoriesFilename(String filename) {
        int iterations = 3;
        final List<String> list = new ArrayList<>();
        final String baseName = FilenameUtils.getBaseName(filename);
        int min = Math.min(baseName.length(), iterations);

        for (int i = 0; i < min; i++) {
            list.add(String.valueOf(filename.charAt(i)));
        }

        list.add(filename);

        return list;
    }
}
