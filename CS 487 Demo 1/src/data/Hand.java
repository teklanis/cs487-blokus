package data;

import java.util.ArrayList;
import java.util.Iterator;


public class Hand {
        
        private ArrayList<Piece> aHand = new ArrayList<Piece>();
        private Piece lastPiecePlaced;

        /**
         * Creates an instance of a Hand.
         * 
         */
        public Hand() 
        {
                reset();
                lastPiecePlaced=null;
                addAllNewPieces();
        }
        
        public void reset()
        {
                aHand.clear();  
        }
        
        public int piecesLeft()
        {
                return aHand.size();
        }
        
        public void removePiece(Piece piece)
        {
                for (int x = 0; x < aHand.size(); x++)
                {
                        if (piece.getType() == aHand.get(x).getType())
                        {
                        		lastPiecePlaced = piece;
                                aHand.remove(x);
                                break;
                        }       
                }
        }
        public Piece getLastPiecePlaced()
        {
        	return lastPiecePlaced;
        }
        
        public Iterator<Piece> getIterator()
        {
                return aHand.iterator();
        }
        
        private void addAllNewPieces()
        {
                Piece.Type[] types = Piece.Type.values();
                for (int i = 0; i < types.length; i++)
                {
                        aHand.add(new Piece(types[i]));
                }
        }
}
