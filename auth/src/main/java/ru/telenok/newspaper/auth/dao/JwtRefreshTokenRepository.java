package ru.telenok.newspaper.auth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.telenok.newspaper.auth.model.JwtRefreshToken;

import java.util.Optional;

@Repository
public interface JwtRefreshTokenRepository extends JpaRepository<JwtRefreshToken, Long> {

    @Query("select t from JwtRefreshToken t where t.userId = ?1 and t.code = ?2")
    Optional<JwtRefreshToken> findByUserAndTokenCode(Long userId, String refreshTokenCode);
}

