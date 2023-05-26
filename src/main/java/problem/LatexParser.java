package problem;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;
import org.scilab.forge.jlatexmath.TeXParser;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class LatexParser {
    public static BufferedImage parseProblem(Problem p, int f){
        return parseProblem(p.getProblem(), f);
    }
    public static BufferedImage parseProblem(String s, int f) {
        TeXFormula tf = new TeXFormula(s);

        TeXIcon ti = tf.createTeXIcon(TeXConstants.STYLE_TEXT, f);
        BufferedImage bimg = new BufferedImage(ti.getIconWidth(), ti.getIconHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2d = bimg.createGraphics();
        JLabel jl = new JLabel();
        jl.setForeground(new Color(0, 0, 0));
        ti.paintIcon(jl, g2d, 0, 0);

        File out = new File("./out.png");
        try {
            ImageIO.write(bimg, "png", out);
        } catch(Exception e){}
        return bimg;
    }

}
