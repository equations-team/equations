package ecs;

import java.util.List;
import java.util.Optional;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import org.optaplanner.core.api.score.Score;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.impl.score.director.easy.EasyScoreCalculator;

import static ecs.Symbols.CLOSE_PAREN;
import static ecs.Symbols.NUMERALS;
import static ecs.Symbols.OPEN_PAREN;
import static ecs.Symbols.SQRT;
import static ecs.Symbols.SYMBOLS;

public class Scorer implements EasyScoreCalculator<EquationsAnswerSolution> {
  public Score calculateScore(EquationsAnswerSolution solution) {
    int hardScore = 0;
    int softScore = 0;

    Optional<Double> goal = evaluateExpressionString(solution.getGoal());
    List<String> options = solution.getOptions();

    if (options.isEmpty()) {
      hardScore--;
    } else {
      for (int i = 0; i < options.size(); i++) {
        if (i + 1 <= options.size()) {
          if (isSquareRoot(options.get(i)) && isSymbol(options.get(i + 1))) {
            hardScore--;
          }
          if (isSquareRoot(options.get(i)) && isCloseParenthesis(
              options.get(i + 1))) {
            hardScore--;
          }

          if (isSymbol(options.get(i)) && isSymbol(options.get(i + 1))) {
            hardScore--;
          }

          if (isOpenParenthesis(options.get(i))) {
            if (isCloseParenthesis(options.get(i + 1))) {
              hardScore--;
            } else if (isSymbol(options.get(i + 1))) {
              hardScore--;
            } else {

            }
          }

          if (isCloseParenthesis(options.get(i)) && isOpenParenthesis(
              options.get(i + 1))) {
            hardScore--;
          }

          if (isNumeral(options.get(i)) && !isSymbol(options.get(i + 1))) {
            hardScore--;
          }
        }
      }
      String expressionString = "";
      for (String str : options) {
        expressionString += str;
      }
      if (!goal.equals(expressionString)) {
        softScore--;
      }
    }
    return HardSoftScore.of(hardScore, softScore);
  }

  // Is a symbol not including sqrt
  public boolean isSymbol(String str) {
    if (SYMBOLS.contains(str)) {
      return true;
    }
    return false;
  }

  public boolean isNumeral(String str) {
    if (NUMERALS.contains(str)) {
      return true;
    }
    return false;
  }

  public boolean isParenthesis(String str) {
    return (isOpenParenthesis(str) || isCloseParenthesis(str));
  }

  public boolean isOpenParenthesis(String str) {
    if (OPEN_PAREN.equals(str)) {
      return true;
    }
    return false;
  }

  public boolean isCloseParenthesis(String str) {
    if (CLOSE_PAREN.equals(str)) {
      return true;
    }
    return false;
  }

  public boolean isSquareRoot(String str) {
    if (SQRT.equals(str)) {
      return true;
    }
    return false;
  }

  public Optional<Double> evaluateExpressionString(String answer) {
    ScriptEngineManager mgr = new ScriptEngineManager();
    ScriptEngine engine = mgr.getEngineByName("JavaScript");
    try {
      return Optional.of(Double.valueOf(engine.eval(answer).toString()));
    } catch (ScriptException e) {
      return Optional.empty();
    }
  }
}
