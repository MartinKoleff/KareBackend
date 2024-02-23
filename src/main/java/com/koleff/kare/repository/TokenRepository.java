package com.koleff.kare.repository;

import com.koleff.kare.models.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query(value = "SELECT * FROM token_table t INNER JOIN user_table u ON t.user.id = u.id " +
            "WHERE u.id = $1 AND (t.expired = false OR t.revoked = false)", nativeQuery = true)
    List<Token> findAllValidTokenByUser(String id);

    Optional<Token> findByToken(String token);
}
