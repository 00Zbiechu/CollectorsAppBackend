package pl.app.collectorsappbackend.model.entity.key;

import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.app.collectorsappbackend.model.entity.CardEntity;
import pl.app.collectorsappbackend.model.entity.UserEntity;

import java.io.Serializable;

@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CollectionKey implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", referencedColumnName = "id")
    private CardEntity card;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;
}
