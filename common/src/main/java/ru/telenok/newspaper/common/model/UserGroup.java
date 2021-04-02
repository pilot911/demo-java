package ru.telenok.newspaper.common.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.*;
import org.javers.core.metamodel.annotation.ShallowReference;
import ru.telenok.newspaper.common.DaoConstants;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = DaoConstants.Table.UserGroupTable.TABLE_NAME)
public class UserGroup implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = DaoConstants.Table.ID)
    private Long id;

    @Column(name = DaoConstants.Table.TITLE)
    private String title;

    @Column(name = DaoConstants.Table.CODE)
    private String code;

    @CreationTimestamp
    @Column(name = DaoConstants.Table.CREATED_AT)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = DaoConstants.Table.UPDATED_AT)
    private LocalDateTime updatedAt;

    @JsonManagedReference
    @ManyToMany
    @Fetch(FetchMode.JOIN)
    @JoinTable(name = DaoConstants.Table.UserGroupToUserAuthorityTable.TABLE_NAME,
            joinColumns = {
                    @JoinColumn(name = DaoConstants.Table.UserGroupToUserAuthorityTable.GROUP_ID, nullable = false)
            },
            inverseJoinColumns = {
                    @JoinColumn(name = DaoConstants.Table.UserGroupToUserAuthorityTable.AUTHORITY_ID, nullable = false),
            }
    )
    private Set<UserAuthority> authorities = new HashSet<>();
}
