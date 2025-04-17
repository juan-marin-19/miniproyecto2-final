package com.example.sudoku.model;

import java.util.List;


public interface BoardInterface {

    boolean fillBlocks(int blockIndex);

    boolean isValid(int row, int col, int candidate);


    void printBoard();


    List<List<Integer>> getBoard();

    int[] getHint ();
}
