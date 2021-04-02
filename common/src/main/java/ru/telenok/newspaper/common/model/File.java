package ru.telenok.newspaper.common.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.multipart.MultipartFile;
import ru.telenok.newspaper.common.DaoConstants;
import ru.telenok.newspaper.common.model.embedded.FileInfo;

import javax.persistence.*;
import java.io.Serializable;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = DaoConstants.Table.FileTable.TABLE_NAME)
public class File implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = DaoConstants.Table.ID)
    private Long id;

    @Column(name = DaoConstants.Table.TITLE)
    private String title;

    @Column(name = DaoConstants.Table.DESCRIPTION)
    private String description;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = DaoConstants.Table.TagFileTable.TABLE_NAME,
            joinColumns = {
                    @JoinColumn(name = DaoConstants.Table.TagFileTable.FILE_ID, nullable = false)
            },
            inverseJoinColumns = {
                    @JoinColumn(name = DaoConstants.Table.TagFileTable.TAG_ID, nullable = false),
            }
    )
    private Set<Tag> tags = new HashSet<>();

//    @JsonManagedReference
//    @OneToMany(mappedBy = "file", fetch = FetchType.EAGER)
//    private Set<FileData> files = new HashSet<>();

    @CreationTimestamp
    @Column(name = DaoConstants.Table.CREATED_AT)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = DaoConstants.Table.UPDATED_AT)
    private LocalDateTime updatedAt;

    @Column(name = DaoConstants.Table.ACTIVE)
    private Boolean active;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = DaoConstants.Table.USER_ID, nullable = false)
    private User user;

    @Embedded
    private FileInfo fileInfo;

    public File merge(File o) {
        if (o.active != null) {
            setActive(o.active);
        }

        if (o.getTitle() != null) {
            setTitle(o.title);
        }

        if (o.getDescription() != null) {
            setDescription(o.description);
        }

        if (o.getActive() != null) {
            setActive(o.active);
        }

        if (o.getUser() != null) {
            setUser(o.user);
        }

        if (o.getFileInfo() != null && o.getFileInfo().getUploadedName() != null) {
            setFileInfo(o.getFileInfo());
        }

        return this;
    }
}
