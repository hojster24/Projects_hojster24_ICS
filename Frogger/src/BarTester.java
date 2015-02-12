/*
 * A class to test your bar class
 *
 * @author 
 * @date 
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class BarTester extends JApplet implements KeyListener 
{
    Bar bar1=new Bar(350);
    Bar bar2=new Bar(400);

    public void init()
    {
        addKeyListener(this);
        setFocusable(true);
        
        bar1.movingRight = false;
        bar2.movingRight = true;
    }

    public void paint (Graphics g)
    {        
        super.paint(g);
        bar1.draw(g);
        bar2.draw(g);
    }

    //Any key that is pressed will call bar1 move so that you can see how move works
    public void keyPressed(KeyEvent e)
    { 
        bar1.move();
        bar2.move();
        
        repaint();
    }

    public void keyReleased(KeyEvent e) { 
    }

    public void keyTyped(KeyEvent e) { 
    }  
}