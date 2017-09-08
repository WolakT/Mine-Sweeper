package com.xformation;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Tomcio on 2017-09-05.
 */
@RunWith(JUnitParamsRunner.class)
public class MyMineSweeperTest {
    private MyMineSweeper testSweeper;

    @Before
    public void setUp() {
        testSweeper = new MyMineSweeper();
    }


    private Object[][] incorrectMineField() {
        Object[][] params = {
                {"aawefaw"},
                {"...\n...\n...\n...\n..,\n**.\n"},
                {"*********************\n....."},
                {"\n...\n...\n..."},
                {"\n***\n***\n***\n"}


        };
        return params;
    }

    @Test(expected = IllegalArgumentException.class)
    @Parameters(method = "incorrectMineField")
    public void shouldThrowAnIllegalArgumentExceptionWhenTheMineFieldIsIncorrect(Object mineField) {

        testSweeper.setMineField(mineField.toString());
    }

    private Object[][] mineToMap() {
        Object[][] params = {
                {"*..\n..*\n.*."},
                {".......\n*******"},
                {".\n*\n.\n*\n.\n*\n."},
                {"..............."}


        };
        return params;
    }

    @Test
    @Parameters(method = "mineToMap")
    public void shouldAssignMineFieldCorrectlyToMineMap(Object mineField) {
        testSweeper.setMineField(mineField.toString());
        String[][] mineFieldMap = testSweeper.getMineFieldArr();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < mineFieldMap.length; i++) {
            for (int j = 0; j < mineFieldMap[i].length; j++) {
                stringBuilder.append(mineFieldMap[i][j]);
            }
            if (i < mineFieldMap.length - 1) {
                stringBuilder.append("\n");
            }
        }
        String result = stringBuilder.toString();
//    String result = Stream.of(mineFieldMap).flatMap(Stream::of).collect(Collectors.joining("\n"));
        Assert.assertEquals(mineField.toString(), result);
    }

    private Object[][] mineToHint() {
        Object[][] params = {
                {"*...\n..*.\n....", "*211\n12*1\n0111"},
                {"*..\n..*\n.*.", "*21\n23*\n1*2"},
                {".......\n*******", "2333332\n*******"},
                {".\n*\n.\n*\n.\n*\n.", "1\n*\n2\n*\n2\n*\n1"},
                {"...............", "000000000000000"},
                {"****\n****", "****\n****"},
                {"****\n*..*\n*..*\n****", "****\n*55*\n*55*\n****"},
                {"*...*\n.*.*.\n..*..\n.*.*.\n*...*", "*222*\n2*3*2\n23*32\n2*3*2\n*222*"},
                {".\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.",
                        "0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0"},


        };
        return params;
    }

    @Test
    @Parameters(method = "mineToHint")
    public void shouldReturnCorrectHintField(Object mineField, Object expectedHintField) {
        testSweeper.setMineField(mineField.toString());
        String result = testSweeper.getHintField();
        Assert.assertEquals(expectedHintField, result);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowIllegalStateExceptionWhenMineFieldNotInitialized() {
        MyMineSweeper sweeper = new MyMineSweeper();
        sweeper.getHintField();
    }

    @Test
    public void shouldReturnTheSameStringAsInput() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("src\\main\\resources\\mineField.txt"));
        String mineField = String.join("\n", lines);
        testSweeper.setMineField(mineField);
        String resultHintField = testSweeper.getHintField();
        Assert.assertEquals(mineField, resultHintField);

    }

}