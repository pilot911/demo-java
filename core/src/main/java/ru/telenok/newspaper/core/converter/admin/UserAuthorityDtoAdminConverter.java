package ru.telenok.newspaper.core.converter.admin;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.telenok.newspaper.common.dto.admin.UserAuthorityDto;
import ru.telenok.newspaper.common.model.UserAuthority;

@Component
public class UserAuthorityDtoAdminConverter implements Converter<UserAuthorityDto, UserAuthority> {

    @Override
    public UserAuthority convert(UserAuthorityDto userAuthorityDto) {
        UserAuthority userAuthority = new UserAuthority();
        userAuthority.setId(userAuthorityDto.getId());
        userAuthority.setCode(userAuthorityDto.getCode());
        userAuthority.setDescription(userAuthorityDto.getDescription());

        return userAuthority;
    }
}
