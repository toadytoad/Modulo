package problem;

/**
 * Class to represent a math problem. Stores a Latex String representing
 * the problem, and an array of integers representing all the solutions to
 * the problem.
 *
 * @author Tom Philip
 */
public class Problem {
    private final String question;
    private final int[] solutions;
    public Problem(String question, int[] solutions){
        this.question= question;
        this.solutions=solutions;
    }
    public int[] getSolutions(){
        int[] ret = new int[solutions.length];
        System.arraycopy(solutions, 0, ret, 0, solutions.length);
        return ret;
    }
    public String getProblem(){
        return question;
    }
}
