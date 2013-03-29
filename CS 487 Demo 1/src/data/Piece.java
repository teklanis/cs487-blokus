package data;

import java.util.Arrays;

public class Piece {
	/** Maximum block sizes */
    public static final int MAX_X_BLOCKSIZE = 7;
    public static final int MAX_Y_BLOCKSIZE = 7;

    /** Default representative character. Used in toString() method. */
    public static final char DEFAULT_CHAR = '*';
    
    /** Block types */
    public enum Type {
            I,
            L,
            U,
            Z,
            T,
            X,
            W,
            V,
            F,
            P,
            Y,
            N,
            One,
            Two,
            Three,
            ShortI,
            ShortT,
            ShortL,
            ShortZ,
            Square,
            Crooked3,
    }
    
    /** Flip axis */
    public enum Axis {
            X,
            Y,
    }
    
    /** Rotate direction */
    public enum Direction {
            Clockwise,
            CounterClockwise,
    }
    
    /** Contains blocks. All blocks have the same capacity (i.e. MAX_Y_BLOCKSIZE by MAX_X_BLOCKSIZE) */
    private int[][] blocks;
    
    /** Piece's type */
    private Type type;
    
    /** Piece's horizontal width */
    private int width;
    
    /** Piece's vertical height */
    private int height;
    
    /** Number of blocks */
    private int numBlocks;
    
    /** Number of corner blocks */
    private int numCornerBlocks;
    
    /** Representative character. Used in toString(). */
    private char repchar;
    
    /** Specifies that the Piece is invariant under any flips. */
    private boolean flipInvariant;
    
    /** Specifies that the Piece is invariant under any rotation. */
    private boolean rotateInvariant;
    
    /** Specifies the number of playable spaces this piece would
     * create in a black board.
     */
    private int numPlayableSpots;
    
    /** Testing switch*/
    private Boolean testing = true;
    
    /** 
     * Creates a new Piece of the given type.
     * @param type Piece's type.
     */
    public Piece(Type type) {
            
            blocks = new int[MAX_Y_BLOCKSIZE][MAX_X_BLOCKSIZE];
            this.type = type;
            repchar = DEFAULT_CHAR;
            flipInvariant = false;
            rotateInvariant = false;
            
            // assign initial block properties
            switch(type) {
            
            case I:
                    numBlocks = 5; height = 7; width = 3; 
                    blocks[2][1] = blocks[3][1] = blocks[4][1] = 1;
                    blocks[1][1] = blocks[5][1] = 2;
                    blocks[0][0] = blocks[0][2] = blocks[6][0] = blocks [6][2] = 3;
                    
                    flipInvariant = true;
                    numCornerBlocks = 2;
                    numPlayableSpots = 4;
                    break;
                    
            case L:
                    numBlocks = 5; height = 6; width = 4;
                    blocks[2][1] = blocks[3][1] = 1;
                    blocks[1][1] = blocks[4][1] = blocks[4][2] = 2;
                    blocks[0][0] = blocks[0][2] = blocks[5][0] = blocks[3][3] = blocks[5][3] = 3;
                    
                    numCornerBlocks = 3;
                    numPlayableSpots = 5;
                    break;
                    
            case U:
                    numBlocks = 5; height = 4; width = 5;
                    blocks[2][2] = 1;
                    blocks[1][1] = blocks[1][3] = blocks[2][1] = blocks[2][3] = 2;
                    blocks[0][0] = blocks[0][2] = blocks[0][4] = blocks[3][0] = blocks[3][4] = 3;
                    
                    numPlayableSpots = 5;
                    numCornerBlocks = 4;
                    break;
                    
            case Z:
                    numBlocks = 5; height = 5; width = 5;
                    blocks[2][2] = 1;
                    blocks[1][1] = blocks[1][2] = blocks[3][2] = blocks[3][3] = 2;
                    blocks[0][0] = blocks[2][0] = blocks[0][3] = blocks[4][1] = blocks[4][4]
                                  = blocks[2][4] = 3;
                    
                    numPlayableSpots = 6;                                                                           
                    numCornerBlocks = 4;
                    break;
                    
            case T:
                    numBlocks = 5; height = 5; width = 5;
                    blocks[1][2] = blocks[2][2] = 1;
                    blocks[1][1] = blocks[1][3] = blocks[3][2] = 2;
                    blocks[0][0] = blocks[0][4] = blocks[4][1] = blocks[4][3] = blocks[2][0]
                                  = blocks[2][4] = 3;                                                             
                    
                    numPlayableSpots = 6;
                    numCornerBlocks = 3;
                    break;
                    
            case X:
                    numBlocks = 5; height = 5; width = 5;
                    blocks[2][2] = 1;
                    blocks[1][2] = blocks[2][1] = blocks[2][3] = blocks[3][2] = 2;
                    blocks[0][1] = blocks[0][3] = blocks[1][0] = blocks[1][4] = 3;
                    blocks[3][0] = blocks[3][4] = blocks[4][1] = blocks[4][3] = 3;
                    
                    numPlayableSpots = 8;
                    flipInvariant = rotateInvariant = true;
                    numCornerBlocks = 4;
                    break;
                    
            case W:
                    numBlocks = 5; height = 5; width = 5;
                    blocks[1][3] = blocks[2][2] = blocks[2][3] = blocks[3][1] = blocks[3][2] = 2;
                    blocks[1][1] = blocks[0][2] = blocks[2][0] = blocks[4][0] = blocks[0][4] = 3;
                    blocks[4][3] = blocks[3][4] = 3;
                    
                    numPlayableSpots = 7;
                    numCornerBlocks = 5;
                    break;
                    
            case V:
                    numBlocks = 5; height = 5; width = 5;
                    blocks[2][3] = blocks[3][2] = 1;
                    blocks[1][3] = blocks[3][1] = blocks[3][3] = 2;
                    blocks[0][2] = blocks[0][4] = blocks[2][0] = blocks [4][0] = 3;
                    blocks[4][4] = 3;
                    
                    numPlayableSpots = 5;
                    numCornerBlocks = 3;
                    break;
                    
            case F:
                    numBlocks = 5; height = 5; width = 5;
                    blocks[2][2] = 1;
                    blocks[1][2] = blocks[1][3] = blocks[2][1] = blocks[3][2] = 2;
                    blocks[0][1] = blocks[0][4] = blocks[1][0] = blocks[3][0] = 3;
                    blocks[4][1] = blocks[4][3] = blocks[2][4] = 3;
                    
                    numPlayableSpots = 7;
                    numCornerBlocks = 4;
                    break;
                    
            case P:
                    numBlocks = 5; height = 5; width = 4;
                    blocks[2][1] = 1;
                    blocks[1][1] = blocks[1][2] = blocks[2][2] = blocks[3][1] = 2;
                    blocks[0][0] = blocks[0][3] = blocks[4][0] = blocks[4][2] = 3;
                    blocks[3][3] = 3;
                    
                    numPlayableSpots = 5;
                    numCornerBlocks = 4;
                    break;
                    
            case Y:
                    numBlocks = 5; height = 4; width = 6;
                    blocks[2][2] = blocks[2][3] = 1;
                    blocks[1][2] = blocks[2][1] = blocks[2][4] = 2;
                    blocks[0][1] = blocks[0][3] = blocks[1][0] = blocks[3][0] = 3;
                    blocks[1][5] = blocks[3][5] = 3;
                    
                    numPlayableSpots = 6;
                    numCornerBlocks = 3;
                    break;
                    
            case N:
                    numBlocks = 5; height = 4; width = 6;
                    blocks[1][3] = 1;
                    blocks[1][2] = blocks[1][4] = blocks[2][1] = blocks[2][2] = 2;
                    blocks[0][1] = blocks[0][5] = blocks[1][0] = blocks[2][5] = 3;
                    blocks[3][0] = blocks[3][3] = 3;
                    
                    numPlayableSpots = 6;
                    numCornerBlocks = 4;
                    break;
                    
            case One:
                    numBlocks = 1; height = 3; width = 3;
                    blocks[1][1] = 2;
                    blocks[0][0] = blocks[0][2] = blocks[2][0] = blocks[2][2] = 3;
                    
                    numPlayableSpots = 4;
                    flipInvariant = rotateInvariant = true;
                    numCornerBlocks = 1;
                    break;
                    
            case Two:
                    numBlocks = 2; height = 3; width = 4;
                    blocks[1][1] = blocks[1][2] = 2;
                    blocks[0][0] = blocks[0][3] = blocks[2][0] = blocks[2][3] = 3;
                    
                    numPlayableSpots = 4;
                    flipInvariant = true;
                    numCornerBlocks = 2;
                    break;
                    
            case Three:
                    numBlocks = 3; height = 3; width = 5;
                    blocks[1][2] = 1;
                    blocks[1][1] =blocks[1][3] = 2;
                    blocks[0][0] = blocks[0][4] = blocks[2][0] = blocks[2][4] = 3;
                    
                    numPlayableSpots = 4;
                    flipInvariant = true;
                    numCornerBlocks = 2;
                    break;
                    
            case ShortI:
                    numBlocks = 4; height = 6; width = 3;
                    blocks[2][1] = blocks[3][1] = 1;
                    blocks[1][1] = blocks[4][1] = 2;
                    blocks[0][0] = blocks[0][2] = blocks[5][0] = blocks[5][2] = 3;
                    
                    numPlayableSpots = 4;
                    flipInvariant = true;
                    numCornerBlocks = 2;
                    break;
                    
            case ShortT:
                    numBlocks = 4; height = 4; width = 5;
                    blocks[1][2] = 1;
                    blocks[1][1] = blocks[1][3] = blocks[2][2] = 2;
                    blocks[0][0] = blocks[2][0] = blocks[0][4] = blocks[2][4] = 3;
                    blocks[3][1] = blocks[3][3] = 3;
                    
                    numPlayableSpots = 6;
                    numCornerBlocks = 3;
                    break;
                    
            case ShortL:
                    numBlocks = 4; height = 5; width = 4;
                    blocks[2][1] = 1;
                    blocks[1][1] = blocks[3][1] = blocks[3][2] = 2;
                    blocks[0][0] = blocks[0][2] = blocks[4][0] = blocks[4][3] = 3;
                    blocks[2][3] = 3;
                    
                    numPlayableSpots = 5;
                    numCornerBlocks = 3;
                    break;
                    
            case ShortZ:
                    numBlocks = 4; height = 4; width = 5;
                    blocks[1][1] = blocks[1][2] = blocks[2][2] = blocks[2][3] = 2;
                    blocks[0][0] = blocks[0][3] = blocks[2][0] = blocks[3][1] = 3;
                    blocks[1][4] = blocks[3][4] = 3;
                    
                    numPlayableSpots = 6;
                    numCornerBlocks = 4;
                    break;
                    
            case Square:
                    numBlocks = 4; height = 4; width = 4;
                    blocks[1][1] = blocks[1][2] = blocks[2][1] = blocks[2][2] = 2;
                    blocks[0][0] = blocks[0][3] = blocks[3][0] = blocks[3][3] = 3;
                    
                    flipInvariant = rotateInvariant = true;
                    numPlayableSpots = 4;
                    numCornerBlocks = 4;
                    break;
                    
            case Crooked3:
                    numBlocks = 3; height = 4; width = 4;
                    blocks[1][1] = blocks[2][1] = blocks[2][2] = 2;
                    blocks[0][0] = blocks[0][2] = blocks[3][0] = blocks[3][3] = 3;
                    blocks[1][3] = 3;
                    
                    numPlayableSpots = 5;
                    numCornerBlocks = 3;
                    break;
                    
            default:
                    throw new RuntimeException("Unknown piece type: " + type);
            }
    }
    
    /**
     * Returns Piece's type.
     * @return Piece's type.
     */
    public Type getType() {
            return type;
    }
    
    /**
     * Returns the horizontal width of the Piece.
     * @return the horizontal width of the Piece.
     */
    public int getWidth() {
            return width;
    }
    
    /**
     * Returns the vertical height of the Piece.
     * @return the vertical height of the Piece.
     */
    public int getHeight() {
            return height;
    }
    
    /**
     * Returns the number of blocks.
     * @return the number of blocks.
     */
    public int getNumBlocks() {
            return numBlocks;
    }
    
    /**
     * Returns the number of corner blocks.
     * @return the number of corner blocks.
     */
    public int getNumCorners() {
            return numCornerBlocks;
    }
    
    /**
     * Returns the number of playable spots this piece would create
     * if placed on an empty board.
     * @return the number of playable spots
     */
    public int getPlayableSpots()
    {
            return numPlayableSpots;
    }
    
    /**
     * Returns the blocks array.
     * @return the blocks array.
     */
    public int[][] getBlocks() {
            return blocks.clone();
    }
    
    /**
     * Returns the representative character of the Piece.
     * @return the representative character of the Piece.
     */
    public char getRepresentingChar() {
            return repchar;
    }
    
    /**
     * Sets the representative character of the Piece.
     * @param ch new character
     */
    public void setRepresentingChar(char ch) {
            repchar = ch;
    }
    
    /**
     * Specifies if the Piece is invariant under any flips.
     * @return true if the Piece is invariant under any flips.
     */
    public boolean isFlipInvariant() {
            return flipInvariant;
    }
    
    /**
     * Specifies if the Piece is invariant under any rotation.
     * @return true if the Piece is invariant under any rotation.
     */
    public boolean isRotateInvariant() {
            return rotateInvariant;
    }
    
    /**
     * Indicates if there is a block in the given location.
     * @param row row index
     * @param col column index
     * @return true if there is a block, else false.
     */
    public boolean hasBlock(int row, int col) {
            boolean result = false;
            if (0 <= row && row < height && 0 <= col && col < width)
            {
                    result = blocks[row][col] != 0;
            }
            return result;
    }
    
    /**
     * Indicates if there is a corner block at the specified location.
     * @param row row index
     * @param col column index
     * @return true if there is a corner block, else false.
     */
    public boolean hasCorner(int row, int col) {
            boolean result = false;
            if (blocks[row][col] == 2)
            {
                    result = true;
            }
            return result;
    }
    
    /**
     * Returns the type of block at the specified location
     * 
     * @param row row index
     * @param col column index
     * @return the int corresponding to the type of block at that location
     */
    public int getBlockType(int row, int col)
    {
            return blocks[row][col];
    }
    
    /**
     * Flip the Piece about the specified axis.
     * @param axis axis.
     */
    public void flip(Axis axis) {
            
            int[][] temp = new int[MAX_Y_BLOCKSIZE][MAX_X_BLOCKSIZE];
            
            if (axis == Axis.X) {
                    
                    // flip about x-axis
                    for (int row = 0; row < height; row++) {
                            for (int col = 0; col < width; col++) {
                                    temp[height-row-1][col] = blocks[row][col];
                            }
                    }
                    
            } else {
                    
                    // flip about y-axis
                    for (int row = 0; row < height; row++) {
                            for (int col = 0; col < width; col++) {
                                    temp[row][width-col-1] = blocks[row][col];
                            }
                    }
                    
            }
            
            blocks = temp;
    }
    
    /**
     * Rotate the Piece 90 degrees in the specified direction.
     * @param dir direction of rotation
     */
    public void rotate(Direction dir) {
            
            int[][] temp = new int[MAX_Y_BLOCKSIZE][MAX_X_BLOCKSIZE];
            
            if (dir == Direction.Clockwise) {
                    
                    // rotate in clockwise direction
                    for (int row = 0; row < height; row++) {
                            for (int col = 0; col < width; col++) {
                                    temp[col][height-row-1] = blocks[row][col];
                            }
                    }
                    
            } else {
                    
                    // rotate in counter-clockwise direction
                    for (int row = 0; row < height; row++) {
                            for (int col = 0; col < width; col++) {
                                    temp[width-col-1][row] = blocks[row][col];
                            }
                    }
                    
            }
            
            blocks = temp;
            int prevWidth = width;
            width = height;
            height = prevWidth;
    }
    
    /**
     * Returns the exact copy of the Piece.
     */
    @Override
    public Piece clone() {
            Piece copy = new Piece(type);
            copy.width = width;
            copy.height = height;
            copy.blocks = blocks.clone();
            return copy;
    }
    
    @Override
    public boolean equals(Object obj) {
            boolean result = true;
            if (obj == null || !obj.getClass().equals(this.getClass())) {
                    return false;
            }
            if (obj == this) {
                    return true;
            } else {
                    Piece other = (Piece) obj;
                    if (width != other.width)
                            result = false;
                    if (height != other.height)
                            result = false;
                    if (numBlocks != other.numBlocks)
                            result = false;
                    if (repchar != other.repchar)
                            result = false;
                    if (type != other.type)
                            result = false;
                    if (!Arrays.deepEquals(blocks, other.blocks))
                            result = false;
            }
            return result;
    }
    
    @Override
    public int hashCode() {
            return toString().hashCode();
    }
    
    @Override
    public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int row = 0; row < height; row++) {
                    for (int col = 0; col < width; col++) {
                            if (blocks[row][col] != 0)
                            {
                                    if (testing)
                                    {
                                            sb.append(blocks[row][col]);
                                    }
                                    else
                                    {
                                            sb.append(repchar);
                                    }
                            }
                            else
                                    sb.append(' ');
                    }
                    sb.append('\n');
            }
            return sb.toString();
    }

}
