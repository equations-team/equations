package ecs;

import java.util.ArrayList;
import java.util.List;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;

public class EquationsConstraintSolver {
  public static void main(String[] args) {
    List<String> options = new ArrayList<>();
    options.add("4");
    options.add("/");
    options.add("2");
    options.add("+");
    options.add("4");

    SolverFactory<EquationsAnswerSolution> solverFactory =
        SolverFactory.createFromXmlResource("ecs.xml");
    Solver<EquationsAnswerSolution> solver = solverFactory.buildSolver();
    EquationsAnswerSolution eas = new EquationsAnswerSolution();
    eas.setOptions(options);
    eas.setGoal("2");

    EquationsAnswerSolution easSolved = solver.solve(eas);
    System.out.println(easSolved);
  }
}
