package com.Codeline.Project.Management.Board.Controllers;


import com.Codeline.Project.Management.Board.Models.Board;
import com.Codeline.Project.Management.Board.Services.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/boards")
public class BoardController {
    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping
    public ResponseEntity<Board> createBoard(@RequestBody Board board) {
        Board createdBoard = boardService.createBoard(board);
        return new ResponseEntity<>(createdBoard, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Board>> getAllBoards() {
        List<Board> boards = boardService.getAllBoards();
        return new ResponseEntity<>(boards, HttpStatus.OK);
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<Board> getBoardById(@PathVariable Long boardId) {
        Board board = boardService.getBoardById(boardId);
        if (board == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(board, HttpStatus.OK);
    }

    @PutMapping("/{boardId}")
    public ResponseEntity<Board> updateBoard(@PathVariable Long boardId, @RequestBody Board updatedBoard) {
        Board board = boardService.updateBoard(boardId, updatedBoard);
        if (board == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(board, HttpStatus.OK);
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<String> deleteBoard(@PathVariable Long boardId) {
        boolean isBoardDeleted = boardService.deleteBoard(boardId);
        if (!isBoardDeleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Board with ID " + boardId + " has been deleted successfully.", HttpStatus.OK);
    }
}