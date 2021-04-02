package ru.telenok.newspaper.auth.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import ru.telenok.newspaper.common.CommonConstants;

import javax.persistence.*;

@Data
public class JwtAccessToken {
    private String code;
}
