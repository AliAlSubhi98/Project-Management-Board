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
import java.util.Date;
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
        try {
            Board createdBoard = boardService.createBoard(board);
            Date now = new Date();

            // Map the createdBoard attributes to a new BoardResponse object
            BoardResponse boardResponse = new BoardResponse(createdBoard.getId(), createdBoard.getTitle(), createdBoard.getColumns() , new Date());

            return new ResponseEntity<>(boardResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping
    public ResponseEntity<List<BoardResponse>> getAllBoards() {
        try {
            List<Board> boards = boardService.getAllBoards();
            List<BoardResponse> responses = new ArrayList<>();

            for (Board board : boards) {
                BoardResponse boardResponse = new BoardResponse(board.getId(), board.getTitle(), board.getColumns(), new Date() /*, board.getCards()*/);
                responses.add(boardResponse);
            }
            return new ResponseEntity<>(responses, HttpStatus.OK);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<Board> getBoardById(@PathVariable Long boardId) {
        try {
            Board board = boardService.getBoardById(boardId);

            if (board == null) {
                // Create a default board and save it
                Board defaultBoard = new Board();
                defaultBoard.setTitle("Default Board Title");
                board = boardService.createBoard(defaultBoard);
                return new ResponseEntity<>(board, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(board, HttpStatus.OK);
        } catch (Exception e) {
            return null;
        }
    }

    @PutMapping("/{boardId}")
    public ResponseEntity<Board> updateBoard(@PathVariable Long boardId, @RequestBody Board updatedBoard) {
        try {
            Board board = boardService.updateBoard(boardId, updatedBoard);
            if (board == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(board, HttpStatus.OK);
        } catch (Exception e) {
            return null;
        }
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<DeleteResponse> deleteBoard(@PathVariable Long boardId) {
        try {
            boolean isBoardDeleted = boardService.deleteBoard(boardId);
            if (!isBoardDeleted) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            // Board is deleted successfully, create the response object
            DeleteResponse response = new DeleteResponse(true, "Board with ID " + boardId + " has been deleted successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return null;
        }
    }
}