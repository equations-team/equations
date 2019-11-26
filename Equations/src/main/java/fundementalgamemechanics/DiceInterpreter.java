package fundementalgamemechanics;

import java.util.ArrayList;
import java.util.List;

public class DiceInterpreter {
    public static List<Double> getAllAnswers(List<String> expression) {
        String input = "";
        for (String str : expression) {
            input += str;
        }
        return computePermutations(input.replace(" ", ""));
    }

    private static List<Double> computePermutations(String expression) {
        List<Double> result = new ArrayList<>();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '-' || c == '+' || c == '*' || c == '/') {
                String a = expression.substring(0, i);
                String b = expression.substring(i + 1);
                List<Double> aTmpList = computePermutations(a);
                List<Double> bTmpList = computePermutations(b);
                for (double r : aTmpList) {
                    for (double l : bTmpList) {
                        if (c == '-') {
                            result.add(l - r);
                        } else if (c == '+') {
                            result.add(l + r);
                        } else if (c == '*') {
                            result.add(l * r);
                        } else if (c == '/') {
                            result.add(l / r);
                        }
                    }
                }
            }
        }
        if (result.size() == 0) result.add(Double.valueOf(expression));
        return result;
    }
}
