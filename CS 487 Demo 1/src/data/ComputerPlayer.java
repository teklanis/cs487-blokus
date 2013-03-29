package data;


public class ComputerPlayer extends Player {

        /** Name of the player */
        private static final String NAME = "Computer";
        
        AI thisAI;
        
        /** 
         * Creates an instance of Computer player.
         */
        public ComputerPlayer(int index, AI ai) {
                super(NAME, index);
                
                thisAI  = ai;
                
                hand = new Hand();
                
        }
        
        @Override
        public Move getNextMove(Board board)
        {
                Move aMove;
                try
                {
                        aMove = thisAI.nextMove(board, this);
                }
                catch(RuntimeException e)
                {
                        this.setHasMoreMoves(false);
                        System.out.println("No more moves");
                        aMove = new Move(Move.Type.Skip, null, 0, 0 );
                }
                
                return aMove;
        }
        
        public AI getAI()
        {
                return thisAI;
        }

}
