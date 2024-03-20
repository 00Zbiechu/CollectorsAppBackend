package pl.app.collectorsappbackend.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Entity
@Table(name = "APP_USER")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserEntity extends BaseEntity {

    @Column(nullable = false)
    private String username;

    @OneToMany(mappedBy = "collectionKey.userId", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<CollectionEntity> collectionEntities;
}
