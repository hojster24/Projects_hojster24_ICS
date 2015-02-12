
/**
 * A class that is to represent a bar that goes back and forth
 * 
 * @author 
 * @version 
 */
import java.awt.*;
import java.util.Random;
public class Bar
{   
	Image img;
	int x=250;
	int y=250;
	int length = 50;
	int speedMod = 5;
	int speed;
	boolean movingRight = false;

	public Bar(int setY)
	{
		y=setY;
		reset();
	}
	//Resets values/booleans concerning the bars (trucks)
	public void reset()
	{
		Random directionSet = new Random();
		Random speedSet = new Random();
		speed = speedSet.nextInt(11)+speedMod;
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
			if(movingRight==false)
			{
				img = javax.imageio.ImageIO.read(this.getClass().getResource("TruckRight.png"));
			}
			else
			{
				img = javax.imageio.ImageIO.read(this.getClass().getResource("TruckLeft.png"));

			}
		}
		catch (Exception e){}
		
	}
	//Detects colision and reports it to the main framework (Frogger.java)
	public boolean isCollision(Frog theFrog)
	{
		if (theFrog.x+25>x && theFrog.x<x+50 && theFrog.y+25>y && theFrog.y<y+25)
		{ 
			return true;
		}
		else
		{
			return false;
		}
	}
	//the movement for the bars (trucks)
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
	
	public void draw(Graphics oG)
	{
        oG.drawImage(img,x,y,null);

	}
	
	public Color findRandomColor()
	{
		Random Rand = new Random();
		int r = Rand.nextInt(255);
		int g = Rand.nextInt(255);
		int b = Rand.nextInt(255);		
		Color random = new Color(r,g,b);

		return random;

	}
}
