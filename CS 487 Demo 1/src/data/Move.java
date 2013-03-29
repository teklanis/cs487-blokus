package data;

public class Move {
	private Piece piece;
    
    private int row;
    
    private int col;
    
    /** Move type enumeration */
    public enum Type {
            Normal,
            Skip,
            Quit,
    }
    
    /** Move type */
    private Type type;
   
    /** 
     * Creates a Move of specified type.
     * 
     * @param type Move Type
     */
    public Move(Type type, Piece piece, int row, int col) {
            this.type = type;
            this.piece = piece;
            this.row = row;
            this.col = col;
    }
    
    /**
     * Returns the Type of this Move.
     * 
     * @return Type of this Move.
     */
    public Type getType() {
            return type;
    }
    
    public Piece getPiece() {
            return piece;
    }
    
    public int getRow() {
            return row;
    }

    public int getColumn() {
            return col;
    }
    
    public String toString()
    {
            if (this.getType() == Move.Type.Skip)
            {
                    return ("skipping a move");
            }
            else
            {
                    return ("placing " + getPiece().getType() + " at (" + getRow() + "," + getColumn() + ")"); 
            }
    }


}
