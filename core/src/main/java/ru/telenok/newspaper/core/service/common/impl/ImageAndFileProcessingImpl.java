package ru.telenok.newspaper.core.service.common.impl;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tika.Tika;
import org.apache.tika.exception.CorruptedFileException;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Service;
import ru.telenok.newspaper.common.FileConstant;
import ru.telenok.newspaper.common.ImageConstant;
import ru.telenok.newspaper.common.model.File;
import ru.telenok.newspaper.core.service.common.ImageAndFileProcessing;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Optional;

@Service
@Slf4j
public class ImageAndFileProcessingImpl implements ImageAndFileProcessing {

    public boolean isImage(String path) {
        return isImage(new java.io.File(path));
    }

    public boolean isImage(File file) {
        return FileConstant.imageExtensions.contains(file.getFileInfo().getUploadedExtension().toLowerCase())
                && FileConstant.imageMimeTypes.contains(file.getFileInfo().getUploadedMimeType().toLowerCase());
    }

    public boolean isImage(java.io.File file) {
        return isImage(file, null);
    }

    public boolean isImage(InputStream in) {
        return isImage(null, in);
    }

    @Override
    public Dimension getDimension(java.io.File file) {
        return getDimension(file, null);
    }

    @Override
    public Dimension getDimension(InputStream in) {
        return getDimension(null, in);
    }

    @Override
    public String getMimeType(java.io.File file) {
        return getMimeType(file, null);
    }

    @Override
    public String getMimeType(InputStream in) {
        return getMimeType(null, in);
    }

    @Override
    public boolean resize(Path from, Path to, int width, int height, ImageConstant.Proportion proportion) {
        return resize(from.toFile(), to.toFile(), width, height, proportion);
    }

    @Override
    public boolean resize(java.io.File from, java.io.File to, int width, int height, ImageConstant.Proportion proportion) {
        try {
            if (proportion == null) {
                proportion = ImageConstant.Proportion.STRONG;
            }

            final String extension = FilenameUtils.getExtension(from.getName());

            if (!isImage(from) || !FileConstant.imageExtensions.contains(extension)) {
                throw new CorruptedFileException("File " + from.toPath() + " is not image!");
            }

            FileUtils.forceMkdirParent(to);

            BufferedImage bufferedImage = ImageIO.read(from);
            BufferedImage temp = resizeImage(bufferedImage, width, height, proportion);
            ImageIO.write(temp, extension, to);
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }

        return true;
    }

    private BufferedImage resizeImage(final Image image, int width, int height, ImageConstant.Proportion proportion) {
        final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        final Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setComposite(AlphaComposite.Src);
        //below three lines are for RenderingHints for better image quality at cost of higher processing time
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (proportion == ImageConstant.Proportion.FILL_FULL) {

            float proportionWidth = (float) image.getWidth(null) / width;
            float proportionHeight = (float) image.getHeight(null) / height;

            int x1, x2, y1, y2;

//            if (proportionWidth > proportionHeight) {
//                x1 = (image.getWidth(null) - width) / 2;
//                x2 = x1 + width;
//                y1 = (image.getHeight(null) - height) / 2;
//                y2 = y1 + height;
//            } else {
                x1 = (image.getWidth(null) - width) / 2;
                x2 = x1 + width;
                y1 = (image.getHeight(null) - height) / 2;
                y2 = y1 + height;
//            }
            graphics2D.drawImage(image, 0, 0, width, height, x1, y1, x2, y2, null);
        } else {
            graphics2D.drawImage(image, 0, 0, width, height, null);
        }
        graphics2D.dispose();
        return bufferedImage;
    }


    private Dimension getDimension(java.io.File file, InputStream in) {
        final Dimension dimension = new Dimension();
        try {
            if (in == null) {
                Optional.of(ImageIO.read(file)).ifPresent(reader -> {
                    dimension.setHeight(reader.getHeight());
                    dimension.setWidth(reader.getWidth());
                });
            } else {
                BufferedImage reader = ImageIO.read(in);
                dimension.setHeight(reader.getHeight());
                dimension.setWidth(reader.getWidth());
            }
        } catch (IOException e) {
        }

        return dimension;
    }

    private boolean isImage(java.io.File file, InputStream in) {
        final String mimeType = getMimeType(file, in);
        return FileConstant.imageMimeTypes.contains(mimeType.toLowerCase());
    }

    private String getMimeType(java.io.File file, InputStream in) {
        try {
            if (in == null) {
                return new Tika().detect(file);
            } else {
                return new Tika().detect(in);
            }
        } catch (IOException e) {
            return StringUtils.EMPTY;
        }
    }

    @Data
    public static class Dimension {
        int height;
        int width;
    }
}
