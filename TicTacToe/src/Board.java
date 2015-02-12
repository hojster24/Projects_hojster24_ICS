import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;

/**
 *This keeps track of the board.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 * Needs ints
 * 
 */
public class Board
{
    //0 represent blank
    //1 represent x
    //2=represent o
    int theBoard [][] = new int [3][3];
    int x=0;
    int y=0;
    public Board()
    {
        //James and Ahmed helped clarify this/
        //nothing is currently on the board 
        theBoard[0][0]= 0;        
        theBoard[0][1]= 0;
        theBoard[0][2]= 0;
        theBoard[1][0]= 0;
        theBoard[1][1]= 0;
        theBoard[1][2]= 0;
        theBoard[2][0]= 0;
        theBoard[2][1]= 0;        
        theBoard[2][2]= 0; 

    }

    //Test Method to print out the board
    public void printBoard()
    {
        for (int r=0;r<3;r++)
        {
            for (int c=0;c<3;c++)
                System.out.print(theBoard[r][c]);
            System.out.println();
        }
    }

    //draws the board and grid
    public void draw (Graphics g)
    {

        //below is for testing purposes
        //This draws the image
        Graphics2D g2 = (Graphics2D)g; 
        g2.setStroke(new BasicStroke(5));
        //now draw line as normal
        //draws the board
        g.drawLine(490,0,490,490);
        g.drawLine(150,0,150,490);
        g.drawLine(320,0,320,490);
        g.drawLine(0,150,490,150);
        g.drawLine(0,325,490,325);
        g.drawLine(0,490,490,490);
       //Changes the font and size
        g.setFont(new Font("Helvetica", Font.PLAIN, 80));

        for (int r=0;r<3;r++)
            for (int c=0;c<3;c++)
            { 
                if (theBoard[r][c]==1)
                    g.drawString("X",c*130+85,r*160+85);
                if (theBoard[r][c]==2)
                    g.drawString("O",c*130+85,r*160+85);

        }
    }
    //outcomes:
    //Undecided
    //Win
    //Tie
    //Loss
    public String GameStatus()
    {
    	String gameStatus = null;
        //Got alot of help from Andrew, Cedric and Landry and James
        //Searching for the win
        //It searches through every spot looking for a blank - if there is, Im returning the game is Undecided
        for (int r=0;r<3;r++)
        {
            if (theBoard[r][0]==1 && theBoard[r][1]==1 && theBoard [r][2]==1 )
            	gameStatus = "Win";
            else
            	gameStatus = "Undecided";
        }

        for (int r=0;r<3;r++)
        {
            if (theBoard[r][0]==2 && theBoard[r][1]==2 && theBoard [r][2]==2 )
            	gameStatus = "Loss";
            else
            	gameStatus = "Undecided";
        }

        for (int c=0;c<3;c++)
        {
            if (theBoard[0][c]==1 && theBoard[1][c]==1 && theBoard [2][c]==1 )
            	gameStatus = "Win";
            if (theBoard[0][c]==2 && theBoard[1][c]==2 && theBoard [2][c]==2 )
            	gameStatus = "Loss";
            if (theBoard[0][0]==1 && theBoard[1][1]==1 && theBoard [2][2]==1 )
            	gameStatus = "Win"; 
            if (theBoard[0][2]==1 && theBoard[1][1]==1 && theBoard [0][2]==1 )
            	gameStatus = "Win"; 
            if (theBoard[0][0]==2 && theBoard[1][1]==2 && theBoard [2][2]==2)
            	gameStatus = "Loss"; 
            if (theBoard[0][2]==1 && theBoard[1][1]==1 && theBoard [0][2]==1 )
            	gameStatus = "Loss"; 
            else
            	gameStatus = "Undecided";
        }
		return gameStatus;       


    }

    //lets user move if there is an open space
    //it will see if there is no one in the location where they pressed (mousex,mousey)
    // if it is empty it puts an X and return true, if there is already a mark there, it will return false
    public boolean makeUserMove(int mouseX, int mouseY)
    {
        //Andrew helped with this
        //below is for testing purposes
        //this just throughs an X any where that is empty
        int r=0;
        int c=0;       
        //When the the mouse is pressed it places a X
        // if there is already an X nothing will be placed
        if (mouseX>0 && mouseX < 163) 
        {
            c=0;
        }
        if (mouseX>150 && mouseX <320) 
        {
            c=1;
        }
        if (mouseX>320 && mouseX <490) 
        {
            c=2;
        }
        if (mouseY>0 && mouseY < 150) 
        {
            r=0;
        }
        if (mouseY>150 && mouseY <490) 
        {
            r=1;
        }
        if (mouseY>325 && mouseY <490) 
        {
            r=2;
        }
        if(theBoard[r][c] ==0)
        {
            theBoard[r][c]=1;
            return true;           
        }
        return false;
    }

    public int findRandom (int low, int high)
    {
        int num = (int) (Math.random()*(high-low+1))+low;
        return num;
    }
}
