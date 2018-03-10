package ball;

import java.awt.*;
import java.awt.geom.*;
import java.util.*;

public class Ball{
    private Ellipse2D.Double e = null;
    private Color color = null;
    
    public Ball(double x, double y){
        e = new Ellipse2D.Double(x - 15, y - 30, 30, 30);
        Random r = new Random();
        color = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
    }
    
    public void setLocation(Point2D.Double p){
        e = new Ellipse2D.Double(p.getX(), p.getY(), 30, 30);
    }
    
    public Point2D.Double getLocation(){
        return new Point2D.Double(e.getX(), e.getY());
    }
    
    public Color getColor(){
        return color;
    }
    
    public Ellipse2D.Double getEllipse(){
        return e;
    }
}