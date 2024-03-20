package pl.app.collectorsappbackend.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "CARD")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CardEntity extends BaseEntity {

    @Column(nullable = false)
    @Lob
    private byte[] image;

    @OneToMany(mappedBy = "collectionKey.cardId", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<CollectionEntity> collectionEntities;
}
