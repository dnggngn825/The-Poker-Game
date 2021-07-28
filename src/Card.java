/** Description of Card.class
 * Variable:    name, suit, rank
 * Constant:    MIN_NUM_RANK, MAX_NUM_RANK: min and max digit rank
 * Method:      validRank: check if card has valid rank
 *              validSuit: check if card has valid suit
 *              getRankChar: return the string description of rank
 *              getRank: return the integer of a rank for comparator method
 *              getName: return the name of the card as a String
 *              getSuit: return the integer value of suit for rank sorting
 *              validCard: check if the card is valid
 */

public class Card {

    /**
     * Define constant for digit rank, minumum value and maximum value
     */
    private static final int MIN_NUM_RANK = 2;
    private static final int MAX_NUM_RANK = 9;

    /**
     * A Card will have a variable: name, rank and suit
     * name: a string
     * rank: a char, extracted from name, index 0
     * suit: a char, extracted from name, index 1
     */
    private String name;
    private char rank;
    private char suit;

    public Card(String card){
        card = card.toUpperCase();
        this.name = card;
        this.rank = card.charAt(0);
        this.suit = card.charAt(1);
    }

    /**
         * Check if a card has a valid rank, whether it is 2 to 9 or,
         *      from Ten to Ace
         * for 2 --> 9:         check if the char isDigit, convert to int
         *                  to check if it is range (2,9)
         * for Ten --> Ace:    check char if char exist in the enum-type Rank
         *          throws exception if it does not exist and return invalid rank
         * @return true if it satisfies either condition, else return false
     */
    public boolean validRank(){
        if (Character.isDigit(rank)){
            int numericValueRank = Character.getNumericValue(this.rank);
            return (numericValueRank >= MIN_NUM_RANK && numericValueRank <= MAX_NUM_RANK);
        } else{
            try {
                Rank check = Rank.valueOf(Character.toString(this.rank));
                return true;
            } catch (IllegalArgumentException e) {
                return false;
            }
        }
    }

    /* Return rank of card as a String
        for description
    */
    public String getRankChar(){
        if (validRank() && Character.isDigit(rank)){
                return Character.toString(rank);
        }
        Rank check = Rank.valueOf(Character.toString(rank));
        return check.getRank();
    }

    
    /**
        * if a rank is digit then convert to int
        * else throws exception if it is not in the enum type Rank,
        * return value of rank if it is in the enum type Rank
        * @return the rank of a card by integer from 2 to 14 (Ace)
    */
    public int getRank(){
        if (validRank() && Character.isDigit(rank)){
            return Character.getNumericValue(rank);
        }
        Rank check = Rank.valueOf(Character.toString(rank));
        return check.getValue();
    }

    /**
        * return name of the card as a string
     */
    public String getName(){
        return this.name;
    }

    /* Check if the card has a valid Suit,
        which is constant in enum type of Suit
    */
    public boolean validSuit(){
        try {
            Suit check = Suit.valueOf(Character.toString(this.suit));
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Use enum Suit to store the value of suits
     * @return integer value of the related suit.
     */
    public int getSuit(){
        Suit check = Suit.valueOf(Character.toString(this.suit));
        return check.getSuit();
    }

    /**
     * Check if card is valid, by checking if
     * the card has valid rank and valid suit.
     */
    public boolean validCard(){
        return (validSuit() && validRank());
    }
}