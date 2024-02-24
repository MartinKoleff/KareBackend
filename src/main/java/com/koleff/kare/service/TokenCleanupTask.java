package com.koleff.kare.service;

import com.koleff.kare.models.entity.Token;
import com.koleff.kare.repository.TokenRepository;
import jakarta.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Component
public class TokenCleanupTask {

    private final TokenRepository tokenRepository;
    private final TaskScheduler taskScheduler;

    @Value("${jwt.access-token.expiration-time}")
    private long accessTokenExpirationTime;

    @Value("${jwt.refresh-token.expiration-time}")
    private long refreshTokenExpirationTime;

    private final static Logger logger = LogManager.getLogger(TokenCleanupTask.class);

    @Autowired
    public TokenCleanupTask(TokenRepository tokenRepository, TaskScheduler taskScheduler) {
        this.tokenRepository = tokenRepository;
        this.taskScheduler = taskScheduler;
    }

    @PostConstruct
    private void scheduleTokenCleanup() {
        taskScheduler.scheduleAtFixedRate(
                this::cleanUpExpiredTokens,
                Instant.now(),
                Duration.of(accessTokenExpirationTime / 1000, ChronoUnit.SECONDS)
        );
    }

    public void cleanUpExpiredTokens() {
        logger.info("Updating token expiration in DB.");

        List<Token> expiredTokens = tokenRepository.findAll()
                .stream()
                .filter(token -> token.expiryTime.isBefore(Instant.now()))
                .filter(token -> !token.expired)
                .toList();

        for (Token token : expiredTokens) {
            logger.info(String.format("Token has expired -> %s", token));

            token.setExpired(true);
            tokenRepository.save(token);
        }
    }
}