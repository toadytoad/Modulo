package problem;
import javax.swing.*;
import java.awt.*;

public class LatexParser {
    public static JComponent parseProblem(Problem p){
        String s = p.getProblem();
        JComponent ret = new JPanel();
        StringBuilder num = new StringBuilder();
        int x = 0;
        for(int i= 0; i<s.length(); i++){
            if(s.charAt(i)>='0'&&s.charAt(i)<='9'||s.charAt(i)=='x'||s.charAt(i)=='y'){
                num.append(s.charAt(i));
            } else {
                if(!num.toString().equals("")){
                    JComponent n = new JTextArea(num.toString());
                    n.setFont(new Font("Ariel", Font.PLAIN, 40));
                    n.setOpaque(false);
                    n.setEnabled(false);
                    n.setAlignmentX(x);
                    x+=n.getWidth();
                    ret.add(n);
                    num = new StringBuilder();
                }
                if(s.charAt(i)=='+'){
                    JComponent n = new JTextArea("+");
                    n.setFont(new Font("Ariel", Font.PLAIN, 40));
                    n.setOpaque(false);
                    n.setEnabled(false);
                    n.setAlignmentX(x);
                    x+=n.getWidth();
                    ret.add(n);
                }
                if(s.charAt(i)=='*'){
                    JComponent n = new JTextArea("\u00d7");
                    n.setFont(new Font("Ariel", Font.PLAIN, 40));
                    n.setOpaque(false);
                    n.setEnabled(false);
                    n.setAlignmentX(x);
                    x+=n.getWidth();
                    ret.add(n);
                }
                if(s.charAt(i)=='-'){
                    JComponent n = new JTextArea("-");
                    n.setFont(new Font("Ariel", Font.PLAIN, 40));
                    n.setOpaque(false);
                    n.setEnabled(false);
                    n.setAlignmentX(x);
                    x+=n.getWidth();
                    ret.add(n);
                }
                if(s.charAt(i)=='/'){
                    JComponent n = new JTextArea("\u00f7");
                    n.setFont(new Font("Ariel", Font.PLAIN, 40));
                    n.setOpaque(false);
                    n.setEnabled(false);
                    n.setAlignmentX(x);
                    x+=n.getWidth();
                    ret.add(n);
                }
            }
        }
        if(!num.toString().equals("")){

            JComponent n = new JTextArea(num.toString());
            n.setFont(new Font("Ariel", Font.PLAIN, 40));
            n.setOpaque(false);
            n.setEnabled(false);
            n.setAlignmentX(x);
            x+=n.getWidth();
            ret.add(n);
        }
        return ret;
    }
    private static JComponent parseFraction(String s){
        return null;
    }
}
