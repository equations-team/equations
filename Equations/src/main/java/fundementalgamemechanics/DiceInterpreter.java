package fundementalgamemechanics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DiceInterpreter {
    private final static Logger LOGGER = LoggerFactory.getLogger(DiceInterpreter.class);

    public static List<Double> getAllAnswers(List<String> expression) {
        String input = "";
        for (String str : expression) {
            input += str;
        }
        if (!input.contains(" ")) {
            return computePermutations(input);
        } else {
            List<Double> result = new ArrayList<>();
            result.add(evaluateParentheticalExpression(input).get());
            return result;
        }
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

    private static Optional<Double> evaluateParentheticalExpression(String expression) {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        String[] exp = expression.split(" ");
        String str = "";
        for (int i = 0; i < exp.length; i++) {
            str = String.format("(%s)", str+exp[i]);
        }
        try {
            return Optional.of((Double.valueOf(engine.eval(str).toString())));
        } catch (ScriptException e) {
            LOGGER.error(e.getMessage());
        }
        return Optional.empty();
    }
}
