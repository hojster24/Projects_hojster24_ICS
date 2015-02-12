/*
 * A basic version of frogger that will have 5 bars going across and the user needs to avoid each one
 * The starting code is from HWJ12 - the square moving around the board.  I also threw runnable in there.
 *
 * @author 
 * @date 
 */

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
//All of this is very simple and needs little explaination
public class Frog
{
	Image img;
	AudioClip frogSound;
    int x=250;
    int y=475;
    int speed=5; 
    
    public Frog()
    {
    	frogSound = Applet.newAudioClip(this.getClass().getResource("frogger.wav"));
    	try
		{
				img = javax.imageio.ImageIO.read(this.getClass().getResource("Frog.png"));	
		}
		catch (Exception e){}
    }

    public void moveUp()
    {
    	y = y - 25;
    	frogSound.play();

    }

    public void moveDown()
    {
    	y = y + 25;
    	frogSound.play();

    }           

    public void moveRight()
    {
    	x = x + 25;
    	frogSound.play();

    }

    public void moveLeft()
    {
    	x = x - 25;
    	frogSound.play();

    }

    public void drawFrog(Graphics g)
    {
    	g.drawImage(img,x,y,null);
    } 

}