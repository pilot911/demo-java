package ru.telenok.newspaper.auth.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import ru.telenok.newspaper.common.CommonConstants;
import ru.telenok.newspaper.common.DaoConstants;

import javax.persistence.*;

@Data
@Entity
@Table(name = DaoConstants.Table.JwtRefreshToken.TABLE_NAME)
public class JwtRefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = DaoConstants.Table.ID)
    private Long id;

    @Column(name = DaoConstants.Table.JwtRefreshToken.USER_ID)
    private Long userId;

    @Column(name = DaoConstants.Table.JwtRefreshToken.REFRESH_TOKEN_CODE)
    private String code;
}
