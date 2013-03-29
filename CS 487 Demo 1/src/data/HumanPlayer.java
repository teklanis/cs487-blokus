package data;


public class HumanPlayer extends Player {
        
        /** Name of the Human player */
        private static final String NAME = "User";
        
        /**
         * Creates an instance of Human player.
         */
        public HumanPlayer(int index) {
                super(NAME, index);
                
                hand = new Hand();
        }

}
