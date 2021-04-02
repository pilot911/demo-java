package ru.telenok.newspaper.common.dto.admin;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserAuthorityDto {
    private long id;
    private String code;
    private String description;
    private List<UserGroupDto> groups = new ArrayList<>();
}
