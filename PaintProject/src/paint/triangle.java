package paint;

import java.awt.Graphics;
import java.awt.Point;

public class triangle extends shape {
    private int[] x = new int[3];
    private int[] y = new int[3];
    
    public triangle(Point x, Point y, Point z){
        super(x.x, x.y,y.x,y.y,z.x,z.y,4);
        this.x[0] = x.x;
        this.x[1] = y.x;
        this.x[2] = z.x;
        
        this.y[0] = x.y;
        this.y[1] = y.y;
        this.y[2] = z.y;
    }
    
    public void draw(Graphics g){
    	g.setColor(this.GetColor());
    	if(!colored)
        g.drawPolygon(x, y, 3);
    	else
          g.fillPolygon(x, y, 3);
    }
    
    public boolean IsSelectedOrdi(Point p) {
        if (((((int)p.getX()) >= x[1] && ((int)p.getX() <= x[2])) || (((int)p.getX()) >= x[0] && ((int)p.getX() <= x[1])) )&&
           ((int)p.getY()) >= y[0] && ((int)p.getY()) <= y[1])
                return true;
        else
            return false;
    }
}
