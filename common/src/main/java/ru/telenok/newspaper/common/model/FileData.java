package ru.telenok.newspaper.common.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import ru.telenok.newspaper.common.DaoConstants;
import ru.telenok.newspaper.common.model.embedded.FileInfo;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = DaoConstants.Table.FileDataTable.TABLE_NAME)
public class FileData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = DaoConstants.Table.ID)
    private Long id;

    @Column(name = DaoConstants.Table.DESCRIPTION)
    private String description;

    @Column(name = DaoConstants.Table.SORT)
    private int sort;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = DaoConstants.Table.FileDataTable.FILE_ID)
    private File file;

    @EqualsAndHashCode.Exclude
    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = DaoConstants.Table.FileDataTable.ARTICLE_ID)
    private Article article;
}
