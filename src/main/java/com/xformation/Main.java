package com.xformation;

public class Main {

    public static void main(String[] args) {

        BasicMineSweeper sweeper = new BasicMineSweeper();
        sweeper.setMineField("*...\n....");
        System.out.println(sweeper.getHintField());
        BasicMineSweeper sweeper2 = new BasicMineSweeper();
        sweeper2.setMineField("*..\n..*\n.*.");
        System.out.println(sweeper2.getHintField());
    }
}
