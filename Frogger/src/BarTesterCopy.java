/*
 * A class to test your bar class
 *
 * @author 
 * @date 
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class BarTesterCopy extends JApplet implements KeyListener 
{
	Bar allBars[]={new Bar(200),new Bar(250),new Bar(300),new Bar(350),new Bar(400),new Bar(450)};


	public void init()
	{
		addKeyListener(this);
		setFocusable(true);
	}

	public void paint (Graphics g)
	{        
		super.paint(g);
		for(int i = 0; i<allBars.length; i++)
		{
			allBars[i].draw(g);
		}
	}

	//Any key that is pressed will call bar1 move so that you can see how move works
	public void keyPressed(KeyEvent e)
	{ 
		for(int i = 0; i<allBars.length; i++)
		{
			allBars[i].move();
		}

		repaint();
	}

	public void keyReleased(KeyEvent e) { 
	}

	public void keyTyped(KeyEvent e) { 
	}  
}