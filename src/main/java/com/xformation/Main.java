package com.xformation;

public class Main {

    public static void main(String[] args) {

        MyMineSweeper sweeper = new MyMineSweeper();
        sweeper.setMineField("*...\n....");
        System.out.println(sweeper.getHintField());
        MyMineSweeper sweeper2 = new MyMineSweeper();
        sweeper2.setMineField("*..\n..*\n.*.");
        System.out.println(sweeper2.getHintField());
    }
}
