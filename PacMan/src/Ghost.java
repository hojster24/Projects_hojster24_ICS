import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.awt.image.*;
import java.util.Random;
import java.io.*;
/**
 * Write a description of class Ghost here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ghost
{
	Image img;
	//    Board b = new Board();
	int x = 250;
	int y = 250;
	boolean isMovingUp=false;
	boolean isMovingLeft=false;
	boolean isMovingDown=false;
	boolean isMovingRight=false;
	int speed = 5;
	public Ghost(int z,int n)
	{
		newDirection();
		try 
		{
			img = javax.imageio.ImageIO.read(this.getClass().getResource("ghost.png"));
		}
		catch (Exception e){}
		x = z;
		y = n;
	}

	public void draw (Graphics g,Board b)
	{
		if (isMovingUp)
		{
			if (b.isValid(x,y-5)&&b.isValid(x+15,y-5))
				moveUp();
			else newDirection();
		}
		else if(isMovingDown)
		{
			if (b.isValid(x,y+20)&&b.isValid(x+15,y+20))
				moveDown();
			else newDirection();
		}
		else if(isMovingLeft)
		{
			if (b.isValid(x-7,y)&&b.isValid(x-7,y+15))
				moveLeft();
			else newDirection();
		}
		else if(isMovingRight)
		{
			if (b.isValid(x+20,y)&&b.isValid(x+20,y+15))
				moveRight();  
			else newDirection();
		}
		g.drawImage(img,x,y,null);
	}

	public void newDirection()
	{
		int r=(int)(Math.random()*4+1);
		isMovingUp=false;
		isMovingDown=false;
		isMovingLeft=false; 
		isMovingRight=false; 
		if (r==1 )
			isMovingUp=true;
		if ( r==2 )
			isMovingDown=true;
		if (r==3)
			isMovingRight=true;
		if(r==4)
			isMovingLeft=true;
	}

	public void moveUp()
	{
		y = y - speed;

	}

	public void moveDown()
	{
		y = y + speed;
	}

	public void moveRight()
	{
		x = x + speed;
	}

	public void moveLeft()
	{
		x = x - speed;
	}
	public boolean isCollision(Graphics g,PacMan p,ScoreKeeper s)
	{
		if(p.x+15>x&&p.x<x+15&&p.y+15>y&&p.y<y+15) 
		{
			g.fillRect(x,y,10,10);
			p.lives--;
			p.reset();
			return true;
		}

		return false;
	}

}