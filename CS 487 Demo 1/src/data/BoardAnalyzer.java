package data;

import java.util.ArrayList;
import java.util.Iterator;

import data.Piece.Direction;

public class BoardAnalyzer {

        /**
         * Returns list of all available moves for given player for the current
         * board configuration.
         * 
         * @param board
         * @param player
         * @return a list of legal moves
         */
        public static ArrayList<Move> getAvailableMoves(Board board, Player player) throws RuntimeException
        {
                ArrayList<Pair> moveSpots = getPlayableSpots(board, player.getIndex());
                ArrayList<Move> moves = new ArrayList<Move>();
                
                System.out.println("Possible move spots: " + moveSpots.size());
                
                if (moveSpots.size() == 0)
                {
                        throw new RuntimeException("No available moves");
                }
                
                Hand playerHand = player.getHand();
                
                for(int x = 0; x < moveSpots.size(); x++)
                {
                        Pair currentSpot = moveSpots.get(x);
                        
                        //System.out.println("P" + player.getIndex() + " pieces: " + playerHand.piecesLeft() + "    loc:" + 
                        //              currentSpot.getRow() + "," + currentSpot.getCol());
                        
                        Iterator<Piece> i = playerHand.getIterator();
                        
                        while (i.hasNext())
                        {
                                Piece p = i.next();
                                if(canPlace(board, p, currentSpot.getRow(), currentSpot.getCol(), player.getIndex()))
                                {
                                        moves.add(new Move(Move.Type.Normal, p.clone(), currentSpot.getRow(),
                                                        currentSpot.getCol()));
                                }
                                
                                if (!p.isRotateInvariant())
                                { 
                                        p.rotate(Direction.Clockwise);
                                        
                                        if(canPlace(board, p, currentSpot.getRow(), currentSpot.getCol(), player.getIndex()))
                                        {
                                                moves.add(new Move(Move.Type.Normal, p.clone(), currentSpot.getRow(),
                                                                currentSpot.getCol()));
                                        }
                                        
                                        p.rotate(Direction.Clockwise);
                                        p.rotate(Direction.Clockwise);
                                        
                                        //checks for flip invariance before checking a flip.
                                        if(!p.isFlipInvariant() && 
                                                        canPlace(board, p, currentSpot.getRow(), 
                                                                        currentSpot.getCol(), player.getIndex()))
                                        {
                                                moves.add(new Move(Move.Type.Normal, p.clone(), currentSpot.getRow(),
                                                                currentSpot.getCol()));
                                        }
                                        p.rotate(Direction.Clockwise);
                                }
                                
                                if (!p.isFlipInvariant())
                                {
                                        p.rotate(Direction.Clockwise);
                                        p.rotate(Direction.Clockwise);
                                        
                                        if(canPlace(board, p, currentSpot.getRow(), currentSpot.getCol(), player.getIndex()))
                                        {
                                                moves.add(new Move(Move.Type.Normal, p.clone(), currentSpot.getRow(),
                                                                currentSpot.getCol()));
                                        }
                                }
                        }
                }
                
                return moves;
        }

        /**
         * Determines if a Piece can be placed legally at a specific location on the Board.
         * 
         * @param board the board configuration
         * @param piece the piece to be placed
         * @param row the row placing the piece
         * @param col the column placing the piece
         * @param player the player attempting to place the piece
         * @return true if the given player can place the specified piece at the specified location
         *                      returns false otherwise.
         */
        public static boolean canPlace(Board board, Piece piece, int row, int col, int player)
        {
                boolean cornerMatched = false;
                int bRow = 0;
                int bCol = 0;
                
                if(piece.getType() == Piece.Type.X)
                {
                        col = col - 1;
                }
                
                switch(player) {
                
                case 2:
                        col = col - piece.getWidth() + 3;
                        break;
                
                case 3:
                        col = col - piece.getWidth() + 3;
                        row = row - piece.getHeight() + 3;
                        break;
                
                case 4:
                        row = row - piece.getHeight() + 3;
                        break;
                        
                default:
                        //player one doesn't need adjusting.
                }
                
                System.out.println("trying " + piece.getType() + " at (" + row + "," + col + ")");
                for (int i= 0; i < piece.getHeight(); i++)
                {
                        for (int j= 0; j < piece.getWidth(); j++)
                        {
                                if (piece.getBlockType(i, j) == 1 || piece.getBlockType(i, j) == 2)
                                {
                                        bRow = row + i - 1;
                                        bCol = col + j - 1;
                                        if(false)
                                        {
                                        System.out.println("row: " + bRow + "    col: " + bCol + 
                                                        "     boardstate: " + board.getBlock(bRow, bCol) +
                                                        "     piecestate: " + piece.getBlockType(i, j) +
                                                        "     player: " + player);
                                        }
                                        if(!inRange(bRow, bCol) && 
                                                        (piece.getBlockType(i, j) == 1 || piece.getBlockType(i, j) == 2))
                                        {
                                                return false;
                                        }
                                        
                                        if (inRange(bRow, bCol))
                                        {
                                        
                                                if(board.getBlock(bRow, bCol) > 0 && board.getBlock(bRow, bCol) < 5 
                                                                || (inRange(bRow - 1, bCol) && (board.getBlock(bRow - 1, bCol) == player))
                                                                || (inRange(bRow + 1, bCol) && (board.getBlock(bRow + 1, bCol) == player))
                                                                || (inRange(bRow, bCol - 1) && (board.getBlock(bRow, bCol - 1) == player))
                                                                || (inRange(bRow, bCol + 1) && (board.getBlock(bRow, bCol + 1) == player)))
                                                
                                                {
                                                        System.out.println("Piece " + piece.getType() + "failed via collision at " +
                                                                      "(" + bRow + "," + bCol + ")");
                                                        return false;
                                                }
                                                
                                                if((board.getBlock(bRow, bCol) - 4 == player) 
                                                                && (piece.getBlockType(i, j) == 2))
                                                {
                                                        cornerMatched = true;
                                                }
                                        }
                                        
                                }
                        }
                }
                if(!cornerMatched) System.out.println("no corner match on " + piece.getType());
                return cornerMatched;
        }
        
        private static boolean inRange(int row, int col)
        {
                return (row >= 0 && row <= 19 && col >= 0 && col <= 19);
        }
        
        private static ArrayList<Pair> getPlayableSpots(Board board, int player)
        {
                ArrayList<Pair> playableSpots = new ArrayList<Pair>();
                int spotMatch = player + 4;
                for (int r = 0; r < 20; r++)
                {
                        for (int c = 0; c < 20; c++)
                        {
                                if (board.getBlock(r, c) == spotMatch)
                                {
                                        playableSpots.add(new Pair(r,c));
                                }
                        }
                }
                
                return playableSpots;
        }
}
