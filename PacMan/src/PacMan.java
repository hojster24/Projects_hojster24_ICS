import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.Random;
import java.io.*;

public class PacMan 
{
	Image imgR,imgL,imgU,imgD;
	int speed = 5;
	int lives = 3;
	int x = 250;
	int y = 218;
	boolean isMovingUp=false;
	boolean isMovingLeft=false;
	boolean isMovingDown=false;
	boolean isMovingRight=false;
	boolean hostileMobs=true;
	String direction = "unset";
	public PacMan()
	{
		try
		{
			imgR = javax.imageio.ImageIO.read(this.getClass().getResource("Pacman.png"));  
		}
		catch (Exception e){}
		try
		{
			imgU = javax.imageio.ImageIO.read(this.getClass().getResource("PacmanU.png"));  
		}
		catch (Exception e){}
		try
		{
			imgD = javax.imageio.ImageIO.read(this.getClass().getResource("PacmanD.png"));  
		}
		catch (Exception e){}
		try
		{
			imgL = javax.imageio.ImageIO.read(this.getClass().getResource("PacmanL.png"));  
		}
		catch (Exception e){}
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

	//these makes sure the directions dont interfere and create diagonal motion
	public void draw(Graphics g,Board b,ScoreKeeper s)
	{

		if (isMovingUp)
		{
			direction ="up";
			if (b.isValid(x,y-5)&&b.isValid(x+15,y-5))
				moveUp();
		}
		else if(isMovingDown)
		{
			direction ="down";
			if (b.isValid(x,y+20)&&b.isValid(x+15,y+20))
				moveDown();
		}
		else if(isMovingLeft)
		{
			direction ="left";
			if (b.isValid(x-7,y)&&b.isValid(x-7,y+15))
				moveLeft();
		}
		else if(isMovingRight)
		{
			direction ="right";
			if (b.isValid(x+20,y)&&b.isValid(x+20,y+15))
				moveRight();  
		}
		if(b.isBigDot(x+5,y+5))
		{
			s.addScore(250);
			hostileMobs=false;
		}

		if(b.isSmallDot(x+5,y+5))
		{
			s.addScore(100);
		}
		if(direction =="up")
		{
			g.drawImage(imgU,x,y,null);
		}
		if(direction =="down")
		{
			g.drawImage(imgD,x,y,null);
		}
		if(direction =="left")
		{
			g.drawImage(imgL,x,y,null);
		}
		if(direction =="right")
		{
			g.drawImage(imgR,x,y,null);
		}
		if(direction == "unset")
		{
			g.drawImage(imgR,x,y,null);
		}
	}
	public void reset()
	{
		x = 250;
		y = 218;
	}
}
