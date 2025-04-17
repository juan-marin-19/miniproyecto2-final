package com.example.sudoku.controller;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import com.example.sudoku.model.Board;

/**
 * Interface that defines the essential behaviors of a Sudoku controller.
 * Classes implementing this interface should provide real-time validation,
 * board initialization, and error handling features for a Sudoku game.
 */
public interface SudokuControllerInterface {


    void initialize();

    void fillBoard();

    void handleNumberTextField(TextField textField, int row, int col);


    void revalidateBoard();


    void showMessageAndFade(Label label, String message);
}
