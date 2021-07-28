

public enum Rank {
    T("10",10),J("Jack",11),Q("Queen",12),K("King",13),A("Ace",14);

    private String rank;
    private int value;
    private Rank(String rank, int value){
        this.rank = rank;
        this.value = value;
    }
    public String getRank(){
        return this.rank;
    }
    public int getValue(){
        return this.value;
    }
}