package pl.app.collectorsappbackend.model.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import pl.app.collectorsappbackend.model.entity.key.CollectionKey;

@Entity
@Table(name = "COLLECTION")
@Data
public class CollectionEntity {

    @EmbeddedId
    private CollectionKey collectionKey;
}
