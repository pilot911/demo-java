package ru.telenok.newspaper.core.service.admin.impl;

import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.telenok.newspaper.common.model.Article;
import ru.telenok.newspaper.common.model.FileData;
import ru.telenok.newspaper.common.model.Tag;
import ru.telenok.newspaper.core.dao.ArticleRepository;
import ru.telenok.newspaper.core.dao.FileDataRepository;
import ru.telenok.newspaper.core.service.admin.ArticleServiceAdmin;
import ru.telenok.newspaper.core.service.admin.FileServiceAdmin;
import ru.telenok.newspaper.core.service.admin.TagServiceAdmin;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ArticleServiceAdminImpl implements ArticleServiceAdmin {

    private ArticleRepository articleRepository;
    private FileDataRepository fileDataRepository;
    private TagServiceAdmin tagService;
    private FileServiceAdmin fileServiceAdmin;

    @Override
    public Optional<Article> findById(Long id) {
        return articleRepository.findById(id);
    }

    @Override
    public List<Article> findByIdIn(Collection<Long> id) {
        return articleRepository.findByIdIn(id);
    }

    @Override
    public List<Article> find(Collection<Long> ids, Boolean active, Collection<Tag> tags) {
        return articleRepository.findByIdIn(ids);
    }

    @Override
    public Slice<Article>
    findAll(Pageable pageable) {
        return articleRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Article save(Article article) {

        final Article originalArticle = articleRepository.findById(article.getId()).orElse(new Article());
        final AtomicReference<Tag> tagAtomicReference = new AtomicReference<>(null);
        Set<Tag> tags = new HashSet<>();
        Set<FileData> fileDatas = article.getFiles();

        if (Objects.nonNull(article.getTag())) {
            final Long tagId = article.getTag().getId();
            tagService.findById(tagId).ifPresent(tagAtomicReference::set);
        }

        article.setTag(tagAtomicReference.get());

        if (CollectionUtils.isNotEmpty(article.getTags())) {
            tags = article.getTags().stream().map(t -> tagService.findById(t.getId())).filter(Optional::isPresent).map(Optional::get).collect(Collectors.toSet());
        }

        article.setTags(tags);

        if (CollectionUtils.isNotEmpty(fileDatas)) {

            fileDataRepository.deleteInBatch(originalArticle.getFiles());

            final AtomicReference<Article> articleAtomicReference = new AtomicReference<>(article);

            fileDatas = fileDatas.stream().map(fd -> {

                FileData fileData = new FileData();

                fileServiceAdmin.findById(fd.getFile().getId()).ifPresent(f -> {

                    String description = StringUtils.trim(fd.getDescription());

                    if (StringUtils.isBlank(description)) {
                        description = f.getDescription();
                    }

                    fileData.setId(null);
                    fileData.setFile(f);
                    fileData.setDescription(description);
                    fileData.setArticle(articleAtomicReference.get());
                });

                return fileData;
            }).collect(Collectors.toSet());

            if (!fileDatas.isEmpty()) {
                fileDataRepository.saveAll(fileDatas);
                fileDataRepository.flush();
            }
        }

        article.setFiles(fileDatas);

        originalArticle.merge(article);

        return articleRepository.saveAndFlush(originalArticle);
    }
}
