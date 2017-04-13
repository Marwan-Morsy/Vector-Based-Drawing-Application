package paint;

import java.awt.Color;
import java.util.List;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class loadShapes {

    Constructor<?> ConstractorCircle;
    Constructor<?> ConstractorLine;
    Constructor<?> ConstractorSquare;
    Constructor<?> ConstractorTriangle;
    public loadShapes(Constructor<?> ConstractorCircle, Constructor<?> ConstractorLine, Constructor<?> ConstractorSquare, Constructor<?> ConstractorTriangle) {
        // TODO Auto-generated constructor stub
        this.ConstractorCircle = ConstractorCircle;
        this.ConstractorLine = ConstractorLine;
        this.ConstractorSquare = ConstractorSquare;
        this.ConstractorTriangle = ConstractorTriangle;
    }
    public void print (){
        System.out.println(ConstractorCircle);
        System.out.println(ConstractorLine);
        System.out.println(ConstractorSquare);
        System.out.println(ConstractorTriangle);
    }
    List<shape> shapes = new ArrayList<shape>();
    
    public List<shape> loadXML (String loadName) {
        //List<CD> cdList = new ArrayList<CD>();
          try {
           // Setup the parser
           DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
           DocumentBuilder builder = builderFactory.newDocumentBuilder();
           
           // Read the XML file
           File inputFile = new File(loadName);
           InputStream inputStream = new FileInputStream(inputFile);
           
           // Parse the XML file   
           org.w3c.dom.Document doc = builder.parse(inputStream);
           
           // Get all CD elements
           NodeList recElements = ((org.w3c.dom.Document) doc).getElementsByTagName("Rectangle");
           NodeList sqaElements = ((org.w3c.dom.Document) doc).getElementsByTagName("Square");
           NodeList triElements = ((org.w3c.dom.Document) doc).getElementsByTagName("Triangle");
           NodeList eliElements = ((org.w3c.dom.Document) doc).getElementsByTagName("Ellipse");
           NodeList circElements = ((org.w3c.dom.Document) doc).getElementsByTagName("Circle");
           NodeList linElements = ((org.w3c.dom.Document) doc).getElementsByTagName("Line");
           for ( int i = 0; i < recElements.getLength(); i++ ) {
            Node currentNode = recElements.item(i);
             int x = 0;
             int y = 0;
             int len = 0;
             int widd = 0;
             Color col = null;
             // Child elements under CD
             NodeList childNodes = currentNode.getChildNodes();
             for ( int j = 0; j < childNodes.getLength(); j++ ) {
              Node childNode = childNodes.item(j);
              
               if ( childNode.getNodeName().equalsIgnoreCase("X") ) {
                x = Integer.parseInt(childNode.getTextContent());
               }
               else if ( childNode.getNodeName().equalsIgnoreCase("Y") ) {
                y = Integer.parseInt(childNode.getTextContent());
               } else if ( childNode.getNodeName().equalsIgnoreCase("len") ) {
                    len = Integer.parseInt(childNode.getTextContent());
               } else if ( childNode.getNodeName().equalsIgnoreCase("wid") ) {
                        widd = Integer.parseInt(childNode.getTextContent());
               } else if ( childNode.getNodeName().equalsIgnoreCase("color") ) {
                   col = new Color(Integer.parseInt(childNode.getTextContent()));
               }
            }
             shape dummy = new rectangle(x, y, len, widd);
             if (col.getRGB() != (Color.black).getRGB()){
             dummy.colored = true;
             }
             dummy.SetColor(col);
              shapes.add(dummy);
              //rectangles.add(dummy);
           }
           for ( int i = 0; i < sqaElements.getLength(); i++ ){
                Node currentNode = sqaElements.item(i);
                 int x = 0;
                 int y = 0;
                 int len = 0;
                Color col = null; 
                 // Child elements under CD
                 NodeList childNodes = currentNode.getChildNodes();
                 for ( int j = 0; j < childNodes.getLength(); j++ ) {
                  Node childNode = childNodes.item(j);
                  
                   if ( childNode.getNodeName().equalsIgnoreCase("X") ) {
                    x = Integer.parseInt(childNode.getTextContent());
                   }
                   else if ( childNode.getNodeName().equalsIgnoreCase("Y") ) {
                    y = Integer.parseInt(childNode.getTextContent());
                   } else if ( childNode.getNodeName().equalsIgnoreCase("len") ) {
                        len = Integer.parseInt(childNode.getTextContent());
                   } else if ( childNode.getNodeName().equalsIgnoreCase("color") ) {
                       col = new Color(Integer.parseInt(childNode.getTextContent()));
                   }
                }
                 if (ConstractorSquare != null) {
                 shape dummy = (shape) ConstractorSquare.newInstance(x, y, len);
                 if (col.getRGB() != (Color.black).getRGB()){
                     dummy.colored = true;
                     }
                     dummy.SetColor(col);
                  shapes.add(dummy);
                 }
                  //squares.add(dummy);
               }
           for ( int i = 0; i < triElements.getLength(); i++ ) {
                  System.out.println(triElements.getLength());
                Node currentNode = triElements.item(i);
                 int x1 = 0;
                 int y1 = 0;
                 int x2 = 0;
                 int y2 = 0;
                 int x3 = 0;
                 int y3 = 0;
                 Color col = null;
                 // Child elements under CD
                 NodeList childNodes = currentNode.getChildNodes();
                 for ( int j = 0; j < childNodes.getLength(); j++ ) {
                  Node childNode = childNodes.item(j);
                  
                   if ( childNode.getNodeName().equalsIgnoreCase("X1") ) {
                    x1 = Integer.parseInt(childNode.getTextContent());
                   }
                   else if ( childNode.getNodeName().equalsIgnoreCase("Y1") ) {
                    y1 = Integer.parseInt(childNode.getTextContent());
                   } else if ( childNode.getNodeName().equalsIgnoreCase("X2") ) {
                        x2 = Integer.parseInt(childNode.getTextContent());
                   } else if ( childNode.getNodeName().equalsIgnoreCase("Y2") ) {
                            y2 = Integer.parseInt(childNode.getTextContent());
                   } else if ( childNode.getNodeName().equalsIgnoreCase("X3") ) {
                        x3 = Integer.parseInt(childNode.getTextContent());
                   } else if ( childNode.getNodeName().equalsIgnoreCase("Y3") ) {
                            y3 = Integer.parseInt(childNode.getTextContent());
                   } else if ( childNode.getNodeName().equalsIgnoreCase("color") ) {
                       col = new Color(Integer.parseInt(childNode.getTextContent()));
                   }
                }
                 if (ConstractorTriangle != null) {
                 shape dummy = (shape) ConstractorTriangle.newInstance(new Point(x1,y1), new Point(x2,y2), new Point(x3,y3));
                 if (col.getRGB() != (Color.black).getRGB()){
                     dummy.colored = true;
                     }
                     dummy.SetColor(col);
                  shapes.add(dummy);
                  //triangles.add(dummy);
                 }
               }
           for ( int i = 0; i < eliElements.getLength(); i++ ) {
                  System.out.println(eliElements.getLength());
                Node currentNode = eliElements.item(i);
                 int x = 0;
                 int y = 0;
                 int len = 0;
                 int widd = 0;
                 Color col = null;
                 // Child elements under CD
                 NodeList childNodes = currentNode.getChildNodes();
                 for ( int j = 0; j < childNodes.getLength(); j++ ) {
                  Node childNode = childNodes.item(j);
                  
                   if ( childNode.getNodeName().equalsIgnoreCase("X") ) {
                    x = Integer.parseInt(childNode.getTextContent());
                   }
                   else if ( childNode.getNodeName().equalsIgnoreCase("Y") ) {
                    y = Integer.parseInt(childNode.getTextContent());
                   } else if ( childNode.getNodeName().equalsIgnoreCase("len") ) {
                        len = Integer.parseInt(childNode.getTextContent());
                   } else if ( childNode.getNodeName().equalsIgnoreCase("wid") ) {
                            widd = Integer.parseInt(childNode.getTextContent());
                   } else if ( childNode.getNodeName().equalsIgnoreCase("color") ) {
                       col = new Color(Integer.parseInt(childNode.getTextContent()));
                   }
                }
                 shape dummy = new Ellipse(x, y, len, widd);
                 if (col.getRGB() != (Color.black).getRGB()){
                     dummy.colored = true;
                     }
                     dummy.SetColor(col);
                  shapes.add(dummy);
                  //ellipses.add(dummy);
               }
           for ( int i = 0; i < circElements.getLength(); i++ ) {
                  System.out.println(circElements.getLength());
                Node currentNode = circElements.item(i);
                 int x = 0;
                 int y = 0;
                 int len = 0;
                 Color col = null;
                 // Child elements under CD
                 NodeList childNodes = currentNode.getChildNodes();
                 for ( int j = 0; j < childNodes.getLength(); j++ ) {
                  Node childNode = childNodes.item(j);
                  
                   if ( childNode.getNodeName().equalsIgnoreCase("X") ) {
                    x = Integer.parseInt(childNode.getTextContent());
                   }
                   else if ( childNode.getNodeName().equalsIgnoreCase("Y") ) {
                    y = Integer.parseInt(childNode.getTextContent());
                   } else if ( childNode.getNodeName().equalsIgnoreCase("len") ) {
                        len = Integer.parseInt(childNode.getTextContent());
                   } else if ( childNode.getNodeName().equalsIgnoreCase("color") ) {
                       col = new Color(Integer.parseInt(childNode.getTextContent()));
                   }
                }
                 if (ConstractorCircle != null) {
                 shape dummy =(shape) ConstractorCircle.newInstance(x, y, len);
                 if (col.getRGB() != (Color.black).getRGB()){
                     dummy.colored = true;
                     }
                     dummy.SetColor(col);
                  shapes.add(dummy);
                 }
                  //circles.add(dummy);
               }
           for ( int i = 0; i < linElements.getLength(); i++ ) {
                  System.out.println(linElements.getLength());
                Node currentNode = linElements.item(i);
                 int x1 = 0;
                 int y1 = 0;
                 int x2 = 0;
                 int y2 = 0;
                 Color col = null;
                 // Child elements under CD
                 NodeList childNodes = currentNode.getChildNodes();
                 for ( int j = 0; j < childNodes.getLength(); j++ ) {
                  Node childNode = childNodes.item(j);
                  
                   if ( childNode.getNodeName().equalsIgnoreCase("X") ) {
                    x1 = Integer.parseInt(childNode.getTextContent());
                   }
                   else if ( childNode.getNodeName().equalsIgnoreCase("Y") ) {
                    y1 = Integer.parseInt(childNode.getTextContent());
                   } else if ( childNode.getNodeName().equalsIgnoreCase("X2") ) {
                        x2 = Integer.parseInt(childNode.getTextContent());
                   } else if ( childNode.getNodeName().equalsIgnoreCase("Y2") ) {
                            y2 = Integer.parseInt(childNode.getTextContent());
                   } else if ( childNode.getNodeName().equalsIgnoreCase("color") ) {
                       col = new Color(Integer.parseInt(childNode.getTextContent()));
                   }
                }
                 if (ConstractorLine != null) {
                 shape dummy = (shape) ConstractorLine.newInstance(x1, y1, x2, y2);
                 if (col.getRGB() != (Color.black).getRGB()){
                     dummy.colored = true;
                     }
                     dummy.SetColor(col);
                  shapes.add(dummy);
                 }
                  //lines.add(dummy);
               }
          } catch (Exception e) {
           e.printStackTrace();
          }
        return shapes;
    }

    public List<shape> loadJson (String loadName) {
        try{
        BufferedReader br = new BufferedReader(new FileReader(loadName));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        while(line != null) {
            sb.append(line);
            line = br.readLine();
        }
        String JsonData = sb.toString();
        JSONObject total = new JSONObject(JsonData);
        JSONObject Shapes = new JSONObject(total.getJSONObject("Shapes").toString());
        JSONArray Rectangles = new JSONArray(Shapes.getJSONArray("Rectangles").toString());
        JSONArray Squares = new JSONArray(Shapes.getJSONArray("Squares").toString());
        JSONArray Triangles = new JSONArray(Shapes.getJSONArray("Triangles").toString());
        JSONArray Circles = new JSONArray(Shapes.getJSONArray("Circles").toString());
        JSONArray Ellipses = new JSONArray(Shapes.getJSONArray("Ellipses").toString());
        JSONArray Lines = new JSONArray(Shapes.getJSONArray("Lines").toString());
        
        for (int i = 0; i < Rectangles.length(); i++) {
            JSONArray rectangle = new JSONArray(Rectangles.getJSONArray(i).toString());
            int x = rectangle.getInt(0);
            int y = rectangle.getInt(1);
            int len = rectangle.getInt(2);
            int wid = rectangle.getInt(3);
            shape dummy = new rectangle(x, y, len, wid);
            Color col = new Color(rectangle.getInt(4));
            if (col.getRGB() != (Color.black).getRGB()){
                dummy.colored = true;
            }
            dummy.SetColor(col);
            shapes.add(dummy);
            //rectangles.add(dummy);
        }
        for (int i = 0; i < Squares.length(); i++) {
            JSONArray square = new JSONArray(Squares.getJSONArray(i).toString());
            int x = square.getInt(0);
            int y = square.getInt(1);
            int len = square.getInt(2);
            if (ConstractorSquare != null) {
            shape dummy = (shape) ConstractorSquare.newInstance(x, y, len);
            Color col = new Color(square.getInt(4));
            if (col.getRGB() != (Color.black).getRGB()){
                dummy.colored = true;
             }
                dummy.SetColor(col);
            shapes.add(dummy);
            }
            //squares.add(dummy);
        }
        for (int i = 0; i < Triangles.length(); i++) {
            JSONArray triangle = new JSONArray(Triangles.getJSONArray(i).toString());
            int x1 = triangle.getInt(0);
            int y1 = triangle.getInt(1);
            int x2 = triangle.getInt(2);
            int y2 = triangle.getInt(3);
            int x3 = triangle.getInt(4);
            int y3 = triangle.getInt(5);
            if (ConstractorTriangle != null) {
            shape dummy = (shape) ConstractorTriangle.newInstance(new Point(x1, y1),new Point(x2, y2), new Point(x3, y3));
            Color col = new Color(triangle.getInt(6));
            if (col.getRGB() != (Color.black).getRGB()){
                dummy.colored = true;
                }
                dummy.SetColor(col);
                shapes.add(dummy);
            }
            //triangles.add(dummy);
        }
        for (int i = 0; i < Circles.length(); i++) {
            JSONArray circle = new JSONArray(Circles.getJSONArray(i).toString());
            int x = circle.getInt(0);
            int y = circle.getInt(1);
            int len = circle.getInt(2);
            if (ConstractorCircle != null) {
            shape dummy = (shape) ConstractorCircle.newInstance(x, y, len);
            Color col = new Color(circle.getInt(4));
            if (col.getRGB() != (Color.black).getRGB()){
                dummy.colored = true;
                }
                dummy.SetColor(col);
            shapes.add(dummy);
            }
            //circles.add(dummy);
        }
        for (int i = 0; i < Ellipses.length(); i++) {
            JSONArray ellipse = new JSONArray(Ellipses.getJSONArray(i).toString());
            int x = ellipse.getInt(0);
            int y = ellipse.getInt(1);
            int len = ellipse.getInt(2);
            int wid = ellipse.getInt(3);
            shape dummy = new Ellipse(x, y, len, wid);
            Color col = new Color(ellipse.getInt(4));
            if (col.getRGB() != (Color.black).getRGB()){
                dummy.colored = true;
                }
                dummy.SetColor(col);
            shapes.add(dummy);
            //ellipses.add(dummy);
        }
        for (int i = 0; i < Lines.length(); i++) {
            JSONArray linee = new JSONArray(Lines.getJSONArray(i).toString());
            int x1 = linee.getInt(0);
            int y1 = linee.getInt(1);
            int x2 = linee.getInt(2);
            int y2 = linee.getInt(3);
            if (ConstractorLine != null) {
            shape dummy = (shape) ConstractorLine.newInstance(x1, y1, x2, y2);
            Color col = new Color(linee.getInt(4));
            if (col.getRGB() != (Color.black).getRGB()){
                dummy.colored = true;
                }
                dummy.SetColor(col);
            shapes.add(dummy);
            }
            //lines.add(dummy);
        }
        } catch (Exception e) {
            System.out.println(e);
        }
        return shapes;
    }
    
}
