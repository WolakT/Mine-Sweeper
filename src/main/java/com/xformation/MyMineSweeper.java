package com.xformation;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by WolakT on 2017-09-05.
 */
public class MyMineSweeper implements MineSweeper {
    private String[][] mineFieldMap;
    private int N;
    private int M;

    public void setMineField(String mineField) throws IllegalArgumentException {
        if (!validateMineField(mineField)) throw new IllegalArgumentException();
        String[] rowsCount = mineField.split("\\\n");
        M = rowsCount.length;
        N = rowsCount[0].length();
        mineFieldMap = new String[M][N];
        for (int i = 0; i < rowsCount.length; i++) {
            String[] singleSign = rowsCount[i].split("");
            for (int j = 0; j < N; j++) {
                mineFieldMap[i][j] = singleSign[j];
            }
        }
    }

    private boolean validateMineField(String mineField) {
        String[] rows = mineField.split("\\n");
        int firstRowLength = rows[0].length();
        for (int i = 1; i < rows.length; i++) {
            if (rows[i].length() != firstRowLength) {
                return false;
            }
        }
        if (!mineField.matches("[\\.\\*\\n]*")) {
            return false;
        } else {
            return true;
        }
    }

    public String getHintField() throws IllegalStateException {
        int[][] hintField = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (mineFieldMap[i][j].equals("*")) {
                    hintField[i][j] = -8;
                    adjustAdjacent(hintField, i, j);
                }
            }
        }


      String result =  Arrays.toString(hintField);
        /*
        String result = Stream.of(hintField).flatMap(Stream::of)
                .map(ints -> String.valueOf(ints))
                .collect(Collectors.joining(", "));*/
        return result;
    }

    private void adjustAdjacent(int[][] hintField, int x, int y) {
        int xLowerBound = x - 1;
        int xUpperBound = x + 1;
        int yLowerBound = y - 1;
        int yUpperBound = y + 1;

        if (xLowerBound < 0) {
            xLowerBound = 0;
        }
        if (xUpperBound > M) {
            xUpperBound = M;
        }
        if (yLowerBound < 0) {
            yLowerBound = 0;
        }
        if (yUpperBound > N) {
            yUpperBound = N;
        }
        for (int i = xLowerBound; i <= xUpperBound; i++) {
            for (int j = yLowerBound; j <= yUpperBound; j++) {
                hintField[i][j]++;
            }
        }
        hintField[x][y] --;
    }


    public String[][] getMineFieldMap() {
        return mineFieldMap;
    }


}
