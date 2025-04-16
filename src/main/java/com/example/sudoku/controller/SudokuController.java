package com.example.sudoku.controller;

import com.example.sudoku.model.Board;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SudokuController {

    @FXML
    private GridPane boardGridPane;
    @FXML
    private Button helpButton;
    @FXML
    private Label hintAvailable;
    private Board board;


    private Random random  = new Random();
    private List<TextField> emptyCells = new ArrayList<>();
    private int helpsRemaining = 3; // Contador

    @FXML
    public void initialize() {
        fillBoard();
        setupHelpButton();
        updateHelpButtonText();
    }

    private void fillBoard() {
        board = new Board();
        emptyCells.clear();
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
                    emptyCells.add(textField);
                }

                boardGridPane.setRowIndex(textField, row);
                boardGridPane.setColumnIndex(textField, col);
                boardGridPane.getChildren().add(textField);
                handleNumberTextField(textField,row,col,board);
            }
        }
    }

    private void setupHelpButton() {
        helpButton.setOnAction(event -> provideHelp());
    }

    private void provideHelp() {
        if (emptyCells.isEmpty()) {
            System.out.println("No hay celdas vacías para ayudar");
            return;
        }

        if (helpsRemaining <= 0) {
            helpButton.setDisable(true);
            // Mostrar mensaje al tontito del usuario
            System.out.println("No quedan ayudas disponibles");
            return;
        }

        // Seleccionar una celda vacía aleatoria
        TextField helpCell = emptyCells.get(random.nextInt(emptyCells.size()));
        int row = GridPane.getRowIndex(helpCell);
        int col = GridPane.getColumnIndex(helpCell);

        // Encontrar números válidos para esta celda
        List<Integer> validNumbers = getValidNumbers(row, col);

        if (validNumbers.isEmpty()) {
            System.out.println("No hay números válidos para esta celda");
            return;
        }

        // Seleccionar un número válido aleatorio
        int helpNumber = validNumbers.get(random.nextInt(validNumbers.size()));

        // Actualizar la celda
        helpCell.setText(String.valueOf(helpNumber));
        helpCell.setStyle("-fx-background-color: lightblue; -fx-font-size: 16;");
        board.getBoard().get(row).set(col, helpNumber);
        emptyCells.remove(helpCell); // Eliminar de las celdas vacías

        // Hacer que la celda ayudada no sea editable
        helpCell.setEditable(false);
        helpsRemaining--;
        updateHelpButtonText();
    }

    private void updateHelpButtonText() {
        hintAvailable.setText(String.valueOf(helpsRemaining));
    }

    private List<Integer> getValidNumbers(int row, int col) {
        List<Integer> validNumbers = new ArrayList<>();

        for (int num = 1; num <= 6; num++) {
            if (board.isValid(row, col, num)) {
                validNumbers.add(num);
            }
        }
        return validNumbers;
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
