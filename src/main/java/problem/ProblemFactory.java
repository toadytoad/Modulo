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
            a = rand.nextInt((int)Math.pow(10, Math.floor((double)dif/2)))+1;
            b = rand.nextInt((int)Math.pow(10, Math.ceil((double)dif/2)))+1;
        } else if(type=='^') {
            b = rand.nextInt(3)+2;
            a = rand.nextInt((int)Math.pow(10, (double)dif/b))+2;
        }
        else {
            a = rand.nextInt((int) Math.pow(10, dif))+1;
            b = rand.nextInt((int) Math.pow(10, dif))+1;
        }
        int ans;
        String op;
        String suf = "";
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
        } else if(type =='^'){
            ans = (int) Math.pow(a, b);
            op = "^{";
            suf = "}";
        }
        else {
            throw new IllegalArgumentException();
        }
        question.append(a);
        question.append(op);
        question.append(b);
        question.append(suf);
        return new Problem(question.toString(), new int[]{ans});
    }

}
