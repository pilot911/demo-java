package ru.telenok.newspaper.common;

import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.List;

public interface FileConstant {

    List<String> imageMimeTypes = Arrays.asList(MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_PNG_VALUE);
    List<String> imageExtensions = Arrays.asList("jpg", "jpeg", "png", "gif");
    List<String> allowUploadMimeType = Arrays.asList(MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_PNG_VALUE);
}
