package pl.app.collectorsappbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pl.app.collectorsappbackend.mapper.BaseMapper;
import pl.app.collectorsappbackend.mapper.CardMapper;
import pl.app.collectorsappbackend.model.dto.Card;
import pl.app.collectorsappbackend.model.entity.CardEntity;
import pl.app.collectorsappbackend.repository.CardRepository;

@Service
@RequiredArgsConstructor
public class CardServiceImpl extends BaseService<CardEntity, Card> implements CardService {

    private final CardRepository cardRepository;

    private final CardMapper cardMapper;

    @Override
    protected JpaRepository<CardEntity, Long> getRepository() {
        return cardRepository;
    }

    @Override
    protected BaseMapper<CardEntity, Card> getMapper() {
        return cardMapper;
    }
}
