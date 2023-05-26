package problem;

import java.util.Random;

import static jdk.nashorn.internal.objects.Global.print;

public class ProblemFactory {
    static {

    }
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
    public static Problem getLinearEquation(int min, int max, String var){
        Random r = new Random();
        int x = r.nextInt(max-min)+min;
        int c = r.nextInt(max-min)+3;
        if(r.nextBoolean()){
            c = -c;
        }
        int res = x*c;
        int a = -(r.nextInt(res!=0?Math.abs(2*res):5)-Math.abs(res));
        int b = res+a;
        System.out.println(c+" "+a+" "+b+" "+x);
        return new Problem(c+var+(a==0?"":a<0?a:"+"+a)+"="+b, new int[]{x});
    }
    public static Problem getSystem(int min, int max){
        Random r = new Random();
        int x = r.nextInt(max-min)+min;
        int y = r.nextInt(max-min)+min;
        int[][] mat = new int[2][2];

        int[] b = new int[2];
        int[] c = new int[2];
        do {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    mat[i][j] = r.nextInt(max - min) + min;
                }
            }
            c[0] = mat[0][0] * x + mat[0][1] * y;
            c[1] = mat[1][0] * x + mat[1][1] * y;
            b[0] = c[0] < 0 ? r.nextInt(-c[0] + max - min) : -r.nextInt(c[0] + max - min);
            b[1] = c[1] < 0 ? r.nextInt(-c[1] + max - min) : -r.nextInt(c[1] + max - min);
            c[0] += b[0];
            c[1] += b[1];
        } while (mat[0][1] * mat[1][0] == mat[0][0] * mat[1][1]);
        String latex = "\\begin{array}{l}";
        for (int i = 0; i < 2; i++) {
            if(mat[i][0]==0){
                latex+=mat[i][1]+"y";
                if(b[i]!=0){
                    latex+="+"+b[i];
                }
                latex+="="+c[i];
            } else {
                latex+=mat[i][0]+"x";
                if(mat[i][1]!=0) {
                    if(mat[i][1]>0) latex+="+";
                    latex += mat[i][1] + "y";
                }
                if(b[i]!=0) {
                    if (b[i] > 0) {
                        latex += '+';
                    }
                    latex+=b[i];
                }
                latex+="="+c[i];
            }
            latex+="\\\\";
        }
        latex += "\\end{array}";
        System.out.println(latex);
        System.out.println(x+" "+y);
        return new Problem(latex, new int[]{x, y});
    }
    public static Problem getFractionProblem(int mx){
        Random r = new Random();
        int w = r.nextInt(mx)+2, x =r.nextInt(mx)+2, y=r.nextInt(mx)+2, z=r.nextInt(mx)+2;
        int c=w*x, d=y*z;
        int k0 = r.nextInt(mx)+1, k1 = r.nextInt(mx)+1;
        int a = k0*x*y;
        int b = k1*w*z;
        System.out.println(k0*k1);
        return new Problem("\\frac{"+a+"}{"+c+"}\\times\\frac{"+b+"}{"+d+"}", new int[]{k0*k1});
    }
    public static Problem getGuardProblem(){
        String latex = "(26\\frac{2}{3}\\div6.4)\\times(19.2\\div3\\frac{5}{9})-\\frac{8\\frac{4}{7}\\div2\\frac{26}{77}}{0.5\\div18\\frac{2}{3}\\times11}-\\frac{1}{18}";
        return new Problem(latex, null);
    }
}
