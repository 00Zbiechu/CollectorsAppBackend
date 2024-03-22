package pl.app.collectorsappbackend.mapper;

import org.mapstruct.Mapper;
import pl.app.collectorsappbackend.model.dto.Card;
import pl.app.collectorsappbackend.model.entity.CardEntity;

@Mapper(componentModel = "spring")
public interface CardMapper extends BaseMapper<CardEntity, Card> {
}
