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

/**
 * Created by WolakT on 2017-09-05.
 */
@RunWith(JUnitParamsRunner.class)
public class BasicMineSweeperTest {
    private static final String NEW_LINE = "\n";
    private static final String PATH_LONG_MINE_FIELD = "src\\test\\resources\\longMineField.txt";
    private static final String PATH_LONG_MINE_FIELD_RESULT = "src\\test\\resources\\longMineField_result.txt";
    private static final String PATH_MINE_FIELD = "src\\test\\resources\\mineField.txt";
    private BasicMineSweeper testSweeper;

    @Before
    public void setUp() {
        testSweeper = new BasicMineSweeper();
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
    public void setMineFieldshouldThrowOnIncorrectMineField(Object mineField) {

        testSweeper.setMineField(mineField.toString());
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
    public void getHintFieldShouldThrowWhenMineFieldNotInitialized() {
        BasicMineSweeper sweeper = new BasicMineSweeper();
        sweeper.getHintField();
    }

    @Test
    public void shouldReturnTheSameStringAsInput() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(PATH_MINE_FIELD));
        String mineField = String.join("\n", lines);
        testSweeper.setMineField(mineField);
        String resultHintField = testSweeper.getHintField();
        Assert.assertEquals(mineField, resultHintField);

    }

    @Test
    public void shouldReturnCorrectHintFieldFromInput() throws IOException{
        List<String> lines = Files.readAllLines(Paths.get(PATH_LONG_MINE_FIELD));
        String mineField = String.join(NEW_LINE, lines);

        List<String> resultLines =  Files.readAllLines(Paths.get(PATH_LONG_MINE_FIELD_RESULT));
        String expectedResult = String.join(NEW_LINE, resultLines);

        testSweeper.setMineField(mineField);
        String resultHintField = testSweeper.getHintField();

        Assert.assertEquals(expectedResult, resultHintField);

    }

}