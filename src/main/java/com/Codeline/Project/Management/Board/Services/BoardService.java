package com.Codeline.Project.Management.Board.Services;

import com.Codeline.Project.Management.Board.Models.Board;
import com.Codeline.Project.Management.Board.Repositories.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Board createBoard(Board board) {
        return boardRepository.save(board);
    }

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    public Board getBoardById(Long boardId) {
        return boardRepository.findById(boardId).orElse(null);
    }

    public Board updateBoard(Long boardId, Board updatedBoard) {
        Board existingBoard = boardRepository.findById(boardId).orElse(null);
        if (existingBoard == null) {
            return null; // Board not found
        }

        // Update the fields of the existing board with the values from the updated board
        // existingBoard.setId(updatedBoard.getId());
        existingBoard.setTitle(updatedBoard.getTitle()); // TITLE IS THE ONLY REQUIRED TO UPDATE
        //existingBoard.setColumns(updatedBoard.getColumns());

        return boardRepository.save(existingBoard);
    }

    public boolean deleteBoard(Long boardId) {
        Board board = boardRepository.findById(boardId).orElse(null);
        if (board == null) {
            return false; // Board not found
        }
        boardRepository.delete(board);
        return true;
    }

}

