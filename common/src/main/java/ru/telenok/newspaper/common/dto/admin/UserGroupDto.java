package ru.telenok.newspaper.common.dto.admin;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserGroupDto {
    private long id;
    private String title;
    private String code;
    private List<UserAuthorityDto> authorities = new ArrayList<>();
}
