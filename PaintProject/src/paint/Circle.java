package paint;

import java.awt.Graphics;
import java.awt.Point;

public class Circle extends Ellipse {
    
    public Circle(int x, int y, int rad) {
        // TODO Auto-generated constructor stub
        super(x, y, rad, rad);
    }

    public void draw(Graphics g) {
        g.setColor(this.GetColor());
         super.draw(g);
    }

    public boolean IsSelectedOrdi(Point p){
        return (super.IsSelectedOrdi(p));
    }

}
