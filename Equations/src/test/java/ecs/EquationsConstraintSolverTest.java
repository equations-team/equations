package ecs;

import org.junit.Test;

import static ecs.Symbols.NUMERALS;
import static ecs.Symbols.SYMBOLS;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EquationsConstraintSolverTest {
  private Scorer scorer = new Scorer();

  @Test
  public void testIsSymbol() {
    for (int i = 0; i < SYMBOLS.size(); i++) {
      assertTrue(scorer.isSymbol(SYMBOLS.get(i)));
    }
  }

  @Test
  public void testIsSqrt() {
    assertTrue(scorer.isSquareRoot("sqrt"));
  }

  @Test
  public void testIsNumeral() {
    for (int i = 0; i < NUMERALS.size(); i++) {
      assertTrue(scorer.isNumeral(NUMERALS.get(i)));
    }
  }

  @Test
  public void testGetGoalValue() {
    String goal = "5/2";
    Double expectedValue = 5.0 / 2.0;
    Double goalValue = scorer.evaluateExpressionString(goal).get();
    assertTrue(goalValue.equals(expectedValue));
  }

  @Test
  public void testGetEquationsAnswerValue() {
    String equationsAnswer = "(1+3)/2+4";
    Double expectedValue = 6.0;
    Double equationsAnswerValue =
        scorer.evaluateExpressionString(equationsAnswer).get();
    assertTrue(equationsAnswerValue.equals(expectedValue));
  }

  @Test
  public void testInvalidExpression() {
    String invalid = "1++1";
    assertFalse(scorer.evaluateExpressionString(invalid).isPresent());
  }
}
