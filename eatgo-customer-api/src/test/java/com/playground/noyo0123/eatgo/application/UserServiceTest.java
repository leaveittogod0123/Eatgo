package com.playground.noyo0123.eatgo.application;

import com.playground.noyo0123.eatgo.domain.User;
import com.playground.noyo0123.eatgo.domain.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserService userService;


    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userRepository, passwordEncoder);
    }

    @Test
    public void registerUser() {
        String email = "tester3@example.com";
        String name = "Tester";
        String password = "test";

        userService.registerUser(email, name, password);
        verify(userRepository).save(any());
    }

    @Test
    public void registerUserWithExistedEmail() {
        String email = "tester@example.com";
        String name = "Tester";
        String password = "test";

        User user = User.builder().build();
        given(userRepository.findByEmail(email)).willReturn(Optional.of(user));

        assertThatThrownBy(() -> {
            userService.registerUser(email, name, password);
        }).isInstanceOf(EmailExistedException.class);

        verify(userRepository, never()).save(any());
    }
}