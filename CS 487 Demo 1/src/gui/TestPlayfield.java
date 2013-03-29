package gui;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.geom.Rectangle2D;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import data.Board;
import data.Game;
import data.Move;

public class TestPlayfield extends JPanel
{

        /**
         * The height of the board.
         */
        private int boardHeight = 600;
        
        /**
         * The width of the board.
         */
        private int boardWidth = 601;
        
        private Game theGame;
        
        private JFrame blokusFrame;
        
        private JButton moveButton;
        private boolean endGame;
        
        public TestPlayfield (Game theGame)
        {
                setPreferredSize(new Dimension(boardWidth, boardHeight));
                blokusFrame = new JFrame("Blokus");
                blokusFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                blokusFrame.add(this, BorderLayout.CENTER);
                
                blokusFrame.add(new JButton(new moveAction()), BorderLayout.SOUTH);
       
                
                blokusFrame.validate();
                blokusFrame.pack();
                blokusFrame.setVisible(true);
        
                this.theGame = theGame;
                endGame= false;
        }
        
        public void paintComponent(Graphics g)
        {
                super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            
            boardHeight = this.getHeight();
            boardWidth = this.getWidth();
            int hieghtFactor = boardHeight/20;
            int widthFactor = boardWidth/20;
            
            //System.out.println("game " + theGame);
            Board currentBoard = theGame.getBoard();
                
            for (int i= 0; i < 20; i++)
            {
                for (int j = 0; j < 20; j++ )
                {

                        g2d.setPaint(getPlayerColor(currentBoard.getBlock(i, j)));
                                
                        Rectangle2D.Double block = new Rectangle2D.Double( 
                                                j*widthFactor, (i)*hieghtFactor, widthFactor, hieghtFactor);
                        g2d.fill(block);
                        g2d.setPaint(Color.BLACK);
                                
                        g2d.setStroke(new BasicStroke(1));
                        g2d.draw(block);
                        
                }
            }
         }
  
		public  void closeGame()
        {
        	blokusFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	JOptionPane.showMessageDialog(blokusFrame,
            "Game is over.");
        	endGame = true;
        	
        
        	
        }
        
        private Color getPlayerColor(int player)
        {
                Color color;
                
                switch(player)
                {
                        case 1:
                                color = Color.BLUE;
                                break;
                        
                        case 2:
                                color = Color.YELLOW;
                                break;
                                
                        case 3:
                                color = Color.RED;
                                break;
                                
                        case 4:
                                color = Color.GREEN;
                                break;
                        
                        default:
                                color = Color.GRAY;
                }
                
                return color;
        }
                
        class moveAction extends AbstractAction
        {
                /**
                 * the name of the action
                 */
                private static final String NAME = "Next Move";
                
                /**
                 * Constructor.
                 */
                public moveAction()
                {
                        super(NAME);
                        this.putValue(MNEMONIC_KEY, 76);
                }
                        
                /**
                 * When the action happens,trigger the next AI to move.
                 * 
                 * @param action the event
                 */
                public void actionPerformed(ActionEvent action)
                {
                	if(endGame ==true)
                    	setEnabled(false);
                    Move move = theGame.getCurrentPlayer().getNextMove(theGame.getBoard());
                    theGame.getCurrentPlayer().putNextMove(move);
                        
                        
                }
               
        }
        

}
