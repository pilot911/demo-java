package ru.telenok.newspaper.common.request;

//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Objects;
import java.util.Optional;

public class CustomPageable implements Pageable {

    private int limitMax = 10;
    private int page = 0;

    private Pageable pageable;

    public CustomPageable(Pageable pageable) {
        if (Objects.isNull(pageable)) {
            this.pageable = PageRequest.of(this.page, this.limitMax);
        } else {
            this.pageable = pageable;
        }
    }

    public CustomPageable(Pageable pageable, int limitMax) {
        this(pageable);

        if (limitMax != 0) {
            this.limitMax = limitMax;
        }
    }

    @Override
    public int getPageNumber() {
        return pageable.getPageNumber();
    }

    @Override
    public int getPageSize() {
        return pageable.getPageSize();
    }

    @Override
    public long getOffset() {
        return pageable.getOffset();
    }

    @Override
    public Sort getSort() {
        return pageable.getSort();
    }

    @Override
    public Pageable next() {
        return pageable.next();
    }

    @Override
    public Pageable previousOrFirst() {
        return pageable.previousOrFirst();
    }

    @Override
    public Pageable first() {
        return pageable.first();
    }

    @Override
    public boolean hasPrevious() {
        return pageable.hasPrevious();
    }

    @Override
    public boolean isPaged() {
        return pageable.isPaged();
    }

    @Override
    public boolean isUnpaged() {
        return pageable.isUnpaged();
    }

    @Override
    public Sort getSortOr(Sort sort) {
        return pageable.getSortOr(sort);
    }

    @Override
    public Optional<Pageable> toOptional() {
        return pageable.toOptional();
    }
}
