
/**
 * A class that is to represent a bar that goes back and forth
 * 
 * @author 
 * @version 
 */
import java.awt.*;
import java.util.Random;
public class Log
{
	Random xValue = new Random();
	Image img;
	int x=xValue.nextInt(200)+150;
	int y=250;
	int length = 75;
	int speedMod = 2;
	int speed;
	boolean movingRight = false;
	
	public Log(int setY)
	{
		y=setY;
		reset();
	}
	//Resets values/booleans concerning the logs
	public void reset()
	{
		Random directionSet = new Random();
		Random speedSet = new Random();
		speed = speedSet.nextInt(5)+speedMod;
		if(directionSet.nextInt(2)==1)
		{
			movingRight=true;
		}
		else
		{
			movingRight=false;
		}
		try
		{
			img = javax.imageio.ImageIO.read(this.getClass().getResource("log.png"));
		}
		catch (Exception e){}
	}
	//the speed of the log
	public int logSpeed()
	{
		return speed;
	}
	//Detects colision and reports it to the main framework (Frogger.java)
	public boolean isCollision(Frog theFrog)
	{
		if (theFrog.x+25>x && theFrog.x<x+75 && theFrog.y+25>y && theFrog.y<y+25)
		{ 
			return true;
		}
		else
		{
			return false;
		}
	}
	//the movement for the logs
	public void move ()
	{
		if(movingRight == true)
		{
			x = x + speed;
			if(x >= 500)
			{
				x = -45;
			}
		}
		if(movingRight == false)
		{
			x = x - speed;
			if(x <= -50)
			{
				x = 495;
			}
		}
	}

	public void draw(Graphics g)
	{
		//        g.setColor(Color.black);
		//        g.fillRect(x,y,75,25);
		g.drawImage(img,x,y,null);

	}
}
