package pl.app.collectorsappbackend.mapper;

import org.mapstruct.Mapper;
import pl.app.collectorsappbackend.model.dto.User;
import pl.app.collectorsappbackend.model.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<UserEntity, User> {
}
