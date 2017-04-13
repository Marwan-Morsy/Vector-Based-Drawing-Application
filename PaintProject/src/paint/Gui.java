package paint;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.*;
import java.util.*;
import java.util.List;
import java.util.jar.*;
import javax.swing.*;
import org.json.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class Gui {
    private List<rectangle> selectRec = new ArrayList<>();
    private List<shape> shapes = new ArrayList<>();
	private RectsPanel panel = new RectsPanel();
	private ImageIcon rectangle;
	private ImageIcon ellipse;
	private ImageIcon line;
	private ImageIcon circle;
	private ImageIcon triangle;
	private ImageIcon square;
	private ImageIcon c;
	private ImageIcon save;
	private ImageIcon load;
	private ImageIcon delete;
	private ImageIcon undo;
	private ImageIcon redo;
	private ImageIcon select;
	private ImageIcon plugin;

	private JToolBar toolbar1;
	private JToolBar toolbar2;

	private JButton rectangleb;
	private JButton colorb;
    private JButton ellipseb;
    private JButton lineb;
    private JButton circleb;
    private JButton triangleb;
    private JButton squareb;
    private JButton saveb;
    private JButton loadb;
    private JButton undob;
    private JButton redob;
    private JButton selectb;
    private JButton deleteb;
    private JButton Extrab;
    
    private int X1Line;
    private int X2Line;
    private int Y1Line;
    private int Y2Line;
    MouseListener ml;
    MouseMotionAdapter motion;
    int coorX;
    int coorY;
    int virtualX1;
    int virtualY1;
    int virtualX2;
    int virtualY2;
    Point xPoint;
    Point yPoint;
    Point zPoint;
    int NowSelected;
    int IndexSelected;
    String ShapeSelected = "";
    int FromX;
    int FromY;
    int ToX;
    int ToY;
    int FromXselected;
    int FromYselected;
    int ToXselected;
    int ToYselected;
    boolean Select = false;
    Point pSelect = null;
    SaveShapes SaveFile;
    int indexS = 0;
    int lensel = 0;
    Constructor<?> ConstractorCircle;
    Constructor<?> ConstractorLine;
    Constructor<?> ConstractorSquare;
    Constructor<?> ConstractorTriangle;
    Stack<actionDone> undoHistory = new Stack<>();
    Stack<actionDone> redoHistory = new Stack<>();
    
	public Gui() {
		JFrame whole = new JFrame("Paint");
		InitializeImages();
		InitializeButtons();
		PutToolbarOne();
		PutToolbarTwo();
		lineb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (ConstractorLine != null) {
                panel.removeMouseListener(ml);
                panel.removeMouseMotionListener(motion);
                ml = new MouseAdapter() {
                    public void mousePressed(MouseEvent me) {
                        X1Line = me.getX();
                        Y1Line = me.getY();
                        panel.repaint();
                    }
                    
                    public void mouseReleased(MouseEvent me) {
                        X2Line = me.getX();
                        Y2Line = me.getY();
                        try {
                            shapes.add((shape) ConstractorLine.newInstance(X1Line,Y1Line,X2Line,Y2Line));
                            undoHistory.push(new actionDone((shape) ConstractorLine.newInstance(X1Line,Y1Line,X2Line,Y2Line), null, indexS++));
                        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                                | InvocationTargetException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        //lines.add(new Line(X1Line,Y1Line,X2Line,Y2Line));
                        virtualX1 = 0;
                        virtualX2 = 0;
                        virtualY1 = 0;
                        virtualY2 = 0;
                        ShapeSelected = "";
                        panel.repaint();
                    }
                };
                
                motion = new MouseMotionAdapter() {
                    @Override
                    public void mouseDragged(MouseEvent e) {
                        // TODO Auto-generated method stub
                        super.mouseDragged(e);
                        virtualX1 = X1Line;
                        virtualY1 = Y1Line;
                        virtualX2 = e.getX();
                        virtualY2 = e.getY();
                        ShapeSelected = "line";
                        panel.repaint();
                    }
                };
                panel.removeMouseListener(ml);
                panel.removeMouseMotionListener(motion);
                panel.addMouseListener(ml);
                panel.addMouseMotionListener(motion);
                panel.repaint();
            } else {
                Object[] te = {"Cancel"};
                int choice = JOptionPane.showOptionDialog(panel, "The Line hasn't Loaded Yet", "Error", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null,te, "Metric");
            }
            }
        });
		rectangleb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.removeMouseListener(ml);
                panel.removeMouseMotionListener(motion);
                ml = new MouseAdapter() {
                    int len;
                    int width;
                    public void mousePressed(MouseEvent me) {
                        //super.mouseClicked(me);
                        coorX = me.getX();
                        coorY = me.getY();
                        panel.repaint();
                    }
                    
                    public void mouseReleased(MouseEvent me) {
                        int finishx = me.getX();
                        int finishy = me.getY();
                        len = Math.abs(finishx-coorX);
                        width = Math.abs(finishy-coorY);
                        shapes.add(new rectangle((coorX<finishx)?coorX:finishx, (coorY<finishy)?coorY:finishy, len, width));
                        undoHistory.push(new actionDone(new rectangle((coorX<finishx)?coorX:finishx, (coorY<finishy)?coorY:finishy, len, width), null, indexS++));
                        //rectangles.add(new rectangle((coorX<finishx)?coorX:finishx, (coorY<finishy)?coorY:finishy, len, width));
                        virtualX1 = 0;
                        virtualX2 = 0;
                        virtualY1 = 0;
                        virtualY2 = 0;
                        ShapeSelected = "";
                        panel.repaint();
                    }
                };
                
                motion = new MouseMotionAdapter() {
                    @Override
                    public void mouseDragged(MouseEvent e) {
                        // TODO Auto-generated method stub
                        super.mouseDragged(e);
                        virtualX1 = (coorX > e.getX())?e.getX():coorX;
                        virtualY1 = (coorY > e.getY())?e.getY():coorY;
                        virtualX2 = (coorX < e.getX())?e.getX():coorX;
                        virtualY2 = (coorY < e.getY())?e.getY():coorY;
                        ShapeSelected = "rectangle";
                        panel.repaint();
                    }
                };
                panel.removeMouseListener(ml);
                panel.removeMouseMotionListener(motion);
                panel.addMouseListener(ml);
                panel.addMouseMotionListener(motion);
                panel.repaint();
            }
        });
		ellipseb.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                panel.removeMouseListener(ml);
	                panel.removeMouseMotionListener(motion);
	                ml = new MouseAdapter() {
	                    int len;
	                    int width;
	                    public void mousePressed(MouseEvent me) {
	                        //super.mouseClicked(me);
	                        coorX = me.getX();
	                        coorY = me.getY();
	                        panel.repaint();
	                    }
	                    
	                    public void mouseReleased(MouseEvent me) {
	                        int finishx = me.getX();
	                        int finishy = me.getY();
	                        len = Math.abs(finishx-coorX);
	                        width = Math.abs(finishy-coorY);
                                shapes.add(new Ellipse((coorX<finishx)?coorX:finishx, (coorY<finishy)?coorY:finishy, width, len));
                                undoHistory.push(new actionDone(new Ellipse((coorX<finishx)?coorX:finishx, (coorY<finishy)?coorY:finishy, width, len), null, indexS++));
	                        //ellipses.add(new Ellipse((coorX<finishx)?coorX:finishx, (coorY<finishy)?coorY:finishy, len, width));
	                        virtualX1 = 0;
	                        virtualX2 = 0;
	                        virtualY1 = 0;
	                        virtualY2 = 0;
	                        ShapeSelected = "";
	                        panel.repaint();
	                    }
	                };
	                
	                motion = new MouseMotionAdapter() {
	                    @Override
	                    public void mouseDragged(MouseEvent e) {
	                        // TODO Auto-generated method stub
	                        super.mouseDragged(e);
	                        virtualX1 = (coorX > e.getX())?e.getX():coorX;
	                        virtualY1 = (coorY > e.getY())?e.getY():coorY;
	                        virtualX2 = (coorX < e.getX())?e.getX():coorX;
	                        virtualY2 = (coorY < e.getY())?e.getY():coorY;
	                        ShapeSelected = "ellipse";
	                        panel.repaint();
	                    }
	                };
	                panel.removeMouseListener(ml);
	                panel.removeMouseMotionListener(motion);
	                panel.addMouseListener(ml);
	                panel.addMouseMotionListener(motion);
	                panel.repaint();
	            }
	        });

		circleb.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                if (ConstractorCircle != null) {
	                panel.removeMouseListener(ml);
	                panel.removeMouseMotionListener(motion);
	                ml = new MouseAdapter() {
	                    int width;
	                    public void mousePressed(MouseEvent me) {
	                        //super.mouseClicked(me);
	                        coorX = me.getX();
	                        coorY = me.getY();
	                        panel.repaint();
	                    }
	                    
	                    public void mouseReleased(MouseEvent me) {
	                        int finishx = me.getX();
	                        int finishy = me.getY();
	                        lensel = Math.abs(finishx-coorX);
	                        width = Math.abs(finishy-coorY);
	                        try {
                                shapes.add((shape) ConstractorCircle.newInstance(coorX-lensel, coorY-lensel, lensel*2));
                                undoHistory.push(new actionDone((shape) ConstractorCircle.newInstance(coorX-lensel, coorY-lensel, lensel*2), null, indexS++));
                            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                                    | InvocationTargetException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
	                        //circles.add(new Circle((coorX<finishx)?coorX:finishx, (coorY<finishy)?coorY:finishy, len));
	                        virtualX1 = 0;
	                        virtualX2 = 0;
	                        virtualY1 = 0;
	                        virtualY2 = 0;
	                        ShapeSelected = "";
	                        panel.repaint();
	                    }
	                };
	                
	                motion = new MouseMotionAdapter() {
	                    @Override
	                    public void mouseDragged(MouseEvent e) {
	                        // TODO Auto-generated method stub
	                        super.mouseDragged(e);
	                        virtualX1 = coorX;
	                        virtualY1 = coorY;
	                        virtualX2 = Math.abs(e.getX()-coorX);
	                        virtualY2 = (coorY < e.getY())?e.getY():coorY;
	                        ShapeSelected = "circle";
	                        panel.repaint();
	                    }
	                };
	                panel.removeMouseListener(ml);
	                panel.removeMouseMotionListener(motion);
	                panel.addMouseListener(ml);
	                panel.addMouseMotionListener(motion);
	                panel.repaint();
	            } else {
	                Object[] te = {"Cancel"};
                    int choice = JOptionPane.showOptionDialog(panel, "The Circle hasn't Loaded Yet", "Error", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, te, "Metric");
	            }
		    }
	        });
		squareb.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                if (ConstractorSquare != null) {
	                panel.removeMouseListener(ml);
	                panel.removeMouseMotionListener(motion);
	                ml = new MouseAdapter() {
	                    //int width;
	                    public void mousePressed(MouseEvent me) {
	                        //super.mouseClicked(me);
	                        coorX = me.getX();
	                        coorY = me.getY();
	                        panel.repaint();
	                    }
	                    
	                    public void mouseReleased(MouseEvent me) {
	                        int finishx = me.getX();
	                        int finishy = me.getY();
	                        lensel = Math.abs(finishx-coorX);
	                        //int width = Math.abs(finishy-coorY);
	                        try {
                                shapes.add((shape) ConstractorSquare.newInstance(coorX-lensel, coorY-lensel, lensel*2));
                                undoHistory.push(new actionDone((shape) ConstractorSquare.newInstance(coorX-lensel, coorY-lensel, lensel*2), null, indexS++));
                            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                                    | InvocationTargetException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
	                        //squares.add(new Square((coorX<finishx)?coorX:finishx, (coorY<finishy)?coorY:finishy, len));
	                        virtualX1 = 0;
	                        virtualX2 = 0;
	                        virtualY1 = 0;
	                        virtualY2 = 0;
	                        ShapeSelected = "";
	                        panel.repaint();
	                    }
	                };
	                
	                motion = new MouseMotionAdapter() {
	                    @Override
	                    public void mouseDragged(MouseEvent e) {
	                        // TODO Auto-generated method stub
	                        super.mouseDragged(e);
	                        
	                        virtualX1 = coorX;
	                        virtualY1 = coorY;
	                        virtualX2 = Math.abs(e.getX()-coorX);
	                        virtualY2 = (coorY < e.getY())?e.getY():coorY;
	                        ShapeSelected = "square";
	                        panel.repaint();
	                    }
	                };
	                panel.removeMouseListener(ml);
	                panel.removeMouseMotionListener(motion);
	                panel.addMouseListener(ml);
	                panel.addMouseMotionListener(motion);
	                panel.repaint();
	            } else {
	                Object[] te = {"Cancel"};
                    int choice = JOptionPane.showOptionDialog(panel, "The Square hasn't Loaded Yet", "Error", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, te, "Metric");
	            }
	            }
	        });      
		triangleb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ConstractorTriangle != null) {
                panel.removeMouseListener(ml);
                panel.removeMouseMotionListener(motion);
                ml = new MouseAdapter() {
                    public void mousePressed(MouseEvent me) {
                        //super.mouseClicked(me);
                        xPoint = new Point(me.getX(),me.getY());
                        coorX = me.getX();
                        coorY = me.getY();
                        panel.repaint();
                    }
                    
                    public void mouseReleased(MouseEvent me) {
                        yPoint = new Point(me.getX(), me.getY());
                        zPoint = new Point((int) (me.getX()+xPoint.getX()), me.getY());
                        try {
                            shapes.add((shape) ConstractorTriangle.newInstance(xPoint, yPoint, zPoint));
                            undoHistory.push(new actionDone((shape) ConstractorTriangle.newInstance(xPoint, yPoint, zPoint), null, indexS++));
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            //e.printStackTrace();
                        }
                        //triangles.add(new triangle(xPoint, yPoint, zPoint));
                        virtualX1 = 0;
                        virtualX2 = 0;
                        virtualY1 = 0;
                        virtualY2 = 0;
                        panel.repaint();
                        ShapeSelected = "";
                        panel.repaint();
                    }
                };
                motion = new MouseMotionAdapter() {
                    @Override
                    public void mouseDragged(MouseEvent e) {
                        // TODO Auto-generated method stub
                        super.mouseDragged(e);
                        virtualX1 = xPoint.x;
                        virtualY1 = xPoint.y;
                        virtualX2 = e.getX();
                        virtualY2 = e.getY();
                        ShapeSelected = "triangle";
                        panel.repaint();
                    }
                };
                } else {
                    Object[] te = {"Cancel"};
                    int choice = JOptionPane.showOptionDialog(panel, "The Triangle hasn't Loaded Yet", "Error", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, te, "Metric");
                }
                panel.removeMouseListener(ml);
                panel.removeMouseMotionListener(motion);
                panel.addMouseListener(ml);
                panel.addMouseMotionListener(motion);
                panel.repaint();
            }
        });
		colorb.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		        JColorChooser painting = new JColorChooser();
                Color c = JColorChooser.showDialog(null, "Please pick a color", Color.red);
                System.out.println("Colored");
                for (shape shape : shapes) {
                    if (shape != null) {
                    System.out.println("Recta " + shape.hashCode() + "    " + NowSelected);
                    if (shape.hashCode() == NowSelected) {
                        if (shape.GetType() == 1) {
                            shape s = new rectangle(shape.GetX(), shape.GetY(), shape.GetLength(), shape.GetWidth());
                            s.SetColor(shape.GetColor());
                            s.colored = shape.colored;
                            shape.SetColor(c);
                            shape.colored = true;
                            undoHistory.push(new actionDone(shape, s, IndexSelected));
                        } else if (shape.GetType() == 2) {
                            shape s = new Ellipse(shape.GetX(), shape.GetY(), shape.GetLength(), shape.GetWidth());
                            s.SetColor(shape.GetColor());
                            s.colored = shape.colored;
                            shape.SetColor(c);
                            shape.colored = true;
                            undoHistory.push(new actionDone(shape, s, IndexSelected));
                        } else if (shape.GetType() == 3) {
                            shape s = null;
                            try {
                                s = (shape) ConstractorLine.newInstance(shape.GetX(), shape.GetY(), shape.GetX2(), shape.GetY2());
                            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                                    | InvocationTargetException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            s.SetColor(shape.GetColor());
                            s.colored = shape.colored;
                            shape.SetColor(c);
                            shape.colored = true;
                            undoHistory.push(new actionDone(shape, s, IndexSelected));
                        } else {
                            shape s = null;
                            try {
                                s = (shape) ConstractorTriangle.newInstance(new Point(shape.GetX(), shape.GetY()), new Point(shape.GetX2(), shape.GetY2()), new Point(shape.GetX3(), shape.GetY3()));
                            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                                    | InvocationTargetException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            s.SetColor(shape.GetColor());
                            s.colored = shape.colored;
                            shape.SetColor(c);
                            shape.colored = true;
                            undoHistory.push(new actionDone(shape, s, IndexSelected));
                        }

                        panel.repaint();
                        break;
                    }
                }
            }
		    }
        });
		selectb.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent arg0) {
                panel.removeMouseListener(ml);
                panel.removeMouseMotionListener(motion);
                panel.addMouseListener( new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent me) {
                        super.mousePressed(me);
                        int counter = -1;
                        boolean NoneSelected = true;
                        for (shape b: shapes) {
                            counter++;
                            if (b != null && b.IsSelectedOrdi(me.getPoint())) {
                                NoneSelected = false;
                                NowSelected = b.hashCode();
                                IndexSelected = counter;
                                int type = 0;
                                if(b.GetType() == 1) {
                                    if (b.GetWidth() == b.GetLength()) {
                                        type = 0;
                                    } else {
                                        type = 1;
                                    }
                                    Resize(b,type);
                                } else if (b.GetType() == 2) {
                                    if (b.GetWidth() == b.GetLength()) {
                                        type = 3;
                                    } else {
                                        type = 2;
                                    }
                                    Resize(b,type);
                                } else if (b.GetType() == 3) {
                                    type = 4;
                                    Resize(b,type);
                                } else {
                                    type = 5;
                                    Resize(b,type);
                                }
                                break;
                            } else if (b == null) {
                                selectRec.clear();
                                Select = false;
                                NowSelected = 0;
                            }
                        }
                        if (NoneSelected) {
                            NowSelected = 0;
                            Select = false;
                        }
                    }
                });
            }
        });
		Extrab.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                JFileChooser loadfi = new JFileChooser();
                String path = "";
                if (loadfi.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    path = loadfi.getSelectedFile().getAbsolutePath();
                    JarFile jarFile = null;
                    try {
                        jarFile = new JarFile(path);
                    } catch (IOException e3) {
                        // TODO Auto-generated catch block
                        e3.printStackTrace();
                    }
                    Enumeration<JarEntry> ee = jarFile.entries();

                    URL[] urls;
                    URL xx = null;
                    try {
                        xx = new URL("jar:file:" + path+"!/");
                    } catch (MalformedURLException e3) {
                        // TODO Auto-generated catch block
                        e3.printStackTrace();
                    }
                    URL[] urlss={xx} ;
                    urls = urlss;
                    System.out.println(urls.toString());
                    URLClassLoader cl = URLClassLoader.newInstance(urls);

                    while (ee.hasMoreElements()) {
                        JarEntry je = ee.nextElement();
                        System.out.println(je.getName());
                        if(je.isDirectory() || !je.getName().endsWith(".class")){
                            continue;
                        }
                        System.out.println(je.getName());
                        // -6 because of .class
                        String className = je.getName().substring(0,je.getName().length()-6);
                        className = className.replace('/', '.');
                        System.out.println(className);
                        Class c = null;
                        try {
                            System.out.println(className);
                            System.out.println(cl);
                            c = cl.loadClass(className);
                            System.out.println(className);
                        } catch (ClassNotFoundException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                        try {
                            System.out.println(c.getName());
                            if (c.getName() == "paint.Circle"){
                                System.out.println("Loop");
                                ConstractorCircle = c.getDeclaredConstructor(int.class, int.class, int.class);
                                ConstractorCircle.setAccessible(true);
                            } else if (c.getName() == "paint.Square") {
                                ConstractorSquare = c.getDeclaredConstructor(int.class, int.class, int.class);
                                ConstractorSquare.setAccessible(true);
                            } else if (c.getName() == "paint.Line") {
                                ConstractorLine = c.getDeclaredConstructor(int.class, int.class, int.class, int.class);
                                ConstractorLine.setAccessible(true);
                            } else if (c.getName() == "paint.triangle") {
                                ConstractorTriangle = c.getDeclaredConstructor(Point.class, Point.class, Point.class);
                                ConstractorTriangle.setAccessible(true);
                            }
                        } catch (Exception e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                        System.out.println("End");
                    }
                  } else {
                      JOptionPane.showMessageDialog(panel, "There is no path selected");
                  }
            }
        });
		saveb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame parentFrame = new JFrame();
                
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Specify a file to save");   
                 
                int userSelection = fileChooser.showSaveDialog(parentFrame);
                 
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
                    Object[] te = {"XML" , "JSON"};
                    int choice = JOptionPane.showOptionDialog(panel, "Choose the type of save", "Save", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, te, "Metric"); 
                        if(choice == 0 ){
                            SaveFile = new SaveShapes(shapes);
                            SaveFile.writeXmlFile(fileToSave.getAbsolutePath()+".xml");
                        }else{
                            try {
                                SaveFile = new SaveShapes(shapes);
                                SaveFile.saveJson(fileToSave.getAbsolutePath()+".json");
                            } catch (JSONException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            } catch (IOException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                            }
                        }
            }
        });
		
		loadb.addActionListener(new ActionListener() {
		    @Override
            public void actionPerformed(ActionEvent e) {
            	JFileChooser loadfi = new JFileChooser();
            	String path = "";
            	if (loadfi.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            	    path = loadfi.getSelectedFile().getAbsolutePath();
            	    shapes.clear();
                    selectRec.clear();
                    undoHistory.clear();
                    redoHistory.clear();
                    if (ConstractorCircle == null || ConstractorLine == null || ConstractorSquare == null || ConstractorTriangle == null) {
                        JOptionPane.showMessageDialog(panel, "Propably, There are Some Shapes won't loaded Because they haven't added yet");
                    }
                    loadShapes loadFile = new loadShapes(ConstractorCircle, ConstractorLine, ConstractorSquare, ConstractorTriangle);
                    loadFile.print();
                    if (path.contains(".xml") && path != "") {
                      System.out.println("Xml");
                        shapes = loadFile.loadXML(path);
                    }
                    else if (path.contains(".json") && path != "") {
                        System.out.println("Json");
                        shapes = loadFile.loadJson(path);
                    }
                  } else {
                      JOptionPane.showMessageDialog(panel, "There is no path selected");
                  }
            	panel.repaint();
            }
          });
		deleteb.addActionListener(new ActionListener() {
		    @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
		        panel.removeMouseListener(ml);
		        panel.removeMouseMotionListener(motion);
                for (shape square : shapes) {
                    if(square != null && square.hashCode() == NowSelected) {
                        undoHistory.push(new actionDone(null, square, IndexSelected));
                        shapes.set(IndexSelected,null);
                        panel.repaint();
                        break;
                    }
                }
                selectRec.clear();
            }
        });
		whole.getContentPane().add(toolbar1, BorderLayout.WEST);
		whole.getContentPane().add(toolbar2, BorderLayout.EAST);
		whole.getContentPane().add(panel, BorderLayout.CENTER);
		whole.setSize(600, 600);
		whole.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		whole.setLocationRelativeTo(null);
		//whole.pack();
        whole.setVisible(true);
	}
	    private void InitializeButtons() {
        rectangleb = new JButton(rectangle);
        rectangleb.setBorder(new EmptyBorder(3, 0, 3, 0));
        ellipseb = new JButton(ellipse);
        ellipseb.setBorder(new EmptyBorder(3, 0, 3, 0));
        lineb = new JButton(line);
        lineb.setBorder(new EmptyBorder(3, 0, 3, 0));
        circleb = new JButton(circle);
        circleb.setBorder(new EmptyBorder(3, 0, 3, 0));
        triangleb = new JButton(triangle);
        triangleb.setBorder(new EmptyBorder(3, 0, 3, 0));
        squareb = new JButton(square);
        squareb.setBorder(new EmptyBorder(3, 0, 3, 0));
        colorb = new JButton(c);
        colorb.setBorder(new EmptyBorder(3, 0, 3, 0));
        Extrab = new JButton(plugin);
        Extrab.setBorder(new EmptyBorder(3, 0, 3, 0));
        saveb = new JButton(save);
        loadb = new JButton(load);
        undob = new JButton(undo);
        undob.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (undoHistory.size() > 0) {
                    undo();
                }
                Select = false;
                panel.repaint();
            }
        });
        redob = new JButton(redo);
        redob.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (redoHistory.size() > 0) {
                    redo();
                }
                Select = false;
                panel.repaint();
            }
        });
        selectb = new JButton(select);
        deleteb = new JButton(delete);
        saveb.setBorder(new EmptyBorder(3, 0, 3, 0));
        loadb.setBorder(new EmptyBorder(3, 0, 3, 0));
        deleteb.setBorder(new EmptyBorder(3, 0, 3, 0));
        selectb.setBorder(new EmptyBorder(3, 0, 3, 0));
        undob.setBorder(new EmptyBorder(3, 0, 3, 0));
        redob.setBorder(new EmptyBorder(3, 0, 3, 0));
    }
	    private void InitializeImages() {
	        rectangle = new ImageIcon(getClass().getResource("rectangle.png"));
	        ellipse = new ImageIcon(getClass().getResource("ellipse.png"));
	        line = new ImageIcon(getClass().getResource("line.png"));
	        circle = new ImageIcon(getClass().getResource("circle.png"));
	        triangle = new ImageIcon(getClass().getResource("triangle.png"));
	        square = new ImageIcon(getClass().getResource("square.png"));
	        c = new ImageIcon(getClass().getResource("color.png"));
	        save = new ImageIcon(getClass().getResource("save.png"));
	        load = new ImageIcon(getClass().getResource("load.png"));
	        delete = new ImageIcon(getClass().getResource("delete.png"));
	        undo = new ImageIcon(getClass().getResource("undo.png"));
	        redo = new ImageIcon(getClass().getResource("redo.png"));
	        select = new ImageIcon(getClass().getResource("cursor.png"));
	        plugin = new ImageIcon(getClass().getResource("plugin_add.png"));
	    }
	    
	    private void PutToolbarOne() {
	        toolbar1 = new JToolBar(JToolBar.VERTICAL);
	        toolbar1.setMargin(new Insets(10, 5, 5, 5));
	        toolbar1.setFloatable(false);
	        toolbar1.setLayout(new GridLayout(8, 1, 5, 5));
	        toolbar1.add(rectangleb);
	        toolbar1.add(lineb);
	        toolbar1.add(ellipseb);
	        toolbar1.add(circleb);
	        toolbar1.add(triangleb);
	        toolbar1.add(squareb);
	        toolbar1.add(colorb);
	    }
	    
	    private void PutToolbarTwo() {
	        toolbar2 = new JToolBar(JToolBar.VERTICAL);
	        toolbar2.setFloatable(false);
	        toolbar2.setMargin(new Insets(20, 20, 20, 20));
	        toolbar2.setLayout(new GridLayout(7, 1, 5, 5));
	        toolbar2.add(saveb);
	        toolbar2.add(loadb);
	        toolbar2.add(undob);
	        toolbar2.add(redob);
	        toolbar2.add(deleteb);
	        toolbar2.add(Extrab);
	        toolbar2.add(selectb);
	    }
	class RectsPanel extends JPanel {
		@Override
		public void setBackground(Color bg) {
			// TODO Auto-generated method stub
			super.setBackground(Color.white);
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
	        Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            //if (!Drag) {
            for (shape square : shapes) {
                if (square == null) {
                    continue;
                }
                square.draw(g2d);
            }
            if (Select){
			    for (rectangle recangle : selectRec) {
	                recangle.draw(g2d);
	            } 
            }
              if (ShapeSelected == "rectangle") {
                  shape x = new rectangle(virtualX1, virtualY1, Math.abs(virtualX1-virtualX2), Math.abs(virtualY1-virtualY2));
                  x.draw(g2d);
              } else if (ShapeSelected == "square") {
                  shape x = null;
                try {
                    x = (shape) ConstractorSquare.newInstance(virtualX1-virtualX2, virtualY1-virtualX2, 2*virtualX2);
                } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                        | InvocationTargetException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                  x.draw(g2d);
              } else if (ShapeSelected == "circle") {
                  shape x = null;
                try {
                    x = (shape) ConstractorCircle.newInstance(virtualX1-virtualX2, virtualY1-virtualX2, 2*virtualX2);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                  x.draw(g2d);
              } else if (ShapeSelected == "triangle") {
                  shape x = null;
                try {
                    x = (shape) ConstractorTriangle.newInstance(new Point(virtualX1,virtualY1),new Point(virtualX2, virtualY2),new Point(virtualX1+virtualX2, virtualY2));
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                  x.draw(g2d);
              } else if (ShapeSelected == "line") {
                  shape x = null;
                try {
                    x = (shape) ConstractorLine.newInstance(virtualX1, virtualY1, virtualX2, virtualY2);
                } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                        | InvocationTargetException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                  x.draw(g2d);
              } else if (ShapeSelected == "ellipse") {
                  shape x = new Ellipse(virtualX1, virtualY1, Math.abs(virtualY1-virtualY2), Math.abs(virtualX1-virtualX2));
                  x.draw(g2d);
              }
	}
	}
	
	int sele = 0;
	public void Resize(shape re, final int type) {
	    int x = 0;
	    int y = 0;
	    int wid = 0;
	    int len = 0;
	    int x2 = 0;
	    int y2 = 0;
	    int x3 = 0;
	    int y3 = 0;
	    if(type == 0) {
	        // Square
	        x = re.GetX();
	        y = re.GetY();
	        wid = re.GetLength();
	        len = re.GetLength();
	    } else if (type == 1) {
	        // Rectangle
	        x = re.GetX();
            y = re.GetY();
            wid = re.GetWidth();
            len = re.GetLength();
	    } else if (type == 2) {
            // Ellipse
	        x = re.GetX();
            y = re.GetY();
            len = re.GetWidth();
            wid = re.GetLength();;
        } else if (type == 3) {
            // Circle
            x = re.GetX();
            y = re.GetY();
            wid = re.GetLength();
            len = re.GetLength();
        } else if (type == 4) {
            // Line
            x = re.GetX();
            y = re.GetY();
            x2 = re.GetX2();
            y2 = re.GetY2();
        } else if (type == 5) {
            // triangle
            x = re.GetX();
            y = re.GetY();
            x2 = re.GetX2();
            y2 = re.GetY2();
            x3 = re.GetX3();
            y3 = re.GetY3();
        }
	    if (type == 4) {
            rectangle left = new rectangle(x, y, 5, 5);
            rectangle right = new rectangle(x2, y2, 5, 5);
            rectangle center = new rectangle((x+x2)/2, (y+y2)/2, 5, 5);
            selectRec.clear();
            selectRec.add(left);
            selectRec.add(right);
            selectRec.add(center);
	    } else if (type == 5) {
	        rectangle top = new rectangle(x, y, 5, 5);
            rectangle bottom = new rectangle(x2, y2, 5, 5);
            rectangle center = new rectangle((x+x2+x3)/3, (y+y2+y3)/3, 5, 5);
            selectRec.clear();
            selectRec.add(top);
            selectRec.add(bottom);
            selectRec.add(center);
	    }else {
	        rectangle top = new rectangle(x+len/2, y, 5, 5);
	        rectangle bottom = new rectangle(x+len/2, y+wid-5, 5, 5);
	        rectangle left = new rectangle(x, y+wid/2, 5, 5);
	        rectangle right = new rectangle(x+len-5, y+wid/2, 5, 5);
	        rectangle center = new rectangle(x+len/2, y+wid/2, 5, 5);
	        selectRec.clear();
	        selectRec.add(top);
	        selectRec.add(bottom);
	        selectRec.add(left);
	        selectRec.add(right);
	        selectRec.add(center);
	    }
	    panel.repaint();
	    Select = true;
	    panel.removeMouseListener(ml);
        panel.removeMouseMotionListener(motion);
        ml = new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                //super.mouseClicked(me);
                FromXselected = me.getX();
                FromYselected = me.getY();
                if (type != 4 && type != 5) {
                if (selectRec.get(0).IsSelectedOrdi(me.getPoint())) {
                    sele = 1;
                } else if (selectRec.get(1).IsSelectedOrdi(me.getPoint())) {
                    sele = 2;
                } else if (selectRec.get(2).IsSelectedOrdi(me.getPoint())) {
                    sele = 3;
                } else if (selectRec.get(3).IsSelectedOrdi(me.getPoint())) {
                    sele = 4;
                } else if (selectRec.get(4).IsSelectedOrdi(me.getPoint())) {
                    sele = 5;
                }
                } else {
                    if (selectRec.get(0).IsSelectedOrdi(me.getPoint())) {
                        sele = 1;
                    } else if (selectRec.get(1).IsSelectedOrdi(me.getPoint())) {
                        sele = 2;
                    } else if (selectRec.get(2).IsSelectedOrdi(me.getPoint())) {
                        sele = 5;
                    }
                }
            }
            public void mouseReleased(MouseEvent me) {
                System.out.println(sele);
                ToXselected = me.getX();
                ToYselected = me.getY();
                rectangle temp = null;
                rectangle temp2 = null;
                rectangle temp3 = null;
                rectangle temp4 = null;
                rectangle temp5 = null;
                if (sele != 0) {
                if (sele == 1 || sele == 2) {
                    temp = new rectangle(selectRec.get(sele-1).GetX(), selectRec.get(sele-1).GetY()+(ToYselected-FromYselected), selectRec.get(sele-1).GetLength(), selectRec.get(sele-1).GetWidth());
                    if (type != 4)
                    temp2 = new rectangle(selectRec.get(2).GetX(), selectRec.get(2).GetY()+(ToYselected-FromYselected), selectRec.get(2).GetLength(), selectRec.get(2).GetWidth());
                    else
                        temp2 = new rectangle(selectRec.get(2).GetX()+(ToXselected-FromXselected), selectRec.get(2).GetY()+(ToYselected-FromYselected), selectRec.get(2).GetLength(), selectRec.get(2).GetWidth());
                    if (type != 4 && type != 5) {
                    temp3 = new rectangle(selectRec.get(3).GetX(), selectRec.get(3).GetY()+(ToYselected-FromYselected), selectRec.get(3).GetLength(), selectRec.get(3).GetWidth());
                    temp4 = new rectangle(selectRec.get(4).GetX(), selectRec.get(4).GetY()+(ToYselected-FromYselected), selectRec.get(4).GetLength(), selectRec.get(4).GetWidth());
                    selectRec.remove(4);
                    selectRec.remove(3);
                    }
                    selectRec.remove(2);
                    selectRec.remove(sele-1);
                    selectRec.add(sele-1, temp);
                    selectRec.add(2, temp2);
                    if (type != 4 && type != 5) {
                    selectRec.add(3, temp3);
                    selectRec.add(4,temp4);
                    }
                }
                else if (sele == 3 || sele == 4){
                   if (type != 4)
                    temp = new rectangle(selectRec.get(sele-1).GetX()+(ToXselected-FromXselected), selectRec.get(sele-1).GetY(), selectRec.get(sele-1).GetLength(), selectRec.get(sele-1).GetWidth());
                   else
                       temp = new rectangle(selectRec.get(sele-1).GetX()+(ToXselected-FromXselected), selectRec.get(sele-1).GetY()+(ToYselected-FromYselected), selectRec.get(sele-1).GetLength(), selectRec.get(sele-1).GetWidth());  
                   temp2 = new rectangle(selectRec.get(0).GetX()+(ToXselected-FromXselected), selectRec.get(0).GetY(), selectRec.get(0).GetLength(), selectRec.get(0).GetWidth());
                   temp3 = new rectangle(selectRec.get(1).GetX()+(ToXselected-FromXselected), selectRec.get(1).GetY(), selectRec.get(1).GetLength(), selectRec.get(1).GetWidth());
                   if (type != 4 && type != 5) {
                   temp4 = new rectangle(selectRec.get(4).GetX()+(ToXselected-FromXselected), selectRec.get(4).GetY(), selectRec.get(4).GetLength(), selectRec.get(4).GetWidth());
                   selectRec.remove(4);
                   }
                   selectRec.remove(sele-1);
                   selectRec.remove(1);
                   selectRec.remove(0);
                   
                   selectRec.add(0, temp2);
                   selectRec.add(1, temp3);
                   selectRec.add(sele-1, temp);
                   if (type != 4 && type != 5) {
                   selectRec.add(4, temp4);
                   }
                   } else if(sele == 5) {
                       temp = new rectangle(selectRec.get(0).GetX()+(ToXselected-FromXselected), selectRec.get(0).GetY()+(ToYselected-FromYselected),5,5);
                       temp2 = new rectangle(selectRec.get(1).GetX()+(ToXselected-FromXselected), selectRec.get(1).GetY()+(ToYselected-FromYselected),5,5);
                       temp3 = new rectangle(selectRec.get(2).GetX()+(ToXselected-FromXselected), selectRec.get(2).GetY()+(ToYselected-FromYselected),5,5);
                       if (type != 4 && type != 5) {
                       temp4 = new rectangle(selectRec.get(3).GetX()+(ToXselected-FromXselected), selectRec.get(3).GetY()+(ToYselected-FromYselected),5,5);
                       temp5 = new rectangle(selectRec.get(4).GetX()+(ToXselected-FromXselected), selectRec.get(4).GetY()+(ToYselected-FromYselected),5,5);
                       selectRec.remove(4);
                       selectRec.remove(3);
                       }
                       selectRec.remove(2);
                       selectRec.remove(1);
                       selectRec.remove(0);
                       
                       selectRec.add(0, temp);
                       selectRec.add(1, temp2);
                       selectRec.add(2, temp3);
                       if (type != 4 && type != 5) {
                       selectRec.add(3, temp4);
                       selectRec.add(4, temp5);
                       }
                   }
                if (type == 0) {
                    shape y = null;
                    try {
                        y = (shape) ConstractorSquare.newInstance(selectRec.get(2).GetX(),selectRec.get(0).GetY(),(sele == 3 || sele == 4)?Math.abs(selectRec.get(2).GetX()-selectRec.get(3).GetX()):Math.abs(selectRec.get(0).GetY()-selectRec.get(1).GetY()));
                    } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                            | InvocationTargetException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    if (shapes.get(IndexSelected).GetColor().getRGB() != (Color.black).getRGB()){
                        y.colored = true;
                        }
                    y.SetColor(shapes.get(IndexSelected).GetColor());
                    undoHistory.push(new actionDone(y,shapes.get(IndexSelected),IndexSelected));
                    shapes.remove(IndexSelected);
                    shapes.add(IndexSelected, y);
                } else if (type == 1) {
                    shape y = new rectangle(selectRec.get(2).GetX(),selectRec.get(0).GetY(),Math.abs(selectRec.get(2).GetX()-selectRec.get(3).GetX()),Math.abs(selectRec.get(0).GetY()-selectRec.get(1).GetY()));
                    if (shapes.get(IndexSelected).GetColor().getRGB() != (Color.black).getRGB()){
                        y.colored = true;
                        }
                    y.SetColor(shapes.get(IndexSelected).GetColor());
                    undoHistory.push(new actionDone(y,shapes.get(IndexSelected),IndexSelected));
                    shapes.remove(IndexSelected);
                    shapes.add(IndexSelected, y);
                } else if (type == 2) {
                    shape y = new Ellipse(selectRec.get(2).GetX(),selectRec.get(0).GetY(),Math.abs(selectRec.get(1).GetY()-selectRec.get(0).GetY()),Math.abs(selectRec.get(2).GetX()-selectRec.get(3).GetX()));
                    if (shapes.get(IndexSelected).GetColor().getRGB() != (Color.black).getRGB()){
                        y.colored = true;
                        }
                    y.SetColor(shapes.get(IndexSelected).GetColor());
                    undoHistory.push(new actionDone(y,shapes.get(IndexSelected),IndexSelected));
                    shapes.remove(IndexSelected);
                    shapes.add(IndexSelected, y);
                } else if (type == 3) {
                    shape y = null;
                    try {
                        y = (shape) ConstractorCircle.newInstance(selectRec.get(2).GetX(),selectRec.get(0).GetY(),(sele == 3 || sele == 4)?Math.abs(selectRec.get(2).GetX()-selectRec.get(3).GetX()):Math.abs(selectRec.get(0).GetY()-selectRec.get(1).GetY()));
                    } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                            | InvocationTargetException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    if (shapes.get(IndexSelected).GetColor().getRGB() != (Color.black).getRGB()){
                        y.colored = true;
                        }
                    y.SetColor(shapes.get(IndexSelected).GetColor());
                    undoHistory.push(new actionDone(y,shapes.get(IndexSelected),IndexSelected));
                    shapes.remove(IndexSelected);
                    shapes.add(IndexSelected, y);
                } else if (type == 4) {
                    shape y = null;
                    try {
                        y = (shape) ConstractorLine.newInstance(selectRec.get(0).GetX(),selectRec.get(0).GetY(),selectRec.get(1).GetX(),selectRec.get(1).GetY());
                    } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                            | InvocationTargetException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    if (shapes.get(IndexSelected).GetColor().getRGB() != (Color.black).getRGB()){
                        y.colored = true;
                        }
                    y.SetColor(shapes.get(IndexSelected).GetColor());
                    undoHistory.push(new actionDone(y,shapes.get(IndexSelected),IndexSelected));
                    shapes.remove(IndexSelected);
                    shapes.add(IndexSelected, y);
                } else if (type == 5) {
                    shape y = null;
                    try {
                        y = (shape) ConstractorTriangle.newInstance(new Point(selectRec.get(0).GetX(),selectRec.get(0).GetY()),new Point(selectRec.get(1).GetX(),selectRec.get(1).GetY()),new Point(selectRec.get(1).GetX()+2*Math.abs(selectRec.get(0).GetX()-selectRec.get(1).GetX()),selectRec.get(1).GetY()));
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    if (shapes.get(IndexSelected).GetColor().getRGB() != (Color.black).getRGB()){
                        y.colored = true;
                        }
                    y.SetColor(shapes.get(IndexSelected).GetColor());
                    undoHistory.push(new actionDone(y,shapes.get(IndexSelected),IndexSelected));
                    shapes.remove(IndexSelected);
                    shapes.add(IndexSelected, y);
                }
                }
                sele = 0;
                panel.repaint();
            }
        };
        motion = new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // TODO Auto-generated method stub
                super.mouseDragged(e);
            }
        };
        panel.removeMouseListener(ml);
        panel.removeMouseMotionListener(motion);
        panel.addMouseListener(ml);
        panel.addMouseMotionListener(motion);
	    panel.repaint();
	}
	public void undo() {
        actionDone x = undoHistory.pop();
        redoHistory.push(x);
        shapes.set(x.getIndex(), x.getOld());
	}
	
	public void redo() {
	    actionDone x = redoHistory.pop();
	    undoHistory.push(x);
	    shapes.set(x.getIndex(), x.getNew());
	}
}