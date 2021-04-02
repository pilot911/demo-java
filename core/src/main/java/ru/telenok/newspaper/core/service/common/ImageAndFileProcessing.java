package ru.telenok.newspaper.core.service.common;

import ru.telenok.newspaper.common.ImageConstant;
import ru.telenok.newspaper.common.model.File;
import ru.telenok.newspaper.core.service.common.impl.ImageAndFileProcessingImpl;

import java.io.InputStream;
import java.nio.file.Path;

public interface ImageAndFileProcessing {
    boolean isImage(String filename);

    boolean isImage(File file);

    boolean isImage(java.io.File file);

    boolean isImage(InputStream file);

    ImageAndFileProcessingImpl.Dimension getDimension(java.io.File in);

    ImageAndFileProcessingImpl.Dimension getDimension(InputStream in);

    String getMimeType(java.io.File in);

    String getMimeType(InputStream in);

    boolean resize(Path from, Path to, int width, int height, ImageConstant.Proportion proportion);

    boolean resize(java.io.File from, java.io.File to, int width, int height, ImageConstant.Proportion proportion);
}
