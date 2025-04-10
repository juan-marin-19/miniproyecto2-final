package com.example.sudoku.controller;

import com.example.sudoku.model.Board;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class SudokuController {

    @FXML
    private GridPane boardGridPane;

    private Board board;

    @FXML
    public void initialize() {
        board = new Board();
        board.printBoard();
    }

}
