package ru.telenok.newspaper.auth.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.telenok.newspaper.auth.model.JwtUserDetails;
import ru.telenok.newspaper.common.model.Article;
import ru.telenok.newspaper.common.model.User;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public interface SecurityService {

    User getCurrentUser();

    JwtUserDetails getCurrentUserDetails();

    boolean canCreateArticle();

    boolean canUpdateArticle(Article article);

    boolean canDeleteArticle(Article article);
}
