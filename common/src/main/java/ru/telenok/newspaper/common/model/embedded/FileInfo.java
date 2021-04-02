package ru.telenok.newspaper.common.model.embedded;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.telenok.newspaper.common.DaoConstants;
import ru.telenok.newspaper.common.FileConstant;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
@Access(AccessType.FIELD)
public class FileInfo implements Serializable {

    @Column(name = DaoConstants.Table.FileTable.UPLOADED_NAME)
    private String uploadedName;

    @Column(name = DaoConstants.Table.FileTable.UPLOADED_MIME_TYPE)
    private String uploadedMimeType;

    @Column(name = DaoConstants.Table.FileTable.UPLOADED_EXTENSION)
    private String uploadedExtension;

    @Column(name = DaoConstants.Table.FileTable.UPLOADED_SIZE)
    private long uploadedSize;

    @Column(name = DaoConstants.Table.FileTable.UPLOADED_WIDTH)
    private int uploadedWidth;

    @Column(name = DaoConstants.Table.FileTable.UPLOADED_HEIGHT)
    private int uploadedHeight;
}
