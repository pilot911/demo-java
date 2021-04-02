package ru.telenok.newspaper.core.converter.admin;

import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.telenok.newspaper.common.dto.admin.UserDto;
import ru.telenok.newspaper.common.model.User;

import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class UserDtoAdminConverter implements Converter<UserDto, User> {

    private UserGroupDtoAdminConverter userGroupDtoConverter;

    @Override
    public User convert(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setActive(userDto.getActive());
        user.setGroups(userDto.getGroups().stream().map(userGroupDtoConverter::convert).collect(Collectors.toSet()));

        return user;
    }
}
