
import java.util.Arrays;
import java.util.Comparator;


/** Description of Hand.class
 * Class:       Hand
 * Variable:    cards: to store array of Card
 * Purpose:     To store array of Cards to apply methods
 * Methods:     Part 1: Process the cards array of the hand
 *               -- check and invalid card in the hand
 *               -- get card with highest rank in the cards array
 *               -- check hand if it is a multiple of five
 *               -- check if the rank in cards array form a sequence of 5
 *               -- sort all cards by rank and suit
 *               -- check if a cards array has all same suit or same rank
 *              Part 2: Check hand of the cards player has,
 *                  depends on which hand will return boolean value or String
 *               -- isStraightFlush: check for straight flush
 *               -- nOfAKind: check for four and three of a kind
 *               -- fullHouse: check for Full House case
 *               -- isFlush: check for flush
 *               -- isStraight: check for straight case
 *               -- oneTwoPairs: check for two or one pair
 * 
 */


public class Hand {

    /** Variable description
     * DEFAULT: constant of minimum number of cards in hand
     * cards: Array of cards in hand of player
     */
    private static final int DEFAULT = 5;
    private Card[] cards;

    public Hand(String[] input){
        cards = new Card[input.length];
        for (int i =0; i< input.length;i++){
            this.cards[i] = new Card(input[i]);
        }
    }

    /**
     * 
     * @return Cards array of the hand
     */
    public Card[] getCards(){
        return this.cards;
    }

    //============ Part 1: Process the cards array of the hand ==========
    /**
     * Loop through the cards array and find the invalid card
     * @return the invalid card as a String, else empty string
     */
    public String returnInvalidCard(){
        for (Card card: cards){
            if (!card.validCard()){
                return card.getName();
            }
        }
        return "";
    }

    /**
     * Check if there is invalid card in hand
     * @return true if there is no invalid card returned, means
     *      that there is empty String returned.
     */
    public boolean isValid(){
        return returnInvalidCard().isEmpty();
    }

    /*
     * Check if the number of elements is a multiple of 5
     * @return true if length is a multiple of 5 and vice versa
     */
    public boolean multipleFive(){
        return ((this.cards.length % DEFAULT == 0) && (this.cards.length >0));
    }

    /*
         * 1. Check if length of input is the multiple of 5
         * 2. Check if there is an invalid card in the hand
         * then @return that invalid card
         */
    public void executeInvalidCard(){
        /* Check for invalid card input */
        if (!isValid()){
            System.out.printf("Error: invalid card name \'%s\'\n", returnInvalidCard());
            System.exit(0);
        }

        /* Check if number of cards > 0 and a multiple of 5 */
        if (!multipleFive()){
            System.out.println("Error: wrong number of arguments; must be a multiple of 5");
            System.exit(0);
        }
    }
    
    public int getHighestRankValue(){
        sortCardsByRank();
        return this.cards[cards.length-1].getRank();
    }

    /**
     * Sort the array by rank and return the rank of
     * the last element in the sorted array
     */
    public String getHighestRank(){
        sortCardsByRank();
        return this.cards[cards.length-1].getRankChar();
    }

    /**
     * Check if all cards' rank form a sequence of 5
     * Start with index 1 and 3 and check the former and latter elements
     * of that element if there difference is 1
     * @return
     */
    private boolean sequenceOfRank(){
        int step = 1;
        for (int i = 1; i< this.cards.length-1; i = i+2){
            if ((this.cards[i].getRank() == this.cards[i-step].getRank() + step)
            && (this.cards[i].getRank() == this.cards[i+step].getRank() - step)){
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    /* Sort array of Card by their suit */
    private Card[] sortCardsBySuit(){
        Comparator<Card> rankComparator = new Comparator<Card>() {
			public int compare(Card c1, Card c2) {
                int v1 = c1.getSuit();
                int v2 = c2.getSuit();
                if (v1 > v2){
                    return 1;
                }else if (v1 < v2){
                    return -1;
                }
                return 0;
			}
		};
        Arrays.sort(this.cards, rankComparator);
        return this.cards;
    }

    /* Sort array of Cards by their rank */
    public Card[] sortCardsByRank(){
        Comparator<Card> rankComparator = new Comparator<Card>() {
			public int compare(Card c1, Card c2) {
                int v1 = c1.getRank();
                int v2 = c2.getRank();
                if (v1 > v2){
                    return 1;
                }else if (v1 < v2){
                    return -1;
                }
                return 0;
			}
		};
        Arrays.sort(this.cards, rankComparator);
        return this.cards;
    }

    /**
     * Check if cards array has same suit for all cards
     * sort the array by suit first then check first element's suit
     * and last element's suit
     * @return true if that array has same suit for all cards.
     */
    private boolean sameSuit(){
        sortCardsBySuit();
        return (this.cards[0].getSuit() 
        == this.cards[this.cards.length-1].getSuit());
    }

    /**
     * Check if cards array has same rank for all cards
     * sort the array by rank first then check first element's rank
     * and last element's rank
     * @return true if that array has same rank for all cards.
     */
    private boolean sameRank(Card[] cards){
        sortCardsByRank();
        return (cards[0].getRank() == cards[cards.length-1].getRank());
    }

    /* Return sub array of Card, take start index and number of elements in sub-array */
    protected static Card[] subArray(Card[] array, int start, int numElement){
        return Arrays.copyOfRange(array,start,start+numElement);
    }

    //============== Part 2: Check hand of the cards player has ============

    /**
     * Firstly, sort the rank of the hand of cards
     * Secondly, check if suits are same for all cards
     *          and also if ranks form a sequence of 5
     * @return true if both conditions satisfy
     */
    protected boolean straightFlush(){
        sortCardsByRank();
        return (sameSuit() && sequenceOfRank());
    }

    /**
     * Firstly, sort hand by rank, N is the number of elements,
     *          that we want to check if they are same rank
     * Secondly, for i from 0 to (cards.length-N), check rank of 
     *          the subarray start from i with N elements
     *          since we have already sort by rank so cards with same
     *          rank will be grouped together in the array
     * @param N
     * @return the index i in the array to output the rank when
     *          we execute the desciption for the player's hand
     *          else return 5 (exceed the array index)
     */
    protected int nOfAKind(int N){
        sortCardsByRank();
        for (int i = 0; i<= cards.length-N; i++){
            if (sameRank(subArray(cards,i,N))){
                if (N == 4){
                    return i;
                } else if (N == 3){
                    return i;
                }
            }  
        }
        return DEFAULT;
    }

    /**
     * Firstly, sort the array by rank to group all elements with same rank together
     * Secondly, check rank of the first 3 and next 2 elements,
     *          also check rank of the first 2 and next 3 elements in the array
     * @param ID
     * @return the description of the player associated with the playerID
     *          else return empty string, which will be checked in Player.class
     */
    protected String fullHouse(int ID){
        int triple = 3;
        int pair = 2;
        sortCardsByRank();
        if (sameRank(subArray(cards,0,triple)) && sameRank(subArray(cards,triple,pair))){
            return "Player "+ ID + ": " + cards[0].getRankChar() 
                    + "s full of " + cards[triple].getRankChar() + "s";
        } else if (sameRank(subArray(cards,0,pair)) && sameRank(subArray(cards,pair,triple))){
            return "Player "+ ID + ": " + cards[pair].getRankChar() 
                    + "s full of " + cards[0].getRankChar() + "s";
        }
        return "";
    }

    /**
     * Check if the array of cards has the same suit for all cards,
     * by using the function sameSuit()
     * @return
     */
    protected boolean isFlush(){

        return sameSuit();
    }

    /**
     * Check if all cards form a sequence of 5 and
     * they must not have same suit (Flush)
     * @return boolean value for later description output of the player's hand
     */
    protected boolean isStraight(){
        sortCardsByRank();
        return (sequenceOfRank() && !sameSuit());
    }

    /**
     * After sorting the array of cards by rank, we form different case
     * that 2 pairs can be located in the array
     * first, check for the first pair location, then check for the second pair
     * location, if we cant find the second pair then we return One Pair case
     * Use sameRank() function to check same rank of the subarray with 2 elements
     * in the array, starting with the index indicating in the comment section
     * before each case.
     */
    protected String oneTwoPairs(int ID){
        sortCardsByRank();
        int pair = 2;

        /** Variable description
         * outputTwoPairs return the description when player has 2 pairs
         * outputAPair return the description when player has only 1 pair
         */
        String outputTwoPairs = "Player "+ ID + ": "+ "%s" 
                                + "s over " + "%s" + "s";
        String outputAPair = "Player "+ ID + ": "
                            + "Pair of " + "%s"+ "s";
        if (sameRank(subArray(cards,0,pair))){

                // Case 1: [p1 p1 x p2 p2] and Case 2: [p1 p1 p2 p2 x]
            if (sameRank(subArray(cards,pair+1,pair)) 
                || sameRank(subArray(cards,pair,pair))){
                return String.format(outputTwoPairs,
                                    cards[pair+1].getRankChar(),
                                    cards[0].getRankChar());
            }

                // only have one pair [p1 p1 x x x]
            return String.format(outputAPair,
                                cards[0].getRankChar());
        
        } else if (sameRank(subArray(cards,1,pair))){

                // Case 3: [x p1 p1 p2 p2]
            if (sameRank(subArray(cards,pair+1,pair))){
                return String.format(outputTwoPairs,
                                    cards[pair+1].getRankChar(),
                                    cards[1].getRankChar());
            }
                // only have one pair [x p1 p1 x x]
            return String.format(outputAPair,
                                cards[1].getRankChar());

        } else if (sameRank(subArray(cards,pair,pair)) 
                    || sameRank(subArray(cards,pair+1,pair))){

                // only have one pair [x x p1 p1 x] or [x x x p1 p1]
            return String.format(outputAPair,
                                cards[pair+1].getRankChar());
        }
            
        return "";
    }
}