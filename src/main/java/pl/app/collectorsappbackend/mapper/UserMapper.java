package pl.app.collectorsappbackend.mapper;

import org.mapstruct.Mapper;
import pl.app.collectorsappbackend.model.entity.UserEntity;
import pl.app.collectorsappbackend.model.entity.dto.User;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<UserEntity, User> {
}
