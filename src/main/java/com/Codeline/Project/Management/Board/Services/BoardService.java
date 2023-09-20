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
        try {
            return boardRepository.save(board);

        } catch (
                Exception e) {
            return null;
        }

    }

    public List<Board> getAllBoards() {
        try {

            return boardRepository.findAll();
        } catch (Exception e) {
            return null;
        }
    }

    public Board getBoardById(Long boardId) {
        try {

            return boardRepository.findById(boardId).orElse(null);
        } catch (Exception e) {
            return null;
        }
    }


    public Board updateBoard(Long boardId, Board updatedBoard) {
        try {
            Board existingBoard = boardRepository.findById(boardId).orElse(null);
            if (existingBoard == null) {
                return null; // Board not found
            }

            // Update the fields of the existing board with the values from the updated board
            // existingBoard.setId(updatedBoard.getId());
            existingBoard.setTitle(updatedBoard.getTitle()); // TITLE IS THE ONLY REQUIRED TO UPDATE
            //existingBoard.setColumns(updatedBoard.getColumns());

            return boardRepository.save(existingBoard);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean deleteBoard(Long boardId) {
        try {
            Board board = boardRepository.findById(boardId).orElse(null);
            if (board == null) {
                return false; // Board not found
            }
            boardRepository.delete(board);
            return true;

        } catch (Exception e) {
            return false;
        }
    }
}

