package pl.app.collectorsappbackend.model.entity.key;

import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import pl.app.collectorsappbackend.model.entity.CardEntity;
import pl.app.collectorsappbackend.model.entity.UserEntity;

import java.io.Serializable;

@Embeddable
@Data
public class CollectionKey implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", referencedColumnName = "id")
    private CardEntity cardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity userId;
}
