package ru.telenok.newspaper.common.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.*;
import ru.telenok.newspaper.common.CommonConstants;
import ru.telenok.newspaper.common.DaoConstants;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
@Data
@Entity
@Table(name = DaoConstants.Table.TagTable.TABLE_NAME)
public class Tag implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = DaoConstants.Table.ID)
    private Long id;

    @Column(name = DaoConstants.Table.TITLE)
    private String title;

    @Column(name = DaoConstants.Table.DESCRIPTION)
    private String description;

    @Column(name = DaoConstants.Table.URL)
    private String url;

    @Column(name = DaoConstants.Table.CODE)
    private String code;

    @Column(name = DaoConstants.Table.ACTIVE)
    private Boolean active;

    @CreationTimestamp
    @Column(name = DaoConstants.Table.CREATED_AT)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = DaoConstants.Table.UPDATED_AT)
    private LocalDateTime updatedAt;
}
