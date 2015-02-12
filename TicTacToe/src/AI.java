
/**
 * AI artificial intelligence class, gets the board and makes the best move 
 * possible.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AI
{

    //Attempts to make the best move against the player in this order:
    //1.looks for win
    //2. Looks to block player
    //3. Looks to play in the center
    //4. Sets up for a win
    //5. Chooses random corner
    //6. Chooses anywhere randomly
    public void makeMove(Board b)
    {
        //This is the start of the code for the AI choosing a winning space
        for (int r=0;r<b.theBoard.length;r++)
        {
            if (b.theBoard[r][0]==2 && b.theBoard[r][1]==2 && b.theBoard[r][2]==0)
            {
                b.theBoard[r][2]=2;
                return;
            }
            if (b.theBoard[r][0]==2 && b.theBoard[r][1]==0 && b.theBoard[r][2]==2)
            {
                b.theBoard[r][1]=2;
                return;
            }
            if (b.theBoard[r][0]==0 && b.theBoard[r][1]==2 && b.theBoard[r][2]==2)
            {
                b.theBoard[r][0]=2;
                return;
            }
            //Searches through every row to find a spot to put the third 'X' or 'O' (whichever is for AI)
        }
        for (int c=0;c<b.theBoard.length;c++)
        {
            if (b.theBoard[0][c]==2 && b.theBoard[1][c]==2 && b.theBoard[2][c]==0)
            {
                b.theBoard[2][c]=2;
                return;
            }
            if (b.theBoard[0][c]==2 && b.theBoard[1][c]==0 && b.theBoard[2][c]==2)
            {
                b.theBoard[1][c]=2;
                return;
            }
            if (b.theBoard[0][c]==0 && b.theBoard[1][c]==2 && b.theBoard[2][c]==2)
            {
                b.theBoard[0][c]=2;
                return;
            }
            //Searches through every column to find a spot to put the third 'X' or 'O' (whichever is for AI)
        }
        //The following if statements look for any diagonals that the AI can win 
        if (b.theBoard[0][0]==0 && b.theBoard[1][1]==2 && b.theBoard[2][2]==2)
        {    
            b.theBoard[0][0]=2;
            return;
        }
        if (b.theBoard[0][0]==2 && b.theBoard[1][1]==0 && b.theBoard[2][2]==2)
        {    
            b.theBoard[1][1]=2;
            return;
        }    
        if (b.theBoard[0][0]==2 && b.theBoard[1][1]==2 && b.theBoard[2][2]==0)
        {   
            b.theBoard[2][2]=2;
            return;
        }
        if (b.theBoard[2][0]==0 && b.theBoard[1][1]==2 && b.theBoard[0][2]==2)
        {
            b.theBoard[3][0]=2;
            return;
        }
        if (b.theBoard[2][0]==2 && b.theBoard[1][1]==0 && b.theBoard[0][2]==2)
        { 
            b.theBoard[1][1]=2;
            return;
        }
        if (b.theBoard[2][0]==2 && b.theBoard[1][1]==0 && b.theBoard[0][2]==2)
        {  
            b.theBoard[0][2]=2;
            return;
        }
        //This is the end of the code for the AI finding a winning space

        //This is the start of the AI trying to block the user from winning
        //This comes after the AI winning because that is more important
        for (int r=0;r<b.theBoard.length;r++)
        {
            if (b.theBoard[r][0]==1 && b.theBoard[r][1]==1 && b.theBoard[r][2]==0)
            {
                b.theBoard[r][2]=2;
                return;
            }
            if (b.theBoard[r][0]==1 && b.theBoard[r][1]==0 && b.theBoard[r][2]==1)
            {
                b.theBoard[r][1]=2;
                return;
            }
            if (b.theBoard[r][0]==0 && b.theBoard[r][1]==1 && b.theBoard[r][2]==1)
            {
                b.theBoard[r][0]=2;
                return;
            }
            //Searches every row for a spot to block the user from winning just like the code for winning
        }
        for (int c=0;c<b.theBoard.length;c++)
        {
            if (b.theBoard[0][c]==1 && b.theBoard[1][c]==1 && b.theBoard[2][c]==0)
            {
                b.theBoard[2][c]=2;
                return;
            }
            if (b.theBoard[0][c]==1 && b.theBoard[1][c]==0 && b.theBoard[2][c]==1)
            {
                b.theBoard[1][c]=2;
                return;
            }
            if (b.theBoard[0][c]==0 && b.theBoard[1][c]==1 && b.theBoard[2][c]==1)
            {
                b.theBoard[0][c]=2;
                return;
            }
            //Searches every column for a spot to block the user from winning just like the code for winning
        }
        //The following if statements look for any diagonals that the AI can block the user from winning
        if (b.theBoard[0][0]==0 && b.theBoard[1][1]==1 && b.theBoard[2][2]==1)
        {    
            b.theBoard[0][0]=2;
            return;
        }
        if (b.theBoard[0][0]==1 && b.theBoard[1][1]==0 && b.theBoard[2][2]==1)
        {    
            b.theBoard[1][1]=2;
            return;
        }    
        if (b.theBoard[0][0]==1 && b.theBoard[1][1]==1 && b.theBoard[2][2]==0)
        {   
            b.theBoard[2][2]=2;
            return;
        }
        if (b.theBoard[2][0]==0 && b.theBoard[1][1]==1 && b.theBoard[0][2]==1)
        {
            b.theBoard[3][0]=2;
            return;
        }
        if (b.theBoard[2][0]==1 && b.theBoard[1][1]==0 && b.theBoard[0][2]==1)
        { 
            b.theBoard[1][1]=2;
            return;
        }
        if (b.theBoard[2][0]==1 && b.theBoard[1][1]==0 && b.theBoard[0][2]==1)
        {  
            b.theBoard[0][2]=2;
            return;
        }
        //This is the end of the code to have the AI block the user from winning

        //This is to play in the center which prioritizes in front of setting up for a win because 
        //it can do that and indirectly block the user from winning as well
        for (int r=0;r<b.theBoard.length;r++)
        {
            if (b.theBoard[r][0]==1 && b.theBoard[r][1]==1 && b.theBoard[r][2]==0)
            {
                b.theBoard[r][2]=2;
                return;
            }
        }

        //This is the beginning of the code to have the AI set itself up for a win when the user has played in a spot other than the corners and center on their first move because this 
        //is the best thing to do that will give you a win for sure
        if (b.theBoard[0][0]==0 && b.theBoard[0][1]==0 && b.theBoard[0][2]==0 && b.theBoard[1][0]==0 && b.theBoard[1][1]==2 && b.theBoard[1][2]==1 &&  b.theBoard[2][0]==0 && b.theBoard[2][1]==0 && b.theBoard[2][2]==0)
        {
            b.theBoard[0][0]=2;
            return;
        }
        if (b.theBoard[0][0]==0 && b.theBoard[0][1]==0 && b.theBoard[0][2]==0 && b.theBoard[1][0]==0 && b.theBoard[1][1]==2 && b.theBoard[1][2]==1 &&  b.theBoard[2][0]==0 && b.theBoard[2][1]==0 && b.theBoard[2][2]==0)
        {
            b.theBoard[2][0]=2;
            return;
        }
        if (b.theBoard[0][0]==0 && b.theBoard[0][1]==0 && b.theBoard[0][2]==0 && b.theBoard[1][0]==1 && b.theBoard[1][1]==2 && b.theBoard[1][2]==0 &&  b.theBoard[2][0]==0 && b.theBoard[2][1]==0 && b.theBoard[2][2]==0)
        {
            b.theBoard[0][2]=2;
            return;
        }
        if (b.theBoard[0][0]==0 && b.theBoard[0][1]==0 && b.theBoard[0][2]==0 && b.theBoard[1][0]==1 && b.theBoard[1][1]==2 && b.theBoard[1][2]==0 &&  b.theBoard[2][0]==0 && b.theBoard[2][1]==0 && b.theBoard[2][2]==0)
        {
            b.theBoard[2][2]=2;
            return;
        }
        if (b.theBoard[0][0]==0 && b.theBoard[0][1]==0 && b.theBoard[0][2]==0 && b.theBoard[1][0]==0 && b.theBoard[1][1]==2 && b.theBoard[1][2]==0 &&  b.theBoard[2][0]==0 && b.theBoard[2][1]==1 && b.theBoard[2][2]==0)
        {
            b.theBoard[0][0]=2;
            return;
        }
        if (b.theBoard[0][0]==0 && b.theBoard[0][1]==0 && b.theBoard[0][2]==0 && b.theBoard[1][0]==0 && b.theBoard[1][1]==2 && b.theBoard[1][2]==0 &&  b.theBoard[2][0]==0 && b.theBoard[2][1]==1 && b.theBoard[2][2]==0)
        {
            b.theBoard[0][2]=2;
            return;
        }
        if (b.theBoard[0][0]==0 && b.theBoard[0][1]==1 && b.theBoard[0][2]==0 && b.theBoard[1][0]==0 && b.theBoard[1][1]==2 && b.theBoard[1][2]==0 &&  b.theBoard[2][0]==0 && b.theBoard[2][1]==0 && b.theBoard[2][2]==0)
        {
            b.theBoard[2][2]=2;
            return;
        }
        if (b.theBoard[0][0]==0 && b.theBoard[0][1]==1 && b.theBoard[0][2]==0 && b.theBoard[1][0]==0 && b.theBoard[1][1]==2 && b.theBoard[1][2]==0 &&  b.theBoard[2][0]==0 && b.theBoard[2][1]==0 && b.theBoard[2][2]==0)
        {
            b.theBoard[2][0]=2;
            return;
        }
        //These are exact scenarios where the AI will most definitely win
        //these if statements basically just say that if the user was to play their first move in a place other than a corner (and it wouldn't be in the center because the AI already has priority to 
        //play in the center before these statements are called) the AI would play it's turn in the either opposite corner that the user played 
        
        //these if statements are also apart of the setting up the win, they are a different scenario than above but will also lead to a "for sure" win for the AI
        if (b.theBoard[0][2]==2 && b.theBoard[1][1]==2 && b.theBoard[2][0]==1 && b.theBoard[1][2]==1 && b.theBoard[0][0]==0)
        {
            b.theBoard[0][0]=2;
            return;
        }
         if (b.theBoard[0][2]==2 && b.theBoard[1][1]==2 && b.theBoard[2][0]==1 && b.theBoard[0][1]==1 && b.theBoard[2][2]==0)
        {
           b.theBoard[2][2]=2;
            return;
        }
        if (b.theBoard[0][2]==1 && b.theBoard[1][1]==2 && b.theBoard[2][0]==2 && b.theBoard[1][0]==1 && b.theBoard[2][2]==0)
        {
            b.theBoard[2][2]=2;
            return;
        }
         if (b.theBoard[0][2]==1 && b.theBoard[1][1]==2 && b.theBoard[2][0]==2 && b.theBoard[2][1]==1 && b.theBoard[0][0]==0)
        {
           b.theBoard[0][0]=2;
            return;
        }
        if (b.theBoard[0][0]==2 && b.theBoard[1][1]==2 && b.theBoard[2][2]==1 && b.theBoard[1][0]==1 && b.theBoard[0][2]==0)
        {
            b.theBoard[0][2]=2;
            return;
        }
        if (b.theBoard[0][0]==2 && b.theBoard[1][1]==2 && b.theBoard[2][2]==1 && b.theBoard[0][1]==1 && b.theBoard[2][0]==0)
        {
            b.theBoard[2][0]=2;
            return;
        }
        if (b.theBoard[0][0]==1 && b.theBoard[1][1]==2 && b.theBoard[2][2]==2 && b.theBoard[1][2]==1 && b.theBoard[2][2]==0)
        {
            b.theBoard[2][2]=2;
            return;
        }
        if (b.theBoard[0][0]==1 && b.theBoard[1][1]==2 && b.theBoard[2][2]==2 && b.theBoard[1][2]==1 && b.theBoard[2][0]==0)
        {
            b.theBoard[2][0]=2;
            return;
        }
        //This is the end of set up to win, this is all I need because of how the priorities are set up in this code, so any other time the AI should be able to block the user and indirectly set itself up for winning

        //this is a for loop to look 100 times for a random corner to play in and if one's empty it will return, if not the AI will have checked 100 times which is very likely to have looked at all of the corners
        for  (int i=0;i<100;i++)
        {
            int R;
            int C;
            if (findRandom(0,1)==0)
                R=0;
            else
                R=2;
            if (findRandom(0,1)==0)
                C=0;
            else
                C=2;
            if (b.theBoard[R][C]==0)
            {
                b.theBoard[R][C]=2;
                return;
            }
        }
        for  (int i=0;i<100;i++)
        {
            int rrr;
            int ccc;
            if (findRandom(0,2)==0)
                rrr=0;
            else
                rrr=2;
            if (findRandom(0,2)==0)
                ccc=0;
            else
                ccc=2;
            if (b.theBoard[rrr][ccc]==0)
            {
                b.theBoard[rrr][ccc]=2;
                return;
            }
        }
        
        //this is the basic code that finds a random spot, sees if it's empty and if so, plays there
        int r=findRandom(0,2);
        int c=findRandom(0,2);
        if (b.theBoard[r][c]==0)
        {
            b.theBoard[r][c]=2;
            return;
        }
        else makeMove(b);  //in case our spot is not empty, we do it all over again
    }

    public int findRandom (int low, int high)
    {
        int num = (int) (Math.random()*(high-low+1))+low;
        return num;
    }

    //This testing method tries out your makemove method
    public void testMove()
    {
        Board b=new Board();
        System.out.println("Before the move:");
        b.printBoard();
        makeMove(b);
        System.out.println("After the move:");        
        b.printBoard();

    }
}
