package com.example.sudoku;

import com.example.sudoku.view.SudokuStage;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
       new SudokuStage();
    }

    public static void main(String[] args) {
        launch();
    }
}