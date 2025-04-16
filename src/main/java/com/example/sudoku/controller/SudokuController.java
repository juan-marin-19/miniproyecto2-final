package com.example.sudoku.controller;

import com.example.sudoku.model.Board;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
public class SudokuController {

    @FXML
    private GridPane boardGridPane;



    private Board board;

    @FXML
    public void initialize() {
        fillBoard();
    }

    private void fillBoard() {
        board = new Board();
        for (int row = 0; row < board.getBoard().size(); row++) {
            for (int col = 0; col < board.getBoard().size(); col++) {
                int number = board.getBoard().get(row).get(col);
                TextField textField = new TextField();
                textField.setAlignment(Pos.CENTER);
                textField.setBackground(null);

                if(number > 0){
                    textField.setText(String.valueOf(number));
                    textField.setEditable(false);
                } else {
                    textField.setText("");
                }

                boardGridPane.setRowIndex(textField, row);
                boardGridPane.setColumnIndex(textField, col);
                boardGridPane.getChildren().add(textField);
                handleNumberTextField(textField,row,col,board);
                helpButton(board);

            }
        }
    }

    private void helpButton(Board board) {


    }

    /**
     *Method which validates in real time that the typed numbers follow the rules of sudoku.
     *
     *
     **/
    private void handleNumberTextField(TextField textField, int row, int col, Board board) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            // Permitir solo vacío o número del 1 al 6
            if (!newValue.matches("[1-6]?")) {
                textField.setText(oldValue); // Revertir al valor anterior
                return;
            }

            if (newValue.isEmpty()) {
                // Campo vacío: limpiar celda
                board.getBoard().get(row).set(col, 0);
                textField.setStyle(""); // Estilo normal
            } else {
                int value = Integer.parseInt(newValue);


                // Validar con las reglas del Sudoku
                if (board.isValid(row, col, value)) {
                    textField.setStyle("-fx-background-color: lightgreen;");
                    board.getBoard().get(row).set(col, value);
                    textField.setEditable(false);
                } else {
                    textField.setStyle("-fx-background-color: lightcoral;");
                    System.out.println("Error");
                }
            }
        });
    }


}
