package com.playground.noyo0123.eatgo.interfaces;

import com.playground.noyo0123.eatgo.application.UserService;
import com.playground.noyo0123.eatgo.domain.User;
import com.playground.noyo0123.eatgo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class SessionController {

    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    public SessionController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/session")
    public ResponseEntity<SessionResponseDto> create(
            @RequestBody sessionRequestDto resource
    ) throws URISyntaxException {
        String email = resource.getEmail();
        String password = resource.getPassword();
        User user = userService.authenticate(email, password);
      String accessToken = jwtUtil.createToken(user.getId(), user.getName(), user.isRestaurantOwner() ? user.getRestaurantId() : null);
      SessionResponseDto sessionResponseDto = SessionResponseDto.builder().accessToken(accessToken).build();
      String url = "/session";
      return ResponseEntity.created(new URI(url)).body(sessionResponseDto);
  }

}
