package pl.app.collectorsappbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.app.collectorsappbackend.model.dto.AddCardsToCollection;
import pl.app.collectorsappbackend.model.dto.CardsCollection;
import pl.app.collectorsappbackend.service.CollectionService;

@RestController
@RequestMapping("/api/collection")
@CrossOrigin("http://localhost:4200")
@RequiredArgsConstructor
public class CollectionController {

    private final CollectionService collectionService;

    @GetMapping
    public ResponseEntity<CardsCollection> getCollection(@RequestParam Long userId) {
        return new ResponseEntity<>(collectionService.getUserCollection(userId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CardsCollection> saveInCollection(@RequestBody AddCardsToCollection addCardsToCollection) {
        return new ResponseEntity<>(collectionService.saveInCollection(addCardsToCollection), HttpStatus.CREATED);
    }

    @PostMapping("/trade")
    public ResponseEntity<CardsCollection> trade(@RequestParam Long userId, @RequestParam Long oldCardId, @RequestParam Long newCardId) {
        return new ResponseEntity<>(collectionService.trade(userId, oldCardId, newCardId), HttpStatus.OK);
    }
}
