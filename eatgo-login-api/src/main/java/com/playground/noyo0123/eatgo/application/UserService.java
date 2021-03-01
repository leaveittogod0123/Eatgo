package com.playground.noyo0123.eatgo.application;

import com.playground.noyo0123.eatgo.domain.User;
import com.playground.noyo0123.eatgo.domain.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    UserRepository userRepository;

    PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }

    public User authenticate(String email, String password) {
        // TODO: ....
        User user = userRepository.findByEmail(email).orElseThrow(() -> new EmailNotExistedException(email));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new PasswordWrongException();
        }

        return user;
    }
}
