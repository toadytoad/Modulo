import problem.LatexParser;
import problem.ProblemFactory;

import javax.swing.*;

public class Test {
    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.getContentPane().add(LatexParser.parseProblem(ProblemFactory.getSimpleProblem(4, '/')));
        f.setVisible(true);
    }
}
