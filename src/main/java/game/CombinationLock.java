package game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CombinationLock extends Popup {
    final int solution;
    int guess;
    public static boolean isSolved;
    public CombinationLock(int solution) {
        super(800, 350,new ArrayList<>() ,true, new ArrayList<>());
        try {
            content.add(new Content(
                    ImageIO.read(new File("src/main/java/assets/uielements/PadlockGUI.png")),
                    true,
                    0,0
            ));
        } catch (IOException e) {
            System.out.println("Could not read file src/main/java/assets/uielements/PadlockGUI.png");
            System.exit(-1);
        }
        super.x = (World.SCREEN_SIZE.x - content.get(0).content.getWidth())/2;
        super.y = (World.SCREEN_SIZE.y - content.get(0).content.getHeight())/2;

        super.buttons.add(new Button(
                null,null,null,
                this.x + 45,this.y + 210,
                true,
                new Rectangle(this.x+45, this.y + 210, 50, 50)
        ) {
            @Override
            public void onClicked() {
                if (!(guess/1000 == 9)) guess += 1000;
                else guess -= 9000;
                check();
            }
        });

        super.buttons.add (new Button(
                null,null,null,
                this.x + 45, this.y + 360,
                true,
                new Rectangle(this.x+45, this.y + 360, 50, 50)
        ) {
            @Override
            public void onClicked() {
                if (!(guess/1000 == 0)) guess -= 1000;
                else guess += 9000;
                check();
            }
        });

        super.buttons.add (new Button(
                null,null,null,
                this.x + 105, this.y+210,
                true,
                new Rectangle(this.x + 105, this.y+210, 50, 50)
        ) {
            @Override
            public void onClicked() {
                if (!((guess % 1000) / 100 == 9)) guess += 100;
                else guess -= 900;
                check();
            }
        });

        super.buttons.add (new Button(
                null,null,null,
                this.x + 105, this.y + 360,
                true,
                new Rectangle(this.x + 105, this.y + 360, 50, 50)
        ) {
            @Override
            public void onClicked() {
                if (!((guess % 1000) / 100 == 0)) guess -= 100;
                else guess += 900;
                check();
            }
        });

        super.buttons.add (new Button(
                null,null,null,
                this.x + 165, this.y+210,
                true,
                new Rectangle(this.x + 165, this.y+210, 50, 50)
        ) {
            @Override
            public void onClicked() {
                if (!((guess % 100) / 10 == 9)) guess += 10;
                else guess -= 90;
                check();
            }
        });

        super.buttons.add (new Button(
                null,null,null,
                this.x + 165, this.y + 360,
                true,
                new Rectangle(this.x + 165, this.y + 360, 50, 50)
        ) {
            @Override
            public void onClicked() {
                if (!((guess % 100) / 10 == 0)) guess -= 10;
                else guess += 90;
                check();
            }
        });

        super.buttons.add (new Button(
                null,null,null,
                this.x + 225, this.y+210,
                true,
                new Rectangle(this.x + 225, this.y+210, 50, 50)
        ) {
            @Override
            public void onClicked() {
                if (!((guess % 10) == 9)) guess += 1;
                else guess -= 9;
                check();
            }
        });

        super.buttons.add (new Button(
                null,null,null,
                this.x + 225, this.y + 360,
                true,
                new Rectangle(this.x + 225, this.y + 360, 50, 50)
        ) {
            @Override
            public void onClicked() {
                if (!((guess % 10) == 0)) guess -= 1;
                else guess += 9;
                check();
            }
        });
        this.solution = solution;
    }

    private void check () {isSolved = guess == solution;}

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (super.isVisible) {
            Font font = new Font("Default", Font.PLAIN, 60);
            g.setColor(Color.white);
            drawCenteredString(g,Integer.toString( guess / 1000), new Rectangle(this.x +45, this.y + 270,50,80), font);
            drawCenteredString(g,Integer.toString( guess % 1000 / 100), new Rectangle(this.x +105, this.y + 270,50,80), font);
            drawCenteredString(g,Integer.toString( guess % 100 / 10), new Rectangle(this.x +165, this.y + 270,50,80), font);
            drawCenteredString(g,Integer.toString( guess % 10), new Rectangle(this.x +225, this.y + 270,50,80), font);
        }
    }

    /**
     * Draws a String centered in the middle of a Rectangle. Courtesy of StackOverflow.
     *
     * @param g The Graphics instance.
     * @param text The String to draw.
     * @param rect The Rectangle to center the text in.
     */
    public void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(font);
        g.drawString(text, x, y);
    }
}
