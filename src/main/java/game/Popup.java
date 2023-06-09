package game;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Popups are painted on top of all other screen elements, and are capable of
 * user interaction through the Button inner class.
 *
 * @author Tom Philip
 */
public class Popup implements MouseListener, MouseMotionListener {
    int x,y;
    List<Button> buttons;
    boolean isVisible;
    List<Content> content;

    /**
     * Constructs a Popup with the specified content, at the specified coordinates, with
     * the specified visibility.
     * @param x The x-coordinate the Popup should be drawn at.
     * @param y The y-coordinate the Popup should be drawn at
     * @param buttons The list of Buttons in this Popup
     * @param isVisible Whether this Popup is visible.
     * @param content The list of Content in this Popup.
     */
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
    public void mouseDragged(MouseEvent e) {}

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

    /**
     * Paints all the Content and Buttons in this Popup
     * @param g The Graphics context to be painted on.
     */
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

    /**
     * Internal class to represent Content that a Popup can display.
     */
    public class Content{
        Image content;
        boolean isVisible;
        int x,y;
        public Content(BufferedImage content, boolean isVisible, int x, int y) {
            this.content = content;
            this.isVisible = isVisible;
            this.x=x;
            this.y=y;
        }
    }

    /**
     * Internal class to represent Buttons within a Popup. Supports multiple
     * images that are swapped when the user hovers or clicks on the button.
     * Behaviours can be attached to a Button by overriding the onClicked() method.
     */
    public class Button {
        private final Image def;
        private final Image hovering;
        private final Image clicked;
        private Image current;
        int x,y;
        boolean isVisible;
        Rectangle bounds;
        public Button(Image def, Image hovering, Image clicked, int x, int y, boolean isVisible, Rectangle bounds){
            this.def=def;
            this.hovering = hovering;
            this.clicked = clicked;
            this.current = def;
            this.x = x;
            this.y = y;
            this.isVisible = isVisible;
            this.bounds = bounds;
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
