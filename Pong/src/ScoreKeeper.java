import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JLabel;

public class ScoreKeeper {
	int scoreR=0;
	int scoreL=0;

	//very simple, these methods add to their respective scores
	
	public void rightScored()
	{
		scoreR++;
	}
	public void leftScored()
	{
		scoreL++;
	}
}
