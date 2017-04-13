package paint;

import java.awt.Graphics;
import java.awt.Point;
public class Square extends rectangle  {
    public Square(int x, int y, int len) {
        // TODO Auto-generated constructor stub
        super(x, y, len, len);
    }

    public void draw(Graphics g) {
    	g.setColor(this.GetColor());
    	
         super.draw(g);
    
    }

    public boolean IsSelected(Point p){
        return (super.IsSelectedOrdi(p));
    }
}