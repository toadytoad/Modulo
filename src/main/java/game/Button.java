package game;

import java.awt.*;

public class Button {
    private Image def, hovering, clicked;
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
