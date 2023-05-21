package problem;

import java.util.Random;

public class ProblemFactory {
    @Deprecated
    public static Problem getAdditionProblem(int dif){
        Random rand = new Random();
        StringBuilder question = new StringBuilder();
        int terms = 2;
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
    public static Problem getSimpleProblem(int dif, char type){
        Random rand = new Random();
        StringBuilder question = new StringBuilder();
        int a;
        int b;
        if(type=='/'){
            a = rand.nextInt((int)Math.pow(10, Math.floor((double)dif/2)));
            b = rand.nextInt((int)Math.pow(10, Math.ceil((double)dif/2)));
        } else {
            a = rand.nextInt((int) Math.pow(10, dif));
            b = rand.nextInt((int) Math.pow(10, dif));
        }
        int ans;
        String op;
        if(type == '+'){
            ans = a+b;
            op = "+";
        }
        else if(type=='*'){
            ans = a*b;
            op="\\times";
        }
        else if(type=='-'){
            ans = a-b;
            op="-";

        } else if(type=='/'){
            ans = a;
            a = ans*b;
            op="\\div";
        }
        else {
            throw new IllegalArgumentException();
        }
        question.append(a);
        question.append(op);
        question.append(b);
        return new Problem(question.toString(), new int[]{ans});
    }
}
