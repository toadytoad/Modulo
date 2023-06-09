package problem;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Parses a Latex String or a Problem into a BufferedImage. Latex-parsing library provided courtesy of Kurt Vermeulen.
 *
 * @author Tom Philip
 */
public class LatexParser {
    /**
     * Parses a Problem's problem text into a BufferedImage.
     * @param p The Problem to be parsed.
     * @param f The font size of the created BufferedImage.
     * @return A BufferedImage with the Problem rendered onto it.
     */
    public static BufferedImage parseProblem(Problem p, int f){
        return parseProblem(p.getProblem(), f);
    }

    /**
     * Parses a Latex into a BufferedImage.
     * @param s The Latex String to be parsed.
     * @param f The font size of the created BufferedImage.
     * @return A BufferedImage with the Latex String rendered onto it.
     */
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
        } catch(Exception ignored){}
        return bimg;
    }

}
