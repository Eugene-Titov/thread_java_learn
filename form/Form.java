package form;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import ball.Ball;
import java.awt.event.*;

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
        
        g.drawString("hello", 100, 100);
        
        Graphics2D g2d = (Graphics2D)g;
        for(Ball b : listBalls){
            g2d.setPaint(b.getColor());
            g2d.fill(b.getEllipse());
            g2d.draw(b.getEllipse());
        }
    }
}

public class Form extends JFrame{

    private Scene scene = null;

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
    
    private void locateComponents(){
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
        GridBagConstraints cstart = getConstraints(0,0,0,0,1,1);
        buttons.add(start, cstart);
        
        JButton stop = new JButton("stop");
        GridBagConstraints cstop = getConstraints(0,0,1,0,1,1);
        buttons.add(stop, cstop);
        
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