import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


public class Ball {
	int speedX = 15;
	int speedY;
	int ballX = 250;
	int ballY = 250;
	int rBallLim;
	int lBallLim;
	int skew;
	boolean scoreR;
	boolean scoreL;

	
	//the balls wall bounce machanics, as well as
	//the booleans that tell when a ball is scored
	
	public void move()
	{
		if(ballX>=500)
		{
			scoreR = true;
		}
		if(ballX<=0)
		{
			scoreL = true;

		}
		if(ballY>=500||ballY<=50)
		{
			speedY = - speedY;
		}

		ballY = ballY + speedY;
		ballX = ballX + speedX;
	}

	//Makes the ball bounce depending on where it impacts the paddle
	
	public void bounce ()
	{
		speedY = speedY - (skew/4);
		skew = 0;
	}
	
	//Adds a new ball to the center, and points it in a random direction
	
	public void reset()
	{
		Random rand = new Random();
		if (rand.nextInt(2) == 0)
		{
			speedX = -speedX;
		}
		if (rand.nextInt(2) == 1)
		{
			speedX = speedX;
		}
		ballX = 250;
		ballY = 250;
		speedY = rand.nextInt(5);
	}

	//All of the ball visuals
	
	public void draw(Graphics oG)
	{
		//oG.setColor(Color.black);
		oG.setColor(Color.green);
		oG.fillOval(ballX,ballY,20,20);
	}

}

