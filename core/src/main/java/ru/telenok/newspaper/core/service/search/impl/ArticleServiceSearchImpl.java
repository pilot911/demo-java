package ru.telenok.newspaper.core.service.search.impl;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import ru.telenok.newspaper.common.CommonConstants;
import ru.telenok.newspaper.common.model.Article;
import ru.telenok.newspaper.core.dao.ArticleRepository;
import ru.telenok.newspaper.core.service.search.ArticleServiceSearch;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class ArticleServiceSearchImpl implements ArticleServiceSearch {

    private ArticleRepository articleRepository;

    @PersistenceContext(unitName = CommonConstants.Beans.ENTITY_MANAGER_FACTORY)
    private EntityManager em;

    public ArticleServiceSearchImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public Optional<Article> findById(Long id) {
        return articleRepository.findById(id);
    }

    @Override
    public Slice<Article> findAll(Pageable pageable) {
        return articleRepository.findAll(pageable);
    }

    @Override
    public List<Article> findByMustAndShouldTags(List<String> tagCodesMustBe, Pageable pageable) {

        final List<String> must = tagCodesMustBe.stream().filter(Predicate.not(StringUtils::isBlank)).collect(Collectors.toList());

        final StringBuilder queryStringBuilder = new StringBuilder("SELECT a FROM Article a ");

        final ArrayList<String> joinTable = new ArrayList<>();
        final ArrayList<String> whereJoin = new ArrayList<>();

        if (!must.isEmpty()) {
            final ArrayList<String> joinPlaceholder = new ArrayList<>();

            for (int i = 0; i < must.size(); i++) {

                final String tableAlias = "ts" + i;

                joinTable.add("INNER JOIN a.tags " + tableAlias + " ");
                joinPlaceholder.add(tableAlias + ".code = :ident" + i + " AND " + tableAlias + ".active = true");
            }
            whereJoin.add(" (" + String.join(" AND ", joinPlaceholder) + ") ");
        }

        if (!joinTable.isEmpty()) {
            queryStringBuilder.append(String.join(" ", joinTable));
        }

        queryStringBuilder.append(" WHERE a.active = true ");

        if (!whereJoin.isEmpty()) {
            queryStringBuilder.append(" AND ").append(String.join(" AND ", whereJoin));
        }

        queryStringBuilder.append(" ORDER BY ").append(":order");

        final String jppql = queryStringBuilder.toString();

        final TypedQuery<Article> query = em.createQuery(jppql, Article.class);

        if (!must.isEmpty()) {
            for (int i = 0; i < must.size(); i++) {
                query.setParameter("ident" + i, must.get(i));
            }
        }

        query.setParameter("order", "a.started_at DESC");

        query.setMaxResults(pageable.getPageSize());

        final List<Article> resultList = query.getResultList();

        final List<Long> ids = resultList.stream().map(el -> el.getId()).collect(Collectors.toList());

        final List<Article> articles = articleRepository.findByIdIn(ids);

        return articles;
    }
}
