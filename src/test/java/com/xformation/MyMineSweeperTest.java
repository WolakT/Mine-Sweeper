package com.xformation;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * Created by Tomcio on 2017-09-05.
 */
@RunWith(JUnitParamsRunner.class)
public class MyMineSweeperTest {
    private MyMineSweeper testSweeper;

    @Before
    public void setUp(){
        testSweeper = new MyMineSweeper();
    }


    private Object[][] setUpParameters(){
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
@Parameters(method = "setUpParameters")
    public void shouldThrowAnIllegalArgumentException(Object mineField){

    testSweeper.setMineField(mineField.toString());
}
    private Object[][] mineToMap(){
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
    public void shouldAssignMineFieldCorrectlyToMineMap(Object mineField){
         testSweeper.setMineField(mineField.toString());
    String[][] mineFieldMap = testSweeper.getMineFieldMap();
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < mineFieldMap.length; i++) {
        for (int j = 0; j < mineFieldMap[i].length; j++) {
            stringBuilder.append(mineFieldMap[i][j]);
        }
        if(i<mineFieldMap.length-1){
            stringBuilder.append("\n");
        }
    }
    String result = stringBuilder.toString();
//    String result = Stream.of(mineFieldMap).flatMap(Stream::of).collect(Collectors.joining("\n"));
    Assert.assertEquals(mineField.toString(),result);
}

}