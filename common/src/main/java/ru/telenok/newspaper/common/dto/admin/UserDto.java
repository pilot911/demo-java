package ru.telenok.newspaper.common.dto.admin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDto {
    private long id;
    private String username;
    @JsonIgnore
    private String password;
    private Boolean active;
    private List<UserGroupDto> groups = new ArrayList<>();
}
