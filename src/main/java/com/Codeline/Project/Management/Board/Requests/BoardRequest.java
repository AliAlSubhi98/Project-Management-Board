package com.Codeline.Project.Management.Board.Requests;

import com.Codeline.Project.Management.Board.Models.Board;
import com.Codeline.Project.Management.Board.Models.Card;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardRequest {
    private Long id;
    private String title;

    private Map<Integer, String> columns;
   // private List<Card> cards;


    public Board ConvertToBoard() {
        Board board = new Board();

        board.setId(this.getId());
        board.setTitle(this.getTitle());
        board.setColumns(getColumns());
       // board.setCards(getCards());
        return board;
    }


}
