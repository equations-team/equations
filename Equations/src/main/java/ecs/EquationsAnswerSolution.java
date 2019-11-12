package ecs;

import java.util.ArrayList;
import java.util.List;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.drools.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.solution.drools.ProblemFactProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardmediumsoft.HardMediumSoftScore;

@PlanningSolution
public class EquationsAnswerSolution {
  private List<String> options;
  private String goal;
  private List<List<String>> answer;

  @ValueRangeProvider(id = "answer")
  public List<List<String>> getAnswer() {
    return answer;
  }

  public void setAnswer(List<List<String>> answer) {
    this.answer = answer;
  }

  private HardMediumSoftScore score;

  public void setGoal(String goal) {
  }

  @ProblemFactProperty
  public String getGoal() {
    return goal;
  }

  public void setOptions(List<String> options) {
    this.options = options;
  }

  @PlanningEntityCollectionProperty
  public List<String> getOptions() {
    return options;
  }

  @ValueRangeProvider(id = "option_slots")
  @ProblemFactCollectionProperty
  public List<String> getOptionSlots() {
    return new ArrayList<>(Symbols.MAX_SOLUTION_SIZE);
  }

  public void setScore(HardMediumSoftScore score) {
    this.score = score;
  }

  @PlanningScore
  public HardMediumSoftScore getScore() {
    return score;
  }
}
