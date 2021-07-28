/** Desciption of Player.class
 * This class represents player
 * Variable:    hand: store the array of cards the player has
 *              playerID: ID of the player
 * Method:      getID: return the ID of the player
 *              getHand: return the hand of player
 *              executeHandPlayer: return description of player's hand
 */

 
public class Player{

    /** Variable Description
     * hand: player's hand, containing the cards array of player
     * playerID: ID of the player in case of multiple players involved
     */
    private Hand hand;
    private int playerID;

    /* Define integer constant for minimum number of cards player has */
    private static final int DEFAULT = 5;

    /* Constructor of Player */
    public Player(String[] cards, int ID){
        this.hand = new Hand(cards);
        this.playerID = ID;
    }

    /**
     * 
     * @return playerID
     */
    public int getID(){
        return playerID;
    }
    /**
     * 
     * @return hand of player
     */
    public Hand getHand(){
        return this.hand;
    }

    /* 
        This function is used to execute the hand of each player
        Variables: Player.hand and playerID
        Method: Use (If else-if) clause from the top priority to the least priority,
                making sure that the higher priority will be executed when overlapped
                with others
            1st Straight flush
            2nd Four of A kind
            3rd Full House
            4th Flush
            5th Straight
            6th Three of A Kind
            7th Two pairs
            8th One Pair
            9th High Card
    */
    public void executeHandPlayer(){

        if (hand.straightFlush()) {
            // Straight Flush
            System.out.println("Player "+ playerID + ": "
                        + this.hand.getHighestRank()+ "-high straight flush");
        }else if (hand.nOfAKind(4) != DEFAULT){
            // Four of a kind
            System.out.println("Player "+ playerID + ": Four "
                        + this.hand.getCards()[hand.nOfAKind(4)].getRankChar()+ "s");
        }else if (!hand.fullHouse(playerID).isEmpty()){
            // Full House
            System.out.println(hand.fullHouse(playerID));
        }else if (hand.isFlush()){
            // Flush
            System.out.println("Player "+ playerID + ": "
                        + this.hand.getHighestRank()+ "-high flush");
        }else if (hand.isStraight()){
            // Straight
            System.out.println("Player "+ playerID + ": "
                        + this.hand.getHighestRank()+ "-high straight");
        }else if (hand.nOfAKind(3) != DEFAULT){
            // Three of a kind
            System.out.println("Player "+ playerID + ": Three "
                        + this.hand.getCards()[hand.nOfAKind(3)].getRankChar()+ "s");
        }else if (!hand.oneTwoPairs(playerID).isEmpty()){
            // Two pairs and One pair
            System.out.println(hand.oneTwoPairs(playerID));
        }else{
            // High Card
            System.out.println("Player "+ playerID + ": "
                        + this.hand.getHighestRank()+ "-high");
        }

    }

}