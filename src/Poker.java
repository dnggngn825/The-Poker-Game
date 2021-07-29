
import java.util.Arrays;


/* Description of Poker.java
 * Variable:    players: array of Player
 *              noPlayer: number of player
 *              hand: hand of player, type Hand
 *              DEFAULT: constant for minimum number of card of a player
 * Method:      notUndertaken: check for number of input argument
 *              subArray: create a subarray by taking the start index
 *                  and the number of elements in the sub-array
 *              main: include 2 states:
 *                  1. Check for valid condition
 *                  2. Then assign cards to the player with an ID
 *                      and check for description in hand of player
 */


public class Poker{

    // Define the constant of min cards for one player
    private static final int DEFAULT = 5;

    /**
     * Local variable:
     * hand:        represents hand
     * players:     array of players in caseof multiple players
     * noPlayer:    number of player of the game
     */
    private static Hand hand;
    private static Player[] players;
    private static int noPlayer;

    public static void main(String[] args){
        /* Create hand variable from String array args */
        hand = new Hand(args);

    /* Initial state: Checking the input
         * 1. Checking if the input is larger than 5 elements
         * @return "NOT UNDERTAKEN"
         * 2. Check if the card input is a valid card from the hand
         * @return the invalid card from the hand
        */

        // Not Undertaken when input is not multiple of 5
            notUndertaken(args);

        /*
         * 1. Check if length of input is the multiple of 5
         * 2. Check if there is an invalid card in the hand
         * then @return that invalid card
         */
            hand.executeInvalidCard();

    // ====================================================

    /* Second State: Assign cards to hand and assign to new player
        /*      and execute the hand description of the player

        /*
         * After making sure that the number of player is 1
         * the input has 5 elements
         */
            noPlayer = args.length/DEFAULT;
            players = new Player[noPlayer];
            
            // index 0 and 1 for compare tier and rank, index 2 for player id
            // index 3 for draw case
            int[] maxScore = {-1,-1,0, 0};

        // Assign cards to player and execute player's hand
        for (int i = 0 ; i < noPlayer; i++ ){

        	int[] array = {0,0};
            players[i] = new Player(subArray(args,i*DEFAULT,DEFAULT),i+1,array);
            players[i].executeHandPlayer();
            
            // Compare if there are multiple players
            if (noPlayer > 1) {
            	if (players[i].getScore()[0] > maxScore[0] && players[i].getScore()[1] > maxScore[1]) {
            		maxScore[2] = i+1;
            		maxScore[0] = players[i].getScore()[0];
            		maxScore[1] = players[i].getScore()[1];
            		maxScore[3] = 0;
            	}
            	else if (players[i].getScore()[0] == maxScore[0] && players[i].getScore()[1] == maxScore[1]) {
            		maxScore[3] = 1;
            	}
            	
            }
        }
        // Check winner and draw case
        if (noPlayer > 1)
        {
        	System.out.println("----------------------");
        	if (maxScore[3] == 1) {
        		System.out.println("There is draw");
        	}
            else {
            	System.out.println("Player " + maxScore[2] +" is the winner!");
            }
        }
        
    //======================================================
}

    /* For 1 player only, if there is more input then execute this */
    private static void notUndertaken(String[] args){
        if (args.length % DEFAULT != 0){
            System.out.println("NOT UNDERTAKEN");
            System.exit(0);
        }
    }

    /* Extract an array of String by taking start index and number of elements */
    protected static String[] subArray(String[] array, 
                                        int start, 
                                        int numElement){
        return Arrays.copyOfRange(array,start,start+numElement);
    }
}