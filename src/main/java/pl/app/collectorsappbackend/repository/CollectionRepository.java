package pl.app.collectorsappbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.app.collectorsappbackend.model.entity.CollectionEntity;
import pl.app.collectorsappbackend.model.entity.key.CollectionKey;

@Repository
public interface CollectionRepository extends JpaRepository<CollectionEntity, CollectionKey> {
}
