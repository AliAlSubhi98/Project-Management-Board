package com.Codeline.Project.Management.Board.Controllers;
import com.Codeline.Project.Management.Board.Models.Board;
import com.Codeline.Project.Management.Board.Models.Card;
import com.Codeline.Project.Management.Board.Services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards/{boardId}/cards")
public class CardController {
    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    public ResponseEntity<Card> createCard(@RequestBody Card card, @PathVariable Long boardId) {
        card.setBoard(new Board(boardId)); // Set the board for the card
        Card createdCard = cardService.createCard(card);
        return new ResponseEntity<>(createdCard, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Card>> getAllCardsFromBoard(@PathVariable Long boardId) {
        List<Card> cards = cardService.getAllCardsFromBoard(boardId);
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

    @GetMapping("/{cardId}")
    public ResponseEntity<Card> getCardFromBoardById(@PathVariable Long boardId, @PathVariable Long cardId) {
        Card card = cardService.getCardFromBoardById(boardId, cardId);
        if (card == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(card, HttpStatus.OK);
    }

    @PutMapping("/{cardId}")
    public ResponseEntity<Card> updateCardOnBoard(@PathVariable Long boardId, @PathVariable Long cardId,
                                                  @RequestBody Card updatedCard) {
        Card card = cardService.getCardFromBoardById(boardId, cardId);
        if (card == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // If the section is changed, update the card's position in the board
        if (!card.getSection().equals(updatedCard.getSection())) {
            card.setSection(updatedCard.getSection());
            // Perform any logic for repositioning the card within the board based on the section
        }

        card.setTitle(updatedCard.getTitle());
        card.setDescription(updatedCard.getDescription());

        Card updatedCardResult = cardService.updateCard(card);
        if (updatedCardResult == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(updatedCardResult, HttpStatus.OK);
    }


    @DeleteMapping("/{cardId}")
    public ResponseEntity<String> deleteCardFromBoard(@PathVariable Long boardId, @PathVariable Long cardId) {
        boolean isCardDeleted = cardService.deleteCardFromBoard(boardId, cardId);
        if (!isCardDeleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Card with ID " + cardId + " has been deleted successfully from board " + boardId + ".",
                HttpStatus.OK);
    }
}