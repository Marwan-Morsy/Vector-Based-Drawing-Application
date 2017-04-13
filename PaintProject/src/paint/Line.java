package paint;

import java.awt.Graphics;
import java.awt.Point;
import java.text.DecimalFormat;

public class Line extends shape {
	    
	    public Line(int x1, int y1,int x2,int y2 ) {
	        super(x1, y1, x2, y2, 3 , 0);
	    }

	    public void draw(Graphics g) {
	    	g.setColor(this.GetColor());
	        g.drawPolygon(new int[]{this.GetX(),this.GetX2()}, new int[]{this.GetY(),this.GetY2()}, 2);
	    }
	    
	    public boolean IsSelectedOrdi(Point p) {
	        if (this.GetX() == p.getX())
	                return this.GetX2() == p.getX();
	        if (this.GetY() == p.getY())
	                return this.GetY2() == p.getY();
	        double RootRate = Math.round(((this.GetY2()-this.GetY()) * 1.0 / (this.GetX2()-this.GetX())))*10.0/10.0;
	        double Rate = Math.round(((p.getY()-this.GetY()) * 1.0 / (p.getX()-this.GetX())))*10.0/10.0;
	        System.out.println(RootRate);
	        System.out.println(Rate);
	        int smallx = (this.GetX() < this.GetX2())? this.GetX():this.GetX2();
	        int smally = (this.GetY() < this.GetY2())? this.GetY():this.GetY2();
	        int bigx = (this.GetX() > this.GetX2())? this.GetX():this.GetX2();
	        int bigy = (this.GetY() > this.GetY2())? this.GetY():this.GetY2();
	        if (p.getX() > smallx && p.getX() < bigx && p.getY() > smally && p.getY() < bigy && RootRate == Rate)
	            return true;
	        else
	            return false;
	        //return (p.getY() - this.GetY()) * (this.GetX2() - this.GetX()) == (this.GetY2() - this.GetY()) * (p.getX() - this.GetX()); 
	    }
}
