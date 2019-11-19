package ecs;

import java.util.List;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity
public class EquationsAnswer {
  private List<String> answer;

  public void setAnswer(List<String> answer) {
    this.answer = answer;
  }

  @PlanningVariable(valueRangeProviderRefs = {"answer"})
  public List<String> getAnswer() {
    return answer;
  }

  public String toString() {
    String strAnswer = "";
    for (int i = 0; i < answer.size(); i++) {
      strAnswer.concat(answer.get(i));
    }
    return strAnswer;
  }
}
