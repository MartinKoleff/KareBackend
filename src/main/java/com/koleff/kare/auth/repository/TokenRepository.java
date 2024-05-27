package com.koleff.kare.auth.repository;

import com.koleff.kare.auth.models.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query("SELECT t FROM Token t WHERE t.userId = :id AND (t.expired = false OR t.revoked = false)")
    List<Token> findAllValidTokenByUser(String id);

    Optional<Token> findByToken(String token);
}
