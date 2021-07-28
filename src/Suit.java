

public enum Suit{
    S(0),D(1),C(2),H(3);

    private int suit;
    private Suit(int suit){
        this.suit = suit;
    }
    public int getSuit(){
        return this.suit;
    }
}