package com.playground.noyo0123.eatgo.application;

import com.playground.noyo0123.eatgo.domain.User;
import com.playground.noyo0123.eatgo.domain.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> getUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public User addUser(String email, String name) {
        User user = User.builder()
                .name(name)
                .email(email)
                .level(1L)
                .build();
        return userRepository.save(user);
    }

    public User updateUser(Long id, String email, String name, Long level) {
        // TODO: restaurantService 예외 처리 참고.
        User user = userRepository.findById(id).orElse(null);
        user.setName(name);
        user.setEmail(email);
        user.setLevel(level);
        return user;
    }

    public User deactivateUser(Long id) {
        // TODO: 작업 필요함.
        User user = userRepository.findById(id).orElse(null);
//        user.setName(name);
//        user.setEmail(email);
//        user.setLevel(level);
//        return user;
        user.deactivate();

        return user;
    }
}
