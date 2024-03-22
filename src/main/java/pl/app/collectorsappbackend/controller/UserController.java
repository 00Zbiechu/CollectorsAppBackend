package pl.app.collectorsappbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.app.collectorsappbackend.model.entity.dto.User;
import pl.app.collectorsappbackend.service.UserService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("http://localhost:4200")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> login(@RequestBody User user) {
        return new ResponseEntity<>(userService.login(user), HttpStatus.CREATED);
    }
}
