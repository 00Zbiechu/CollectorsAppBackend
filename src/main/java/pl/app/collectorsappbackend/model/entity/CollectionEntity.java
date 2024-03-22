package pl.app.collectorsappbackend.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.app.collectorsappbackend.model.entity.key.CollectionKey;

@Entity
@Table(name = "COLLECTION")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CollectionEntity {

    @EmbeddedId
    private CollectionKey collectionKey;

    @Column(nullable = false)
    private Long amount;
}
