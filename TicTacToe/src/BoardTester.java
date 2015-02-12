import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;

/**
 * 
 * 
 * @author (your name) 
 * @version (a version number or a date)
 * It is the applet
 * Not runable
 * Needs a mouse listener
 * Needs buttons preformed 
 * Randomly chooses who makes first move
 * If (computer plays first): Tells AI to make a move, 
 * draws board
 * Else: User selects where to go after prompt
 * Repeats until board returns a winner/tie
 * Congradulaates winner/laughs in your face
 * 
 */
public class BoardTester extends JApplet implements MouseListener
{

    Board myBoard=new Board();
    public void init()
    {           
       addMouseListener(this);
    }

    public void paint (Graphics g)
    {
        myBoard.draw(g);
    }
    
      //when someone presses the mouse button
    public void mousePressed(MouseEvent e)
    {       
        int x=e.getX();
        int y=e.getY();
        myBoard.makeUserMove(x,y);
        repaint();
    }

    //when someone releases the mouse button
    public void mouseReleased(MouseEvent e)
    {       

    }

    // when the mouse enters the applet
    public void mouseEntered(MouseEvent e)
    {       

    }

    //when the mouse leaves the applet
    public void mouseExited(MouseEvent e)
    {       

    }

    //when the mouse button is clicked
    public void mouseClicked(MouseEvent e)
    {
    }

 
}
