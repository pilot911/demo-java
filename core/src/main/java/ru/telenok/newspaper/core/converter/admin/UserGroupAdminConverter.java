package ru.telenok.newspaper.core.converter.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.telenok.newspaper.common.dto.admin.UserGroupDto;
import ru.telenok.newspaper.common.model.UserGroup;

import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class UserGroupAdminConverter implements Converter<UserGroup, UserGroupDto> {

    private UserAuthorityAdminConverter userAuthorityConverter;

    @Override
    public UserGroupDto convert(UserGroup userGroup) {
        UserGroupDto userGroupDto = new UserGroupDto();
        userGroupDto.setId(userGroup.getId());
        userGroupDto.setTitle(userGroup.getTitle());
        userGroupDto.setCode(userGroup.getCode());
        userGroupDto.setAuthorities(userGroup.getAuthorities().stream().map(userAuthorityConverter::convert).collect(Collectors.toList()));

        return userGroupDto;
    }
}
