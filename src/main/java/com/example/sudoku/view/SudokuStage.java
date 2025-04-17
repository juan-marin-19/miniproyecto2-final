package com.example.sudoku.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class is responsible for loading and displaying the main Sudoku window.
 * */
public class SudokuStage extends Stage {

    /**
     * Constructor for the SudokuStage class which loads the layout from the FXML file and sets the window title and size.
     *
     * @throws IOException if the FXML file cannot be loaded.
     */
    public SudokuStage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/sudoku/sudoku-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        setTitle("Sudoku");
        setResizable(false);
        setScene(scene);
        show();
    }
}