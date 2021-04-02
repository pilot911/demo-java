package ru.telenok.newspaper.common.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FileAdminDto {
    private long id;
    private String title;
    private String url;
    private String description;
    private Boolean active;
    private UserDto user;
    private List<TagAdminDto> tags = new ArrayList<>();
    private List<String> storedFiles = new ArrayList<>();

    private String uploadedName;
    private Path uploadedFilesystemPath;
//    private String uploadedWebPath;
    private String uploadedMimeType;
    private String uploadedExtension;
    private long uploadedSize;
    private int uploadedWidth;
    private int uploadedHeight;
}
