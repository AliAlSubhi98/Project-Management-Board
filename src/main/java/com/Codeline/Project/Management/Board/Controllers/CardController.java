package com.Codeline.Project.Management.Board.Controllers;
import com.Codeline.Project.Management.Board.Models.Board;
import com.Codeline.Project.Management.Board.Models.Card;
import com.Codeline.Project.Management.Board.Requests.CardRequest;
import com.Codeline.Project.Management.Board.Responses.CardResponse;
import com.Codeline.Project.Management.Board.Responses.DeleteResponse;
import com.Codeline.Project.Management.Board.Services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public ResponseEntity<CardResponse> addCardToBoard(@PathVariable Long boardId, @RequestBody CardRequest cardRequest) {
        Card card = cardRequest.convertToCard();
        card.setBoard(new Board(boardId)); // Set the board for the card

        Card createdCard = cardService.createCard(card);

        // Map the createdCard attributes to a new CardResponse object
        CardResponse cardResponse = new CardResponse(
                createdCard.getId(),
                createdCard.getTitle(),
                createdCard.getDescription(),
                createdCard.getSection()
        );

        return new ResponseEntity<>(cardResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CardResponse>> getAllCardsFromBoard(@PathVariable Long boardId) {
        List<Card> cards = cardService.getAllCardsFromBoard(boardId);
        List<CardResponse> cardResponses = new ArrayList<>();

        for (Card card : cards) {
            CardResponse cardResponse = new CardResponse(
                    card.getId(),
                    card.getTitle(),
                    card.getDescription(),
                    card.getSection()
            );
            cardResponses.add(cardResponse);
        }

        return new ResponseEntity<>(cardResponses, HttpStatus.OK);
    }

    @GetMapping("/{cardId}")
    public ResponseEntity<CardResponse> getCardFromBoardById(@PathVariable Long boardId, @PathVariable Long cardId) {
        Card card = cardService.getCardFromBoardById(boardId, cardId);
        if (card == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        CardResponse cardResponse = new CardResponse(
                card.getId(),
                card.getTitle(),
                card.getDescription(),
                card.getSection()
        );

        return new ResponseEntity<>(cardResponse, HttpStatus.OK);
    }

    @PutMapping("/{cardId}")
    public ResponseEntity<CardResponse> updateCardOnBoard(@PathVariable Long boardId, @PathVariable Long cardId,
                                                          @RequestBody CardRequest updatedCardRequest) {
        Card card = updatedCardRequest.convertToCard();
        card.setId(cardId); // Set the ID of the updated card

        // If the section is changed, update the card's position in the board
        Card existingCard = cardService.getCardFromBoardById(boardId, cardId);
        if (existingCard == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

//        if (!existingCard.getSection().equals(card.getSection())) {
//            // Perform any logic for repositioning the card within the board based on the section
//            // (You can handle this logic in the cardService or any other appropriate service)
//        }

        Card updatedCard = cardService.updateCard(card);
        if (updatedCard == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        CardResponse cardResponse = new CardResponse(
                updatedCard.getId(),
                updatedCard.getTitle(),
                updatedCard.getDescription(),
                updatedCard.getSection()
        );

        return new ResponseEntity<>(cardResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{cardId}")
    public ResponseEntity<DeleteResponse> deleteCardFromBoard(@PathVariable Long boardId, @PathVariable Long cardId) {
        boolean isCardDeleted = cardService.deleteCardFromBoard(boardId, cardId);
        if (!isCardDeleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String message = "Card with ID " + cardId + " has been deleted successfully from board " + boardId + ".";
        DeleteResponse deleteResponse = new DeleteResponse(true, message);
        return new ResponseEntity<>(deleteResponse, HttpStatus.OK);
    }

}
