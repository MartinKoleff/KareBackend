package com.koleff.kare.auth.service;

import com.koleff.kare.auth.models.entity.User;
import com.koleff.kare.auth.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final static Logger logger = LogManager.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    /**
     * Used in AuthenticationManager
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info(String.format("Fetching user details for username %s.", username));

        //TODO: throw InvalidTokenException?
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User with username %s doesn't exist in the DB.", username)
                ));
    }

    public Optional<User> getUserById(String userId) {
        return userRepository.findById(userId);
    }

    public User getUserByEmail(String email) {
        logger.info(String.format("Fetching user details for email %s.", email));
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException( //TODO: create custom error?
                        String.format("User with email %s doesn't exist in the DB.", email)
                ));
    }

    public User getUserByUsername(String username) {
        logger.info(String.format("Fetching user details for username %s.", username));
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException( //TODO: create custom error?
                        String.format("User with email %s doesn't exist in the DB.", username)
                ));
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
