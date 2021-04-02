package ru.telenok.newspaper.core.converter.admin;

import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.telenok.newspaper.common.dto.admin.UserGroupDto;
import ru.telenok.newspaper.common.model.UserGroup;

import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class UserGroupDtoAdminConverter implements Converter<UserGroupDto, UserGroup> {

    private UserAuthorityDtoAdminConverter userAuthorityDtoConverter;

    @Override
    public UserGroup convert(UserGroupDto userGroupDto) {
        UserGroup userGroup = new UserGroup();
        userGroup.setId(userGroupDto.getId());
        userGroup.setTitle(userGroupDto.getTitle());
        userGroup.setCode(userGroupDto.getCode());
        userGroup.setAuthorities(userGroupDto.getAuthorities().stream().map(userAuthorityDtoConverter::convert).collect(Collectors.toSet()));

        return userGroup;
    }
}
