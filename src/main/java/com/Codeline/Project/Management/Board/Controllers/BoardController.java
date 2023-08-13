package com.Codeline.Project.Management.Board.Controllers;

import com.Codeline.Project.Management.Board.Models.Board;
import com.Codeline.Project.Management.Board.Responses.BoardResponse;
import com.Codeline.Project.Management.Board.Responses.DeleteResponse;
import com.Codeline.Project.Management.Board.Services.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/boards")
public class BoardController {
    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping
    public ResponseEntity<BoardResponse> createBoard(@RequestBody Board board) {
        Board createdBoard = boardService.createBoard(board);

        // Map the createdBoard attributes to a new BoardResponse object
        BoardResponse boardResponse = new BoardResponse(createdBoard.getId(), createdBoard.getTitle(), createdBoard.getColumns() /*, createdBoard.getCards()*/);

        return new ResponseEntity<>(boardResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BoardResponse>> getAllBoards() {
        List<Board> boards = boardService.getAllBoards();
        List<BoardResponse> responses = new ArrayList<>();

        for (Board board : boards) {
            BoardResponse boardResponse = new BoardResponse(board.getId(), board.getTitle(), board.getColumns() /*, board.getCards()*/);
            responses.add(boardResponse);
        }
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<Board> getBoardById(@PathVariable Long boardId) {
        Board board = boardService.getBoardById(boardId);

        if (board == null) {
            // Create a default board and save it
            Board defaultBoard = new Board();
            defaultBoard.setTitle("Default Board Title");
            board = boardService.createBoard(defaultBoard);
            return new ResponseEntity<>(board, HttpStatus.CREATED);
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
    public ResponseEntity<DeleteResponse> deleteBoard(@PathVariable Long boardId) {
        boolean isBoardDeleted = boardService.deleteBoard(boardId);
        if (!isBoardDeleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Board is deleted successfully, create the response object
        DeleteResponse response = new DeleteResponse(true, "Board with ID " + boardId + " has been deleted successfully.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}