package com.xformation;

/**
 * Created by WolakT on 2017-09-05.
 */
public class MyMineSweeper implements MineSweeper {
    private String[][]  mineFieldMap;
    private int N;
    private int M;

    public void setMineField(String mineField) throws IllegalArgumentException {
        if(!validateMineField(mineField)) throw new IllegalArgumentException();
        String[] rowsCount = mineField.split("\\\n");
        M = rowsCount.length;
        N = rowsCount[0].length();
        mineFieldMap = new String[M] [N];
        for(int i = 0; i<rowsCount.length; i++){
            String[] singleSign = rowsCount[i].split("");
            for(int j = 0; j<N; j++) {
                mineFieldMap[i][j] = singleSign[j];
            }
        }
    }

    private boolean validateMineField(String mineField){
        String[] rows = mineField.split("\\n");
        int firstRowLength = rows[0].length();
        for (int i = 1; i < rows.length; i++) {
            if(rows[i].length() != firstRowLength){
                return false;
            }
        }
        if(!mineField.matches("[\\.\\*\\n]*")){
            return false;
        }
        else {
            return true;
        }
    }

    public String[][] getMineFieldMap() {
        return mineFieldMap;
    }

    public String getHintField() throws IllegalStateException {
        return null;
    }
}
