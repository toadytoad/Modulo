package game;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.List;

public class Popup implements MouseListener, MouseMotionListener {
    int x,y;
    List<Button> buttons;
    boolean isVisible;
    BufferedImage content;

    public Popup(int x, int y, List<Button> buttons, boolean isVisible, BufferedImage content) {
        this.x = x;
        this.y = y;
        this.buttons = buttons;
        this.isVisible = isVisible;
        this.content = content;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(this.isVisible) {
            for (Button b : buttons) {
                if(b.isVisible&&b.bounds.contains(e.getPoint()))
                b.onClicked();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(this.isVisible) {
            for (Button b : buttons) {
                if(b.isVisible&&b.bounds.contains(e.getPoint()))
                b.setToClicked();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(this.isVisible) {
            for (Button b : buttons) {
                if(b.isVisible&&b.bounds.contains(e.getPoint()))
                b.onReleased();
            }
        }
    }



    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(this.isVisible) {
            for (Button b : buttons) {
                if(b.isVisible) {
                    if (b.bounds.contains(e.getPoint())) b.setToHovering();
                    else b.setToDefault();
                }
            }
        }
    }

    public void paint(Graphics g) {
        if(this.isVisible) {
            g.drawImage(this.content, this.x, this.y, null);
            for(Button b : buttons){
                if(b.isVisible){
                    g.drawImage(b.getImage(), b.x, b.y, null);
                }
            }
        }
    }
}
