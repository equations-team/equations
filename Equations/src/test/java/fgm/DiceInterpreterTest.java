package fgm;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static fundementalgamemechanics.DiceInterpreter.getAllAnswers;
import static org.junit.Assert.assertTrue;

public class DiceInterpreterTest {
    @Test
    public void test2NValidAnswer() {
        List<String> input = new ArrayList<>();
        input.add("1");
        input.add("+");
        input.add("2");
        input.add("/");
        input.add("3");
        List<Double> expected = new ArrayList<>();
        expected.add(1.0 + 2.0 / 3.0);
        expected.add(1.0);
        List<Double> results = getAllAnswers(input);
        assertTrue(expected.equals(results));
    }

    @Test
    public void test3NValidAnswer() {
        List<String> input = new ArrayList<>();
        input.add("1 ");
        input.add("+");
        input.add("2");
        input.add("/");
        input.add("3");
        input.add("*");
        input.add("4");
        List<Double> expected = new ArrayList<>();
        expected.add(1.0 + 2.0 / (3.0 * 4.0));
        expected.add(1.0 + (2.0 / 3.0) * 4.0);
        expected.add((1.0 + 2.0) / (3.0 * 4.0));
        expected.add((1.0 + (2.0 / 3.0)) * 4);
        expected.add(((1.0 + 2.0) / 3.0) * 4.0);

        List<Double> results = getAllAnswers(input);
        assertTrue(expected.equals(results));
    }

    @Test
    public void testParentheticalAnswer() {
        List<String> input = new ArrayList<>();
        input.add("1");
        input.add("+");
        input.add("2");
        input.add(" ");
        input.add("/");
        input.add("3");
        List<Double> expected = new ArrayList<>();
        expected.add(1.0);
        List<Double> results = getAllAnswers(input);
        assertTrue(expected.equals(results));
    }
}
