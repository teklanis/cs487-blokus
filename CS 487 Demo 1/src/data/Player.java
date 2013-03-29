package data;

import java.util.concurrent.ArrayBlockingQueue;

abstract public class Player implements Cloneable {

        /** Player move queue size.  Just need one for thread synchronization. */
        private static final int QUEUE_SIZE = 1;

		
        /**
         * Queue used to force Game's thread to wait for user input.
         */
        private ArrayBlockingQueue<Move> moveQueue;
        
        /** Stores player's hand */
        protected Hand hand;
        
        protected boolean hasMoreMoves;
        
        /** Player's name */
        private String name;
        
        /** Player's score */
        private int score;
        
        /** Player index. Represents the position at the table */
        private int index;
        
        private boolean autoProgress;
        
        /**
         * Creates an instance of Player.
         * 
         * @param name Player's display name.
         * @param index Player's position at the table.
         */
        public Player(String name, int index) {
                this.name = name;
                moveQueue = new ArrayBlockingQueue<Move>(QUEUE_SIZE);
                hasMoreMoves = true;
                this.index = index;
                autoProgress = true;
        }
        
        /**
         * Returns player's hand.
         * 
         * @return player's hand.
         */
        public Hand getHand() {
                return hand;
        }
        
        /**
         * Returns player's name.
         * 
         * @return player's name.
         */
        public String getName() {
                return name;
        }
        
        /**
         * Returns player's score.
         * 
         * @return player's score.
         */
        public int getScore() {
                return score;
        }
        
        public int getIndex() {
                return index;
        }
        
        public boolean isAutoProgress() {
                return autoProgress;
       }
        
        public void setAutoProgress(boolean auto) {
               autoProgress = auto;
        }
        
        /**
         * Adds score to player's total score.
         * 
         * @param score score to add.
         */
        public int addScore(int score) {
                this.score += score;
                return this.score;
        }

        /**
         * Reset player's state.
         */
        public void reset() {
                //hand.reset();
                score = 0;
        }
        
        public boolean hasMoreMoves() {
                return hasMoreMoves;
        }
        
        /**
         * Sets the _hasMoreMoves parameter
         * 
         * @param canMove true if the player can move, false if otherwise.
         */
        public void setHasMoreMoves(boolean canMove)
        {
                hasMoreMoves = canMove;
        }
        
        /**
         * Removes a Move from the MoveQueue.  This method blocks the
         * Game thread until a Move becomes available in the Queue.
         * 
         * @param monitor instance of GameMonitor.
         * @return next Move
         */
        public Move waitForNextMove(Board board) {
                Move move = null;

                if (isAutoProgress()) {
                        move = getNextMove(board);
                } else {
                        try {
                                move = moveQueue.take();
                        } catch (InterruptedException ex) {
                                // do nothing
                        }
                }
                
                return move;
        }
        
        /**
         * Should always be overridden
         * 
         * @param board the current board
         * @return the next move
         */
        public Move getNextMove(Board board) {
                throw new UnsupportedOperationException();
        }
        
        /**
         * Places a Move to the MoveQueue.  This method is called by UI
         * components to place user's input into the Queue.
         * 
         * @param move next Move.
         */
        public void putNextMove(Move move) {
                try {
                        moveQueue.put(move);
                } catch (InterruptedException ex) {
                        // do nothing
                }
        }
        
        public void abort() {
                // create an abort 
                Move move = new Move(Move.Type.Quit, null, 0, 0);
                putNextMove(move);
        }
        
        public Player clone() {
                return null;
        }
        

}