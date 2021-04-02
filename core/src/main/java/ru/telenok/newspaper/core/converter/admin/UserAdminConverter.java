package ru.telenok.newspaper.core.converter.admin;

import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.telenok.newspaper.common.dto.admin.UserDto;
import ru.telenok.newspaper.common.model.User;

import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class UserAdminConverter implements Converter<User, UserDto> {

    private UserGroupAdminConverter userGroupConverter;

    @Override
    public UserDto convert(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setActive(user.getActive());
        userDto.setGroups(user.getGroups().stream().map(userGroupConverter::convert).collect(Collectors.toList()));

        return userDto;
    }
}
