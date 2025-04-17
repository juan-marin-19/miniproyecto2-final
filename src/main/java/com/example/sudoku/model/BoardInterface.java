package com.example.sudoku.model;

import java.util.List;


public interface BoardInterface {


    boolean isValid(int row, int col, int candidate);


    void printBoard();


    List<List<Integer>> getBoard();
}
