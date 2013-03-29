package data;

import java.util.ArrayList;
import java.util.Random;

public class AIRandom extends AI
{
        public AIRandom()
        {
                
        }

        @Override
        Move nextMove(Board board, Player player) throws RuntimeException
        {
                System.out.println("I've gotten to here");
                Random generator = new Random(); 
                ArrayList<Move> moves = super.getAvailableMoves(board, player);
                return moves.get(generator.nextInt(moves.size()));
        }
        
        public String toString()
        {
                return("Random AI");
        }

}
