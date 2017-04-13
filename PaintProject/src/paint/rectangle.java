package paint;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class rectangle extends shape {
	/*private int x;
	private int y;
    private int length;
	private int width;*/
	
	public rectangle(int x,int y,int length,int width){
		super(x, y, length, width,1);
		/*this.x = x;
		this.y = y;
		this.length =length;
		this.width = width;*/
    }
	
    public void draw(Graphics g){
        g.setColor(this.GetColor());
        if(!colored)
        g.drawRect(this.GetX(), this.GetY(), this.GetLength(), this.GetWidth());
        else
        g.fillRect(this.GetX(), this.GetY(), this.GetLength(), this.GetWidth());
    }
    
    public boolean IsSelectedOrdi(Point p) {
        if (((int)p.getX()) >= this.GetX() && ((int)p.getX()) <= (this.GetX()+this.GetLength()) &&
           ((int)p.getY()) >= this.GetY() && ((int)p.getY()) <= this.GetY()+this.GetWidth())
                return true;
        else
            return false;
    }
    
    /*public int GetX() {
        return x;
    }
    public int GetY() {
        return y;
    }
    public int GetLen() {
        return length;
    }
    public int GetWidd() {
        return width;
    }*/
}