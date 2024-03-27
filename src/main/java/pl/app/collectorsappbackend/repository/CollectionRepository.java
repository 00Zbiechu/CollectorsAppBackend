package pl.app.collectorsappbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.app.collectorsappbackend.model.entity.CollectionEntity;
import pl.app.collectorsappbackend.model.entity.key.CollectionKey;

import java.util.List;
import java.util.Optional;

@Repository
public interface CollectionRepository extends JpaRepository<CollectionEntity, CollectionKey> {

    @Query(value = "SELECT * FROM COLLECTION WHERE CARD_ID=:cardId AND USER_ID=:userId", nativeQuery = true)
    Optional<CollectionEntity> findByCollectionKey(Long cardId, Long userId);

    List<CollectionEntity> findByCollectionKeyUserId(Long userId);
}
