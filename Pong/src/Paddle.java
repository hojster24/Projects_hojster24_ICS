
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.awt.image.*;
import java.util.Random;

public class Paddle {
	int x;
	int y = 250;
	int speed = 20;
	boolean movingUp = false;
	boolean movingDown = false;
	
	//Does all of the Paddle motion
	
	public void move()
	{
		if(movingUp == true&&y>=70)
		{
			y = y - speed;
		}
		if(movingDown == true&&y<=400)
		{
			y = y + speed;
		}
	}
	
	//Does all of the Paddle visuals
	
	public void draw (Graphics oG)
	{
		//oG.setColor(Color.black);
		oG.setColor(Color.green);
		oG.fillRect(x, y, 20, 100);
	}
}

