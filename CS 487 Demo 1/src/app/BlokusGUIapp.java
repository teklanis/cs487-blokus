package app;

import gui.TestPlayfield;

import java.util.Observable;
import java.util.Observer;

//import data.AILikesMiddle;
import data.AIRandom;
//import data.AImidBlocksCorners;
import data.Board;
import data.Bulletin;
import data.ComputerPlayer;
import data.Game;
import data.Player;
import data.Game.MessageType;

public class BlokusGUIapp implements Observer {

        /** Stores reference to Game */
        private Game game;
       
        private TestPlayfield playfield;
       
        private void printSeparatorLine() {
                for (int i=0; i < Board.X_DIMENSION; i++) {
                        System.out.print(" -");
                }
                System.out.println();
        }

        private void printBoardLine(Board board, int row) {
                for (int i=0; i < Board.X_DIMENSION; i++) {
                       
                        System.out.print("|" + board.getBlock(row, i));
                }
                System.out.println("|");
        }
       
        private void printBoard(Board board) {
                printSeparatorLine();
                for (int row=0; row < Board.Y_DIMENSION; row++) {
                        printBoardLine(board, row);
                }
                printSeparatorLine();
        }
       
        /**
         * Creates a new Blokus game and sets up the Game.
         */
        public BlokusGUIapp() {
               
                game = new Game();
               
                // add self as observers
                Bulletin.getBoard().addObserver(this);
                game.addObserver(this);
                playfield = new TestPlayfield(game);
        }

        /**
         * Starts game by showing UI.
         */
        public void start() {
                game.reset();

                // HACK: add some players
                Player player1 = new ComputerPlayer(1, new AIRandom());
                ( player1).setAutoProgress(false);
                game.addPlayer(player1);
               
                Player player2 = new ComputerPlayer(2, new AIRandom());
                ( player2).setAutoProgress(false);
                game.addPlayer(player2);
               
                Player player3 = new ComputerPlayer(3, new AIRandom());
                ( player3).setAutoProgress(false);
                game.addPlayer(player3);
               
                Player player4 = new ComputerPlayer(4, new AIRandom());
                ( player4).setAutoProgress(false);
                game.addPlayer(player4);

               
                game.start();
             
        }

        //@Override
        public synchronized void update(Observable obj, Object arg) {
                if (obj instanceof Bulletin) {
                        Bulletin b = (Bulletin) obj;
                        System.out.println(b.getLastMsg());
                        
                }
                if (obj instanceof Game)
                {
                        playfield.repaint();
                        printBoard(game.getBoard());
                        if(game.isRunning() == false)
                        {
                        	System.out.println("Game ended");
                        	playfield.closeGame();
                        }
                }
        }
       
        /**
         * Program execution entry point.
         *
         * @param args command line arguments (unused)
         */
        public static void main(String[] args) {
                BlokusGUIapp app = new BlokusGUIapp();
                app.start();
        }

}

