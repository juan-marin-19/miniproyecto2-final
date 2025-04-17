package com.example.sudoku.controller;

import com.example.sudoku.model.Board;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.animation.FadeTransition;
import javafx.util.Duration;
import javafx.scene.control.Label;


/**
 * Controller class for the Sudoku game.
 * This class is responsible for initializing the board,
 * handling user input, validating Sudoku rules in real time,
 * and showing error messages with fade effect.
 */
public class SudokuController {

    @FXML
    private GridPane boardGridPane;

    @FXML
    private Label errorMessage;

    private Board board;

    /**
     * Initializes the controller after the FXML file has been loaded.
     * Calls the method to fill the Sudoku board.
     */
    @FXML
    public void initialize() {
        fillBoard();
    }

    /**
     * Fills the Sudoku board with TextFields. Some cells will show pre-filled numbers,the others will be empty and editable.
     * Also connects each editable cell to real-time validation.
     */
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
     * Connects a TextField to real-time validation logic.
     * Checks if the number typed by the user is between 1 and 6,
     * and updates the board data.
     *
     * @param textField the cell where the user types a number
     * @param row the row index of the cell
     * @param col the column index of the cell
     * @param board the current Sudoku board
     */
    private void handleNumberTextField(TextField textField, int row, int col, Board board) {
        revalidateBoard();
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[1-6]?")) {
                textField.setText(oldValue);
                return;
            }

            if (newValue.isEmpty()) {
                board.getBoard().get(row).set(col, 0);
            } else {
                int value = Integer.parseInt(newValue);
                board.getBoard().get(row).set(col, value);
            }


            revalidateBoard();
        });
    }


    /**
     * Validates all cells on the board after a change, Updating the color of each cell depending on whether
     * it follows the Sudoku rules, Also shows a temporary error message if a rule is broken.
     */
    private void revalidateBoard() {
        for (javafx.scene.Node node : boardGridPane.getChildren()) {
            if (node instanceof TextField) {
                TextField tf = (TextField) node;
                Integer row = GridPane.getRowIndex(tf);
                Integer col = GridPane.getColumnIndex(tf);

                String text = tf.getText();
                if (text == null || text.isEmpty()) {
                    tf.setStyle("");
                    continue;
                }

                int value = Integer.parseInt(text);

                board.getBoard().get(row).set(col, 0);

                if (board.isValid(row, col, value)) {
                    tf.setStyle("-fx-background-color: lightgreen;");
                } else {
                    tf.setStyle("-fx-background-color: lightcoral;");
                    showMessageAndFade(errorMessage, "¡Entrada inválida!");

                }

                board.getBoard().get(row).set(col, value);
            }
        }
    }


    /**
     * Displays the error message if a rule is broken.
     *
     * @param label the Label where the message is shown
     * @param message the text to display
     */
    public void showMessageAndFade(Label label, String message) {
        label.setText(message);
        label.setOpacity(1.0);

        FadeTransition fade = new FadeTransition(Duration.seconds(2), label);
        fade.setFromValue(1.0);
        fade.setToValue(0.0);
        fade.setDelay(Duration.seconds(1));
        fade.play();
    }

}
