package ru.telenok.newspaper.common.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.*;
import ru.telenok.newspaper.common.DaoConstants;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode
@Data
@Entity
@Table(name = DaoConstants.Table.UserTable.TABLE_NAME)
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = DaoConstants.Table.ID)
    private Long id;

    @Column(name = DaoConstants.Table.UserTable.USERNAME)
    private String username;

    @Column(name = DaoConstants.Table.UserTable.PASSWORD)
    private String password;

    @Column(name = DaoConstants.Table.ACTIVE)
    private Boolean active;

    @CreationTimestamp
    @Column(name = DaoConstants.Table.CREATED_AT)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = DaoConstants.Table.UPDATED_AT)
    private LocalDateTime updatedAt;

    @JsonManagedReference
    @ManyToMany
    @Fetch(FetchMode.JOIN)
    @JoinTable(name = DaoConstants.Table.UserGroupToUserTable.TABLE_NAME,
            joinColumns = {
                    @JoinColumn(name = DaoConstants.Table.UserGroupToUserTable.USER_ID, nullable = false),
            },
            inverseJoinColumns = {
                    @JoinColumn(name = DaoConstants.Table.UserGroupToUserTable.GROUP_ID, nullable = false)
            }
    )
    private Set<UserGroup> groups = new HashSet<>();
}
