package game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class CombinationLock extends Popup {
    final int solution;
    int guess;
    public static boolean isSolved;
    public CombinationLock(int solution) {
        super(800, 350,new ArrayList<>() ,true, new ArrayList<>());

        BufferedImage bf = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
        bf.getGraphics().drawRect(0,0,50,50);


        super.buttons.add(new Button(
                bf, bf, bf,
                this.x, this.y,
                true,
                new Rectangle(this.x, this.y, 50, 50)
        ) {
            @Override
            public void onClicked() {
                if (!(guess/1000 == 9)) guess += 1000;
                else guess -= 9000;
                check();
            }
        });

        super.buttons.add (new Button(
                bf, bf, bf,
                this.x, this.y + 100,
                true,
                new Rectangle(this.x, this.y + 100, 50, 50)
        ) {
            @Override
            public void onClicked() {
                if (!(guess/1000 == 0)) guess -= 1000;
                else guess += 9000;
                check();
            }
        });

        super.buttons.add (new Button(
                bf, bf, bf,
                this.x + 50, this.y,
                true,
                new Rectangle(this.x + 50, this.y, 50, 50)
        ) {
            @Override
            public void onClicked() {
                if (!((guess % 1000) / 100 == 9)) guess += 100;
                else guess -= 900;
                check();
            }
        });

        super.buttons.add (new Button(
                bf, bf, bf,
                this.x + 50, this.y + 100,
                true,
                new Rectangle(this.x + 50, this.y + 100, 50, 50)
        ) {
            @Override
            public void onClicked() {
                if (!((guess % 1000) / 100 == 0)) guess -= 100;
                else guess += 900;
                check();
            }
        });

        super.buttons.add (new Button(
                bf, bf, bf,
                this.x + 100, this.y,
                true,
                new Rectangle(this.x + 100, this.y, 50, 50)
        ) {
            @Override
            public void onClicked() {
                if (!((guess % 100) / 10 == 9)) guess += 10;
                else guess -= 90;
                check();
            }
        });

        super.buttons.add (new Button(
                bf, bf, bf,
                this.x + 100, this.y + 100,
                true,
                new Rectangle(this.x + 100, this.y + 100, 50, 50)
        ) {
            @Override
            public void onClicked() {
                if (!((guess % 100) / 10 == 0)) guess -= 10;
                else guess += 90;
                check();
            }
        });

        super.buttons.add (new Button(
                bf, bf, bf,
                this.x + 150, this.y,
                true,
                new Rectangle(this.x + 150, this.y, 50, 50)
        ) {
            @Override
            public void onClicked() {
                if (!((guess % 10) == 9)) guess += 1;
                else guess -= 9;
                check();
            }
        });

        super.buttons.add (new Button(
                bf, bf, bf,
                this.x + 150, this.y + 100,
                true,
                new Rectangle(this.x + 150, this.y + 100, 50, 50)
        ) {
            @Override
            public void onClicked() {
                if (!((guess % 10) == 0)) guess -= 1;
                else guess += 9;
                check();
            }
        });
        this.solution = solution;
        System.out.println(solution);
    }

    private void check () {isSolved = guess == solution;}

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (super.isVisible) {
            g.setFont(new Font("Default", Font.PLAIN, 50));
            g.drawString(String.format("%04d", guess), 800, 450);
        }
    }
}
