package pl.app.collectorsappbackend.service;

import pl.app.collectorsappbackend.model.entity.dto.User;

public interface UserService {

    User login(User user);
}
