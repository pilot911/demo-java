package ru.telenok.newspaper.auth.service.impl;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.telenok.newspaper.auth.model.JwtUserDetails;
import ru.telenok.newspaper.auth.service.JwtUserDetailsService;
import ru.telenok.newspaper.common.model.User;
import ru.telenok.newspaper.common.model.UserAuthority;
import ru.telenok.newspaper.common.model.UserGroup;
import ru.telenok.newspaper.core.service.admin.UserGroupServiceAdmin;
import ru.telenok.newspaper.core.service.admin.UserServiceAdmin;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JwtUserDetailsServiceImpl implements JwtUserDetailsService {

    private UserServiceAdmin userServiceAdmin;
    private UserGroupServiceAdmin userGroupServiceAdmin;
    private MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

    public JwtUserDetailsServiceImpl(UserServiceAdmin userServiceAdmin, UserGroupServiceAdmin userGroupServiceAdmin) {
        this.userServiceAdmin = userServiceAdmin;
        this.userGroupServiceAdmin = userGroupServiceAdmin;
    }

    @Override
    public JwtUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userServiceAdmin.findByUsernameWithGroups(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                this.messages.getMessage("Jwt.notFound",
                                        new Object[]{username}, "Username {0} not found"))
                );

        final List<UserAuthority> authorities = user.getGroups().stream()
                .map(UserGroup::getAuthorities)
                .flatMap(Set::stream)
                .collect(Collectors.toList());

        return new JwtUserDetails(user.getId(), user.getUsername(), user.getPassword(), authorities);
    }
}
