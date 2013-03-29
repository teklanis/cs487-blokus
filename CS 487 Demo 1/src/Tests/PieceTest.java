package Tests;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import data.Piece;


public class PieceTest {

        @Test
        public void testConstructor() {
                Piece.Type[] types = Piece.Type.values();
                for (int i = 0; i < types.length; i++) {
                        Piece piece = new Piece(types[i]);
                        System.out.println(types[i].name());
                        System.out.println(piece.toString());
                }
        }
       
        @Test
        public void testFlipAboutX() {
                Piece.Type[] types = Piece.Type.values();
                for (int i = 0; i < types.length; i++) {
                       
                        // create a new Piece
                        Piece piece = new Piece(types[i]);
                        System.out.println(types[i].name());
                        System.out.println("Beore:");
                        System.out.println(piece.toString());
                        int prevWidth = piece.getWidth();
                        int prevHeight = piece.getHeight();
                        int prevNumBlocks = piece.getNumBlocks();
                        Piece.Type prevType = piece.getType();
                        int[][] prevBlocks = piece.getBlocks();
                        Piece prevPiece = piece.clone();
                       
                        // flip it about x-axis
                        piece.flip(Piece.Axis.X);
                        System.out.println("Ater flipping about x-axis:");
                        System.out.println(piece.toString());
                       
                        // verify properties
                        assertEquals(prevWidth, piece.getWidth());
                        assertEquals(prevHeight, piece.getHeight());
                        assertEquals(prevNumBlocks, piece.getNumBlocks());
                        assertEquals(prevType, piece.getType());
                        if (piece.isFlipInvariant()) {
                                assertArrayEquals(prevBlocks, piece.getBlocks());
                        }

                        // flip it about x-axis, again
                        piece.flip(Piece.Axis.X);
                       
                        // verify properties
                        assertTrue(piece.equals(prevPiece));
                }
        }

        @Test
        public void testFlipAboutY() {
                Piece.Type[] types = Piece.Type.values();
                for (int i = 0; i < types.length; i++) {
                       
                        // create a new Piece
                        Piece piece = new Piece(types[i]);
                        System.out.println(types[i].name());
                        System.out.println("Beore:");
                        System.out.println(piece.toString());
                        int prevWidth = piece.getWidth();
                        int prevHeight = piece.getHeight();
                        int prevNumBlocks = piece.getNumBlocks();
                        Piece.Type prevType = piece.getType();
                        int[][] prevBlocks = piece.getBlocks();
                        Piece prevPiece = piece.clone();
                       
                        // flip it about y-axis
                        piece.flip(Piece.Axis.Y);
                        System.out.println("Ater flipping about y-axis:");
                        System.out.println(piece.toString());
                       
                        // verify properties
                        assertEquals(prevWidth, piece.getWidth());
                        assertEquals(prevHeight, piece.getHeight());
                        assertEquals(prevNumBlocks, piece.getNumBlocks());
                        assertEquals(prevType, piece.getType());
                        if (piece.isFlipInvariant()) {
                                assertArrayEquals(prevBlocks, piece.getBlocks());
                        }

                        // flip it about y-axis, again
                        piece.flip(Piece.Axis.Y);
                       
                        // verify properties
                        assertTrue(piece.equals(prevPiece));
                }
        }

        @Test
        public void testRotateClockwise() {
                Piece.Type[] types = Piece.Type.values();
                for (int i = 0; i < types.length; i++) {
                       
                        // create a new Piece
                        Piece piece = new Piece(types[i]);
                        System.out.println(types[i].name());
                        System.out.println("Beore:");
                        System.out.println(piece.toString());
                        int prevWidth = piece.getWidth();
                        int prevHeight = piece.getHeight();
                        int prevNumBlocks = piece.getNumBlocks();
                        Piece.Type prevType = piece.getType();
                        int[][] prevBlocks = piece.getBlocks();
                        Piece prevPiece = piece.clone();
                       
                        // rotate it in clockwise direction
                        piece.rotate(Piece.Direction.Clockwise);
                        System.out.println("Ater rotating in clockwise direction:");
                        System.out.println(piece.toString());
                       
                        // verify properties
                        assertEquals(prevHeight, piece.getWidth());
                        assertEquals(prevWidth, piece.getHeight());
                        assertEquals(prevNumBlocks, piece.getNumBlocks());
                        assertEquals(prevType, piece.getType());
                        if (piece.isRotateInvariant()) {
                                assertArrayEquals(prevBlocks, piece.getBlocks());
                        }

                        // rotate it three more times
                        piece.rotate(Piece.Direction.Clockwise);
                        piece.rotate(Piece.Direction.Clockwise);
                        piece.rotate(Piece.Direction.Clockwise);
                       
                        // verify properties
                        assertTrue(piece.equals(prevPiece));
                }
        }

        @Test
        public void testRotateCounterClockwise() {
                Piece.Type[] types = Piece.Type.values();
                for (int i = 0; i < types.length; i++) {
                       
                        // create a new Piece
                        Piece piece = new Piece(types[i]);
                        System.out.println(types[i].name());
                        System.out.println("Beore:");
                        System.out.println(piece.toString());
                        int prevWidth = piece.getWidth();
                        int prevHeight = piece.getHeight();
                        int prevNumBlocks = piece.getNumBlocks();
                        Piece.Type prevType = piece.getType();
                        int[][] prevBlocks = piece.getBlocks();
                        Piece prevPiece = piece.clone();
                       
                        // rotate it in counter-clockwise direction
                        piece.rotate(Piece.Direction.CounterClockwise);
                        System.out.println("Ater rotating in counter-clockwise direction:");
                        System.out.println(piece.toString());
                       
                        // verify properties
                        assertEquals(prevHeight, piece.getWidth());
                        assertEquals(prevWidth, piece.getHeight());
                        assertEquals(prevNumBlocks, piece.getNumBlocks());
                        assertEquals(prevType, piece.getType());
                        if (piece.isRotateInvariant()) {
                                assertArrayEquals(prevBlocks, piece.getBlocks());
                        }

                        // rotate it three more times
                        piece.rotate(Piece.Direction.CounterClockwise);
                        piece.rotate(Piece.Direction.CounterClockwise);
                        piece.rotate(Piece.Direction.CounterClockwise);
                       
                        // verify properties
                        assertTrue(piece.equals(prevPiece));
                }
        }
       
        @Test
        public void testRotateAndReverse() {
                Piece.Type[] types = Piece.Type.values();
                for (int i = 0; i < types.length; i++) {
                       
                        // create a new Piece
                        Piece piece = new Piece(types[i]);
                        Piece prevPiece = piece.clone();
                       
                        // rotate it in counter-clockwise direction, then undo it
                        piece.rotate(Piece.Direction.CounterClockwise);
                        piece.rotate(Piece.Direction.Clockwise);
                       
                        // verify properties
                        assertTrue(piece.equals(prevPiece));

                        // rotate it in counter-clockwise direction
                        piece.rotate(Piece.Direction.Clockwise);
                        piece.rotate(Piece.Direction.CounterClockwise);
                       
                        // verify properties
                        assertTrue(piece.equals(prevPiece));
                }
        }

        @Test
        public void testHasCorners() {
                Piece.Type[] types = Piece.Type.values();
                for (int i = 0; i < types.length; i++) {
                       
                        // create a new Piece
                        Piece piece = new Piece(types[i]);
                        int numCorners = piece.getNumCorners();
                       
                        // find all corners
                        int found = 0;
                        for (int row=0; row < piece.getHeight(); row++) {
                                for (int col=0; col < piece.getWidth(); col++) {
                                        if (piece.hasCorner(row,col))
                                                found++;
                                }
                        }
                       
                        assertEquals("Expected=" + numCorners + ",Found=" + found, numCorners, found);
                }
        }
       
}

