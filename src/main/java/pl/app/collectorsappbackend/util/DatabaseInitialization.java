package pl.app.collectorsappbackend.util;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import pl.app.collectorsappbackend.model.entity.CardEntity;
import pl.app.collectorsappbackend.repository.CardRepository;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class DatabaseInitialization {

    private final CardRepository cardRepository;

    private final ResourceLoader resourceLoader;

    @PostConstruct
    public void saveCards() throws IOException {

        cardRepository.save(
                CardEntity
                        .builder()
                        .image(resourceLoader.getResource("classpath:images/AlexEnglish.png").getInputStream().readAllBytes())
                        .build()
        );

        cardRepository.save(
                CardEntity
                        .builder()
                        .image(resourceLoader.getResource("classpath:images/AustinCarr.png").getInputStream().readAllBytes())
                        .build()
        );

        cardRepository.save(
                CardEntity
                        .builder()
                        .image(resourceLoader.getResource("classpath:images/BenWallace.png").getInputStream().readAllBytes())
                        .build()
        );

        cardRepository.save(
                CardEntity
                        .builder()
                        .image(resourceLoader.getResource("classpath:images/BobMcAdoo.png").getInputStream().readAllBytes())
                        .build()
        );

        cardRepository.save(
                CardEntity
                        .builder()
                        .image(resourceLoader.getResource("classpath:images/ChrisBosh.png").getInputStream().readAllBytes())
                        .build()
        );

        cardRepository.save(
                CardEntity
                        .builder()
                        .image(resourceLoader.getResource("classpath:images/ChrisWebber.png").getInputStream().readAllBytes())
                        .build()
        );

        cardRepository.save(
                CardEntity
                        .builder()
                        .image(resourceLoader.getResource("classpath:images/ClydeDrexler.png").getInputStream().readAllBytes())
                        .build()
        );

        cardRepository.save(
                CardEntity
                        .builder()
                        .image(resourceLoader.getResource("classpath:images/DanMajerle.png").getInputStream().readAllBytes())
                        .build()
        );
    }
}
