public class Problem {
    String question;
    int[] solutions;
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
