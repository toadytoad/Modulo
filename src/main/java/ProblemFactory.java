import java.util.Random;

public class ProblemFactory {
    public static Problem getAdditionProblem(int terms, int dif){
        Random rand = new Random();
        StringBuilder question = new StringBuilder();
        int sum = 0;
        for(int i = 0; i<terms; i++){
            int term = rand.nextInt((int)Math.pow(10, dif));
            sum+=term;
            question.append(term);
            if (i<terms-1){
                question.append('+');
            }
        }
        return new Problem(question.toString(), new int[]{sum});
    }
}
