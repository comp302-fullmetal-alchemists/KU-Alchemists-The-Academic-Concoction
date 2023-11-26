package system.domain;

public abstract class Cards{

    //<<abstract class>>Cards	cardnamecardeffect

    private String cardName;

    public Cards(String cardName) {
        this.cardName = cardName;
    }

    public String getCardName() {
        return cardName;
    }
    
}
