package form;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import ball.Ball;
import java.awt.event.*;
import java.awt.geom.*;

class Scene extends JPanel{
    private java.util.List<Ball> listBalls = new ArrayList<>();

    @Override
    public Dimension getPreferredSize(){
        return new Dimension(600,400);
    }
    
    public void addBall(double x, double y){
        listBalls.add(new Ball(x, y));
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        //System.out.println("draw");
        
        g.drawString("hello", 100, 100);
        
        Graphics2D g2d = (Graphics2D)g;
        for(Ball b : listBalls){
            g2d.setPaint(b.getColor());
            g2d.fill(b.getEllipse());
            g2d.draw(b.getEllipse());
        }
    }
    
    public void flashing(){
        Random r = new Random();
        for(Ball b : listBalls){
            int dx = r.nextInt(5), dy = r.nextInt(5);
            int kx = r.nextInt(100) > 50 ? 1 : -1;
            int ky = r.nextInt(100) > 50 ? 1 : -1;
            dx *= kx;
            dy *= ky;
            
            Point2D.Double p = b.getLocation();
            p.setLocation(p.getX() + dx, p.getY() + dy);
            b.setLocation(p);
        }
        //repaint();
    }
}

public class Form extends JFrame{

    private Scene scene = null;
    private Thread t;

    public Form(){
        setTitle("thread3");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(dimension.width >> 2, dimension.height >> 2, dimension.width >> 1, dimension.height >> 1);
        
        addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent event){
                System.out.println("mouse");
                scene.addBall(event.getPoint().x, event.getPoint().y);
            }
        });
        
        locateComponents();
        setVisible(true);
    }
    
    private void locateComponents() {
        setLayout(new GridBagLayout());
        JPanel panel = new JPanel();
                
        GridBagConstraints c0 = getConstraints(0,0,0,0,1,1);
        c0.anchor = GridBagConstraints.NORTH;        
        add(panel, c0);
        
        panel.setLayout(new GridBagLayout());
                
        scene = new Scene();
        scene.setBackground(new Color(100,100,100));
        GridBagConstraints cscene = getConstraints(0,0,0,0,1,1);
        cscene.anchor = GridBagConstraints.NORTH;
        panel.add(scene, cscene);
        
        JPanel buttons = new JPanel();
        buttons.setBackground(new Color(0,255,0));
        GridBagConstraints cbuttons = getConstraints(0,0,0,1,1,1);        
        panel.add(buttons, cbuttons);
        
        buttons.setLayout(new GridBagLayout());
        JButton start = new JButton("start");
        
        Runnable r = () -> {
            try{
                for(int i = 0; i < 1000; ++i){
                    scene.flashing();
                    scene.repaint();
                    Thread.sleep(10);
                }
            }catch(InterruptedException e){
                System.out.println("interrupted");
            }finally{
            }
            
        };
        
        start.addActionListener( (event) -> {
            t = new Thread(r);
            t.start();
        });
        /*start.addActionListener( (event) -> {
            for(int i = 0; i < 1000; ++i){
                scene.flashing();
                scene.paint(scene.getGraphics());
                try{
                    Thread.sleep(100);
                }catch(InterruptedException e){}
            }
        });*/
        GridBagConstraints cstart = getConstraints(0,0,0,0,1,1);
        buttons.add(start, cstart);
        
        JButton stop = new JButton("stop");
        GridBagConstraints cstop = getConstraints(0,0,1,0,1,1);
        buttons.add(stop, cstop);
        stop.addActionListener( (event) -> { 
            t.interrupt();
            try{
                t.join();
            }catch(InterruptedException e){}
            //System.exit(0); 
        } );
        
        pack();
    }
    
    private GridBagConstraints getConstraints(int wx, int wy, int gx, int gy, int gw, int gh){
        GridBagConstraints g = new GridBagConstraints();
        g.weightx = wx;
        g.weighty = wy;
        g.gridx = gx;
        g.gridy = gy;
        g.gridwidth = gw;
        g.gridheight = gh;
        return g;
    }
}