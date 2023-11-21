package Domain.Controllers;



public class GameAreaController {
    
    private String choice;

    public GameAreaController() {
        
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public void printChoice(){
        System.out.println(choice);
    }
    

}
