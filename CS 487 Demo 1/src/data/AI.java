package data;

import java.util.ArrayList;

public abstract class AI
{
        abstract Move nextMove(Board board, Player player);
        
        public ArrayList<Move> getAvailableMoves(Board board, Player player) throws RuntimeException
        {
                return BoardAnalyzer.getAvailableMoves(board, player);
        }
        
        public float distToMiddle(int row, int col)
        {
                return (float)(Math.sqrt((10-row)*(10-row) + (10-col)*(10-col)));
        }

}
