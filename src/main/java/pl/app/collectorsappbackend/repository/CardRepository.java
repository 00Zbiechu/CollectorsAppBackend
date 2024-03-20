package pl.app.collectorsappbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.app.collectorsappbackend.model.entity.CardEntity;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, Long> {
}
