package ru.telenok.newspaper.core.converter.admin;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.telenok.newspaper.common.dto.admin.UserAuthorityDto;
import ru.telenok.newspaper.common.model.UserAuthority;

@Component
public class UserAuthorityAdminConverter implements Converter<UserAuthority, UserAuthorityDto> {

    @Override
    public UserAuthorityDto convert(UserAuthority userAuthority) {
        UserAuthorityDto userAuthorityDto = new UserAuthorityDto();
        userAuthorityDto.setId(userAuthority.getId());
        userAuthorityDto.setCode(userAuthority.getCode());
        userAuthorityDto.setDescription(userAuthority.getDescription());
        return userAuthorityDto;
    }
}
