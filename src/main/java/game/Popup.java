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
    List<Content> content;

    public Popup(int x, int y, List<Button> buttons, boolean isVisible, List<Content> content) {
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
            for(Content c : content) {
                if(c.isVisible) {
                    g.drawImage(c.content, this.x+c.x, this.y+c.y, null);
                }
            }
            for(Button b : buttons){
                if(b.isVisible){
                    g.drawImage(b.getImage(), b.x, b.y, null);
                }
            }
        }
    }
    public class Content{
        BufferedImage content;
        boolean isVisible;
        int x,y;
        public Content(BufferedImage content, boolean isVisible, int x, int y) {
            this.content = content;
            this.isVisible = isVisible;
            this.x=x;
            this.y=y;
        }
    }
    public class Button {
        private final Image def;
        private final Image hovering;
        private final Image clicked;
        private Image current;
        int x,y;
        boolean isVisible;
        Rectangle bounds;
        public Button(Image def, Image hovering, Image clicked){
            this.def=def;
            this.hovering = hovering;
            this.clicked = clicked;
            this.current = def;
        }
        public Image getImage(){
            return this.current;
        }

        public void setToDefault(){
            this.current = this.def;
        }

        public void setToHovering(){
            this.current = this.hovering;
        }
        public void setToClicked(){
            this.current = this.clicked;
        }
        public void onReleased(){
            this.current = this.def;
        }
        public void onClicked(){
            this.current = this.def;
        }
    }
}
