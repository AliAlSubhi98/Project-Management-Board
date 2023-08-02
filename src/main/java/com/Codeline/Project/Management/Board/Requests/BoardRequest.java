package com.Codeline.Project.Management.Board.Requests;

import com.Codeline.Project.Management.Board.Models.Board;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardRequest {
    private Long id;
    private String title;

    private Map<Integer, String> columns;

    public Board ConvertToBoard(){
        Board board = new Board();

        board.setId(this.getId());
        board.setTitle(this.getTitle());
        board.setColumns(getColumns());
        return board;
    }


}
