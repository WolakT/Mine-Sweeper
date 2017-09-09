package com.xformation;

/**
 * Created by WolakT on 2017-09-05.
 */
public class BasicMineSweeper implements MineSweeper {
    private String[][] mineFieldArr;
    private int N;
    private int M;

    public void setMineField(String mineField) throws IllegalArgumentException {
        if (!validateMineField(mineField)) throw new IllegalArgumentException();
        String[] rowsCount = mineField.split("\\\n");
        M = rowsCount.length;
        N = rowsCount[0].length();
        mineFieldArr = new String[M][N];
        for (int i = 0; i < rowsCount.length; i++) {
            String[] singleSign = rowsCount[i].split("");
            for (int j = 0; j < N; j++) {
                mineFieldArr[i][j] = singleSign[j];
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
        if (mineFieldArr == null) {
            throw new IllegalStateException("MineField not initialized. Call setMineField first!");
        }
        int[][] hintField = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (mineFieldArr[i][j].equals("*")) {
                    hintField[i][j] = -8;
                    adjustAdjacent(hintField, i, j);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hintField.length; i++) {
            for (int j = 0; j < hintField[0].length; j++) {
                if (hintField[i][j] < 0) {
                    sb.append("*");
                } else {
                    sb.append(hintField[i][j]);
                }
                if (j == N - 1 && i < M - 1) {
                    sb.append("\n");
                }
            }
        }

        return sb.toString();
    }

    private void adjustAdjacent(int[][] hintField, int x, int y) {
        int xLowerBound = x - 1;
        int xUpperBound = x + 1;
        int yLowerBound = y - 1;
        int yUpperBound = y + 1;

        if (xLowerBound < 0) {
            xLowerBound = 0;
        }
        if (xUpperBound >= M) {
            xUpperBound = M - 1;
        }
        if (yLowerBound < 0) {
            yLowerBound = 0;
        }
        if (yUpperBound >= N) {
            yUpperBound = N - 1;
        }
        for (int i = xLowerBound; i <= xUpperBound; i++) {
            for (int j = yLowerBound; j <= yUpperBound; j++) {
                hintField[i][j]++;
            }
        }
        hintField[x][y]--;
    }


    public String[][] getMineFieldArr() {
        return mineFieldArr;
    }


}
