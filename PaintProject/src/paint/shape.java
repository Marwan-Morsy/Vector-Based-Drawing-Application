package paint;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

public abstract class shape extends JPanel {
    private Color color=Color.black;
    private int x;
    private int y;
    private int lenth;
    private int width;
    private int x2;
    private int y2;
    private int x3;
    private int y3;
    private int type = 0;
    public boolean colored=false; 

    public shape(int x, int y, int lenth, int width, int type) {
        this.x = x;
        this.y = y;
        this.lenth = lenth;
        this.width = width;
        this.type = type;
    }
    
    public shape(int x, int y, int x2, int y2, int type, int dummy) {
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
        this.type = type;
    }
    
    public shape(int x, int y, int x2, int y2, int x3, int y3, int type) {
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
        this.type = type;
    }
    
    public void SetColor(Color newColor) {
        color = newColor;
    }
    
    public int GetX(){
        return x;
    }
    
    public int GetY(){
        return y;
    }
    
    public int GetLength(){
        return lenth;
    }
    
    public int GetWidth(){
        return width;
    }
    
    public int GetX2(){
        return x2;
    }
    
    public int GetY2(){
        return y2;
    }
    
    public int GetX3(){
        return x3;
    }
    
    public int GetY3(){
        return y3;
    }
    
    public int GetType(){
        return type;
    }
    
    public Color GetColor(){
        return color;
    }

    public abstract boolean IsSelectedOrdi(Point p);
    public abstract void draw(Graphics g);
}
