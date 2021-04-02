package ru.telenok.newspaper.auth.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Service;
import ru.telenok.newspaper.auth.config.AuthConstants;
import ru.telenok.newspaper.auth.model.JwtUserDetails;
import ru.telenok.newspaper.auth.service.SecurityService;
import ru.telenok.newspaper.common.model.Article;
import ru.telenok.newspaper.common.model.User;
import ru.telenok.newspaper.core.dao.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class SecurityServiceImpl implements SecurityService {

    private UserRepository userRepository;

    @Override
    public boolean canCreateArticle() {
        final List<String> rolesAccept = Arrays.asList(
                AuthConstants.Roles.ROLE_ADMIN,
                AuthConstants.Roles.ROLE_CREATE_ARTICLE
        );

        return hasAcceptedRoles(rolesAccept);
    }

    @Override
    public boolean canUpdateArticle(Article article) {

        return false;
//
//        List<String> rolesAccept = Arrays.asList(
//                AuthConstants.AuthRole.ROLE_ADMIN,
//                AuthConstants.AuthRole.ROLE_UPDATE_ARTICLE
//        );
//
//        if (hasAcceptedRoles(rolesAccept)) {
//            return true;
//        } else {
//            rolesAccept = Collections.singletonList(
//                    AuthConstants.AuthRole.ROLE_UPDATE_ARTICLE_OWN
//            );
//
//            return hasAcceptedRoles(rolesAccept) && article.getUser().getId().equals(getCurrentUserDetails().getId());
//        }

    }

    @Override
    public boolean canDeleteArticle(Article article) {
        return false;
    }

    public User getCurrentUser() {
        final JwtUserDetails currentUserDetails = getCurrentUserDetails();
        final User user = userRepository.findById(currentUserDetails.getId()).orElseThrow(() -> new SessionAuthenticationException("Unauthorized"));
        return user;
    }

    public JwtUserDetails getCurrentUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.isAuthenticated()) {
            final JwtUserDetails userDetails = (JwtUserDetails)authentication.getPrincipal();
            return userDetails;
        } else {
            throw new SessionAuthenticationException("Unauthorized");
        }
    }

    private boolean hasAcceptedRoles(List<String> rolesAccept) {
        final Set<String> rolesUser = getCurrentUserDetails().getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());

        rolesUser.retainAll(rolesAccept);

        return !rolesUser.isEmpty();
    }
}
