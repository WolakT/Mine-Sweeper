package com.xformation;

public class Main {

    public static void main(String[] args) {
	// write your code here
        MyMineSweeper sweeper = new MyMineSweeper();
        sweeper.setMineField("*...\n....");
        System.out.println(sweeper.getHintField());
    }
}
