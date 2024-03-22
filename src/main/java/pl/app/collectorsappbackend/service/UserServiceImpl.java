package pl.app.collectorsappbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pl.app.collectorsappbackend.mapper.BaseMapper;
import pl.app.collectorsappbackend.mapper.UserMapper;
import pl.app.collectorsappbackend.model.dto.User;
import pl.app.collectorsappbackend.model.entity.UserEntity;
import pl.app.collectorsappbackend.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends BaseService<UserEntity, User> implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    protected JpaRepository<UserEntity, Long> getRepository() {
        return userRepository;
    }

    @Override
    protected BaseMapper<UserEntity, User> getMapper() {
        return userMapper;
    }

    public User login(User dto) {
        var user = userRepository.findByUsername(dto.username());
        if (user.isEmpty()) {
            return userMapper.toDTO(userRepository.save(userMapper.toEntity(dto)));
        }
        return userMapper.toDTO(user.get());
    }
}
