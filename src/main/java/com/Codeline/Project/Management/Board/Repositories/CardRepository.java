package com.Codeline.Project.Management.Board.Repositories;

import com.Codeline.Project.Management.Board.Models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByBoardId(Long boardId);

    Card findByBoardIdAndId(Long boardId, Long cardId);
}