package pl.app.collectorsappbackend.mapper;

import org.mapstruct.Mapper;
import pl.app.collectorsappbackend.model.entity.CardEntity;
import pl.app.collectorsappbackend.model.entity.dto.Card;

@Mapper(componentModel = "spring")
public interface CardMapper extends BaseMapper<CardEntity, Card> {
}
