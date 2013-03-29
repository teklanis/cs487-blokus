package data;

import java.util.Observable;

public class Board extends Observable{

	public static final int X_DIMENSION = 20;
    
    public static final int Y_DIMENSION = 20;

    int[][] blocks;
   
   
    /**
     * Creates an empty board.
     */
    public Board()
    {
    	blocks = new int[Y_DIMENSION][X_DIMENSION];
    	blocks[0][0] = 5;
        blocks[0][19] = 6;
        blocks[19][19] = 7;
        blocks[19][0] = 8;

    	
    }
   
    
    public synchronized int getBlock(int row, int col)
    {
            try
            {
                    return blocks[row][col];
            }
            catch(Exception e)
            {
                    return -1;
            }
    }
   
    /**
     * Reset Board state.
     */
    public synchronized void reset() {
            blocks = new int[Y_DIMENSION][X_DIMENSION];
            blocks[0][0] = 5;
            blocks[0][19] = 6;
            blocks[19][19] = 7;
            blocks[19][0] = 8;
            
    }
   
    /**
     * Places a piece on the board. 
     * 
     * @param piece Blokus piece to place.
     * @param index Player index.
     */
    public synchronized void place(Piece piece, int row, int col, Player player)
    {
            int bRow, bCol;
            //System.out.println("placing: " + BoardAnalyzer.canPlace(this, piece, row, col, player.getIndex()));
            if (BoardAnalyzer.canPlace(this, piece, row, col, player.getIndex()))
            {
                    if(piece.getType() == Piece.Type.X)
                    {
                            col = col - 1;
                    }
                    
                    switch(player.getIndex())
                    {
                    
                    case 2:
                            col = (col - piece.getWidth()) + 3;
                            break;
                    
                    case 3:
                            col = (col - piece.getWidth()) + 3;
                            row = row - piece.getHeight() + 3;
                            break;
                    
                    case 4:
                            row = row - piece.getHeight() + 3;
                            col = col;
                            break;
                            
                    default:
                            //player one doesn't need adjusting.
                    }
                    
                    int placement = 0;
                    for (int i=0; i < piece.getHeight(); i++)
                    {
                            bRow = row + i - 1;
                            for (int j=0; j < piece.getWidth(); j++) 
                            {
                                    bCol = col + j - 1;
                                    if (inRange(bRow, bCol) && piece.hasBlock(i, j) && 
                                                    (blocks[bRow][bCol] == 0 || blocks[bRow][bCol] > 4)) 
                                    {
                                            placement = piece.getBlockType(i, j);
                                            if (placement == 3)
                                            {
                                                    placement = 4;
                                            }
                                            else
                                            {
                                                    placement = 0;
                                            }
                                            blocks[bRow][bCol] = placement + player.getIndex();
                                    }
                            }
                    }
            } else {
                    throw new RuntimeException("Can't place the piece on the board.");
            }
            
            player.getHand().removePiece(piece);
            
    }

   
    private boolean inRange(int row, int col)
    {
            return (row >= 0 && row <= 19 && col >= 0 && col <= 19);
    }

    @Override
    public synchronized Board clone() {
            Board copy = new Board();
            copy.blocks = blocks.clone();
            return copy;
    }
   
   
}


