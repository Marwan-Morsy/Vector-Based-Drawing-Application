package paint;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Element;

public class SaveShapes {
    List<shape> rectangles = new ArrayList<>();
    List<shape> squares = new ArrayList<>();
    List<shape> ellipses = new ArrayList<>();
    List<shape> circles = new ArrayList<>();
    List<shape> lines = new ArrayList<>();
    List<shape> triangles = new ArrayList<>();
    org.w3c.dom.Document doc;
    
    public SaveShapes (List<shape> shapes) {
        ConvertToArrays(shapes);
    }
    
    private void ConvertToArrays(List<shape> shapes) {
        // TODO Auto-generated method stub
        for (shape sh : shapes) {
            if (sh != null) {
            if (sh.GetType() == 3) {
                lines.add(sh);
            } else if (sh.GetType() == 4) {
                triangles.add(sh);
            } else if (sh.GetType() == 2) {
                if (sh.GetLength() == sh.GetWidth())
                    circles.add(sh);
                else
                    ellipses.add(sh);
            } else if (sh.GetType() == 1) {
                if (sh.GetLength() == sh.GetWidth())
                    squares.add(sh);
                else
                    rectangles.add(sh);
            }
        }
        }
    }

    public void writeXmlFile(String saveName) {
        try {
            DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = dFact.newDocumentBuilder();
            doc = build.newDocument();

            Element root = doc.createElement("Shapes");
            doc.appendChild(root);

            Element Details = doc.createElement("Rectangles");
            root.appendChild(Details);

            
            for (shape dtl : rectangles) {
                Element reca = doc.createElement("Rectangle");
                Details.appendChild(reca);
                reca = PoillingPlate(dtl, reca);
                }
            Element Details2 = doc.createElement("Squares");
            root.appendChild(Details2);
            
            for (shape dtl : squares) {
                Element reca = doc.createElement("Square");
                Details2.appendChild(reca);
                reca = PoillingPlate(dtl, reca);
            }
            
            Element Details3 = doc.createElement("Triangles");
            root.appendChild(Details3);
            
            for (shape dtl : triangles) {
                Element reca = doc.createElement("Triangle");
                Details3.appendChild(reca);
                reca = PoillingPlate(dtl, reca);
                }

            Element Details4 = doc.createElement("Ellipses");
            root.appendChild(Details4);
            
            for (shape dtl : ellipses) {
                Element reca = doc.createElement("Ellipse");
                Details4.appendChild(reca);
                reca = PoillingPlate(dtl, reca);
                }
            
            Element Details5 = doc.createElement("Circles");
            root.appendChild(Details5);
            
            for (shape dtl : circles) {
                Element reca = doc.createElement("Circle");
                Details5.appendChild(reca);
                reca = PoillingPlate(dtl, reca);
                }

            Element Details6 = doc.createElement("Lines");
            root.appendChild(Details6);
            
            for (shape dtl : lines) {
                Element reca = doc.createElement("Line");
                Details6.appendChild(reca);
                reca = PoillingPlate(dtl, reca);
            }
            // Save the document to the disk file
            TransformerFactory tranFactory = TransformerFactory.newInstance();
            Transformer aTransformer = tranFactory.newTransformer();

            // format the XML nicely
            aTransformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");

            aTransformer.setOutputProperty(
                    "{http://xml.apache.org/xslt}indent-amount", "4");
            aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(doc);
            try {
                // location and name of XML file you can change as per need
                FileWriter fos = new FileWriter(saveName);
                StreamResult result = new StreamResult(fos);
                aTransformer.transform(source, result);

            } catch (IOException e) {

                e.printStackTrace();
            }

        } catch (TransformerException ex) {
            System.out.println("Error outputting document");

        } catch (ParserConfigurationException ex) {
            System.out.println("Error building document");
        }
    }
    private Element PoillingPlate(shape dtl, Element recaa) {
        // TODO Auto-generated method stub
        Element reca = recaa;
        if (dtl.GetType() == 3) {
            Element id = doc.createElement("X");
            id.appendChild(doc.createTextNode(String.valueOf(dtl.GetX())));
            reca.appendChild(id);

            Element mmi = doc.createElement("Y");
            mmi.appendChild(doc.createTextNode(String.valueOf(dtl.GetY())));
            reca.appendChild(mmi);
            
            Element le = doc.createElement("X2");
            le.appendChild(doc.createTextNode(String.valueOf(dtl.GetX2())));
            reca.appendChild(le);
            
            Element wi = doc.createElement("Y2");
            wi.appendChild(doc.createTextNode(String.valueOf(dtl.GetY2())));
            reca.appendChild(wi);
            if(dtl.GetType() == 4) {
                Element le2 = doc.createElement("X3");
                le2.appendChild(doc.createTextNode(String.valueOf(dtl.GetX3())));
                reca.appendChild(le2);
                
                Element wi2 = doc.createElement("Y3");
                wi2.appendChild(doc.createTextNode(String.valueOf(dtl.GetY3())));
                reca.appendChild(wi2);
            }
            Element col = doc.createElement("color");
            col.appendChild(doc.createTextNode(String.valueOf(dtl.GetColor().getRGB())));
            reca.appendChild(col);
            } else if (dtl.GetType() == 1 || dtl.GetType() == 2) {
        Element id = doc.createElement("X");
        id.appendChild(doc.createTextNode(String.valueOf(dtl.GetX())));
        reca.appendChild(id);

        Element mmi = doc.createElement("Y");
        mmi.appendChild(doc.createTextNode(String.valueOf(dtl.GetY())));
        reca.appendChild(mmi);
        
        Element le = doc.createElement("len");
        le.appendChild(doc.createTextNode(String.valueOf(dtl.GetLength())));
        reca.appendChild(le);
        
        Element wi = doc.createElement("wid");
        wi.appendChild(doc.createTextNode(String.valueOf(dtl.GetWidth())));
        reca.appendChild(wi);
        
        Element col = doc.createElement("color");
        col.appendChild(doc.createTextNode(String.valueOf(dtl.GetColor().getRGB())));
        reca.appendChild(col);
        }
        return reca;
    }
    
    public void saveJson(String saveName) throws JSONException, IOException {
        JSONObject obj = new JSONObject();
        JSONObject Shapes = new JSONObject();
        JSONArray Rrcts = new JSONArray();
        for (shape x : rectangles) {
            JSONArray Recte = new JSONArray();
            Recte = PoillingTwo(Recte,x);
            Rrcts.put(Recte);
        }
        JSONArray Squar = new JSONArray();
        for (shape x : squares) {
            JSONArray Recte = new JSONArray();
            Recte = PoillingTwo(Recte,x);
            Squar.put(Recte);
        }
        JSONArray tria = new JSONArray();
        for (shape x : triangles) {
            JSONArray Recte = new JSONArray();
            Recte = PoillingTwo(Recte,x);
            tria.put(Recte);
        }
        JSONArray ellip = new JSONArray();
        for (shape x : ellipses) {
            JSONArray Recte = new JSONArray();
            Recte = PoillingTwo(Recte,x);
            ellip.put(Recte);
        }
        JSONArray Circl = new JSONArray();
        for (shape x : circles) {
            JSONArray Recte = new JSONArray();
            Recte = PoillingTwo(Recte,x);
            Circl.put(Recte);
        }
        JSONArray Lin = new JSONArray();
        for (shape x : lines) {
            JSONArray Recte = new JSONArray();
            Recte = PoillingTwo(Recte,x);
            Lin.put(Recte);
        }
        Shapes.put("Rectangles", Rrcts);
        Shapes.put("Squares", Squar);
        Shapes.put("Triangles", tria);
        Shapes.put("Ellipses", ellip);
        Shapes.put("Circles", Circl);
        Shapes.put("Lines", Lin);
        obj.put("Shapes", Shapes);
        // try-with-resources statement based on post comment below :)
        try (FileWriter file = new FileWriter(saveName)) {
            file.write(obj.toString(4));
            file.close();
        };
    }

    private JSONArray PoillingTwo(JSONArray rectee, shape x) {
        // TODO Auto-generated method stub
        JSONArray Recte = rectee;
        Recte.put(x.GetX());
        Recte.put(x.GetY());
        if (x.GetType() == 1 || x.GetType() == 2){
        Recte.put(x.GetLength());
        Recte.put(x.GetWidth());
        } else {
            Recte.put(x.GetX2());
            Recte.put(x.GetY2());
            if (x.GetType() == 4) {
                Recte.put(x.GetX3());
                Recte.put(x.GetY3());
            }
        }
        Recte.put(x.GetColor().getRGB());
        return Recte;
    }
}