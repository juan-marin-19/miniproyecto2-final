
package com.example.sudoku.model;

import java.util.ArrayList;
import java.util.List;

public abstract class BoardAdapter implements BoardInterface {


        public boolean fillBlocks(int blockIndex){return true;}


        public boolean isValid(int row, int col, int number) {
            return true;
        }

        public List<List<Integer>> getBoard() {
            return new ArrayList<>();
        }

        public int[] getHint() {return new int[]{0,0,0};}

}




