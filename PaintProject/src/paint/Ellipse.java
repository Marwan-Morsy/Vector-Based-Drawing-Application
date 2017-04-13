package paint;

import java.awt.Graphics;
import java.awt.Point;

public class Ellipse extends shape {
    
    public Ellipse (int x,int y,int length,int width){
        super(x, y, length, width,2);
    }
    
    public void draw(Graphics g){
        g.setColor(this.GetColor());
        if (!colored)
            g.drawOval(this.GetX(), this.GetY(), this.GetWidth(), this.GetLength());
        else
            g.fillOval(this.GetX(), this.GetY(), this.GetWidth(), this.GetLength());
    }

    public boolean IsSelectedOrdi(Point p) {
        if (((int)p.getX()) >= this.GetX() && ((int)p.getX()) <= (this.GetX()+this.GetWidth()) &&
                ((int)p.getY()) >= this.GetY() && ((int)p.getY()) <= this.GetY()+this.GetLength())
                     return true;
             else
                 return false;
    }
}
