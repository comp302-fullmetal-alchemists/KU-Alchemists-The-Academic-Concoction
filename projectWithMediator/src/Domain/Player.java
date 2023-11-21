package Domain;

public class Player {
    
    private String name;
    private Boolean turn;
    private String prop1;
    private String prop2;


    public Player(String name, String prop1, String prop2) {
        this.name = name;
        this.turn = false;
        this.prop1 = prop1;
        this.prop2 = prop2;

    }

    public String getName(){
        return name;
    }

    public Boolean isInTurn() {
        return turn;
    }

    public void changeTurn() {
        turn = !turn;
    }


    public String getProp1() {
        return prop1;
    }

    public String getProp2(){
        return prop2;       
    }

}
