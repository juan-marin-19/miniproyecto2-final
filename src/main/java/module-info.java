module com.example.sudoku {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.sudoku to javafx.fxml;
    exports com.example.sudoku;
    exports com.example.sudoku.controller;
    opens com.example.sudoku.controller to javafx.fxml;
}