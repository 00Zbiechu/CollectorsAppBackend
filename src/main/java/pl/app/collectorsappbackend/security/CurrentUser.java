package pl.app.collectorsappbackend.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.app.collectorsappbackend.exception.CustomErrorException;
import pl.app.collectorsappbackend.exception.errors.ErrorCodes;
import pl.app.collectorsappbackend.model.entity.UserEntity;
import pl.app.collectorsappbackend.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class CurrentUser {

    private final UserRepository userRepository;

    public UserEntity getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUsername(auth.getName()).orElseThrow(() ->
                new CustomErrorException("user", ErrorCodes.ENTITY_DOES_NOT_EXIST, HttpStatus.NOT_FOUND)
        );
    }
}
