package ru.telenok.newspaper.common.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.*;
import ru.telenok.newspaper.common.DaoConstants;
import ru.telenok.newspaper.common.statemachine.state.ArticleState;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = DaoConstants.Table.ArticleTable.TABLE_NAME)
public class Article implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = DaoConstants.Table.ID)
    private Long id;

    @Column(name = DaoConstants.Table.TITLE)
    private String title;

    @Column(name = DaoConstants.Table.ArticleTable.CONTENT_SHORT)
    private String contentShort;

    @Column(name = DaoConstants.Table.ArticleTable.CONTENT)
    private String content;

    @Column(name = DaoConstants.Table.URL)
    private String url;

    @JsonManagedReference
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = DaoConstants.Table.ArticleTable.TAG_ID)
    private Tag tag;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    @JoinTable(
            name = DaoConstants.Table.TagArticleTable.TABLE_NAME,
            joinColumns = {
                    @JoinColumn(name = DaoConstants.Table.TagArticleTable.ARTICLE_ID, nullable = false)
            },
            inverseJoinColumns = {
                    @JoinColumn(name = DaoConstants.Table.TagArticleTable.TAG_ID, nullable = false),
            }
    )
    private Set<Tag> tags = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @JsonManagedReference
    @OneToMany(mappedBy = "article", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<FileData> files = new HashSet<>();

    @CreationTimestamp
    @Column(name = DaoConstants.Table.CREATED_AT)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = DaoConstants.Table.UPDATED_AT)
    private LocalDateTime updatedAt;

    @Column(name = DaoConstants.Table.STARTED_AT)
    private LocalDateTime startAt;

    @Column(name = DaoConstants.Table.ACTIVE)
    private Boolean active;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = DaoConstants.Table.USER_ID, nullable = false)
    private User user;

    @Enumerated
    @Column(name = DaoConstants.Table.STATE)
    private ArticleState state;

    public void merge(Article article) {

        if (StringUtils.isNoneBlank(article.getTitle())) {
            this.setTitle(article.getTitle());
        }

        if (StringUtils.isNoneBlank(article.getContentShort())) {
            this.setContentShort(article.getContentShort());
        }

        if (StringUtils.isNoneBlank(article.getContent())) {
            this.setContent(article.getContent());
        }

        if (StringUtils.isNoneBlank(article.getUrl())) {
            this.setUrl(article.getUrl());
        }

        if (article.getTag() != null) {
            this.setTag(article.getTag());
        }

        this.setTags(CollectionUtils.isEmpty(article.getTags()) ? Collections.emptySet() : article.getTags());

        this.setFiles(CollectionUtils.isEmpty(article.getFiles()) ? Collections.emptySet() : article.getFiles());

        this.setUpdatedAt(article.getUpdatedAt() == null ? this.getUpdatedAt() : article.getUpdatedAt());

        this.setStartAt(article.getStartAt() == null ? this.getStartAt() : article.getStartAt());

        this.setActive(article.getActive() == null ? this.getActive() : article.getActive());

        this.setUser(article.getUser() == null ? this.getUser() : article.getUser());

        this.setState(article.getState() == null ? this.getState() : article.getState());
    }
}
