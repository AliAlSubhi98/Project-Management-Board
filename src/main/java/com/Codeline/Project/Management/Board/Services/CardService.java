package com.Codeline.Project.Management.Board.Services;

import com.Codeline.Project.Management.Board.Models.Card;
import com.Codeline.Project.Management.Board.Repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {
    private final CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Card createCard(Card card) {
        return cardRepository.save(card);
    }

    public List<Card> getAllCardsFromBoard(Long boardId) {
        return cardRepository.findByBoardId(boardId);
    }

    public Card getCardFromBoardById(Long boardId, Long cardId) {
        return cardRepository.findByBoardIdAndId(boardId, cardId);
    }

    public Card updateCard(Card updatedCard) {
        Card existingCard = cardRepository.findById(updatedCard.getId()).orElse(null);
        if (existingCard == null) {
            return null; // Card not found
        }

        if(updatedCard.getTitle() != null  ){
            existingCard.setTitle(updatedCard.getTitle());
        }
        if(updatedCard.getDescription() != null  ) {
            existingCard.setDescription(updatedCard.getDescription());
        }
        if(updatedCard.getSection() != null ) {
            existingCard.setSection(updatedCard.getSection());
        }
        return cardRepository.save(existingCard);
    }

    public boolean deleteCardFromBoard(Long boardId, Long cardId) {
        Card card = cardRepository.findByBoardIdAndId(boardId, cardId);
        if (card == null) {
            return false; // Card not found
        }
        cardRepository.delete(card);
        return true;
    }
}
