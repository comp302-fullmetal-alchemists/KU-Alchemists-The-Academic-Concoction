package system.domain.controllers;

import java.util.List;

import system.domain.Alchemy;
import system.domain.Alchemy.AlchemicalConstants;
import system.domain.GameAction;
import system.domain.Theory;
import system.domain.interfaces.Observer;
import system.domain.util.IngredientFactory;

public class PublicationAreaController {

    // theory board
    // alchemy markers for each alchemy formula, players will use them to publish or debunk theory
    
    private GameLogController gameLog;
    private Alchemy alchemy;
    private int alchemyIndex = 0;
    private Observer publicationAreaUI;

    public PublicationAreaController() {
        this.gameLog = GameBoardController.getInstance().getGameLog();

    }

    public PublicationAreaController(List<Alchemy> alchemyMarkers, List<Theory> theories) {
        this.gameLog = GameBoardController.getInstance().getGameLog();
    }

    public void setObserver(Observer observer) {
        this.publicationAreaUI = observer;
    }

    public void setAlchemy(int index) {
        alchemyIndex = index;
    }

    public int getAlchemyIndex() {
        return alchemyIndex;
    }

    public boolean checkAlchemyChoice() {
        if (alchemyIndex == 0) {
            publicationAreaUI.update("NO_ALCHEMY_CHOSEN");
            return false;
        }
        return true;
    }

    public void publishTheory() {
        if (alchemyIndex == 0) {
            publicationAreaUI.update("NO_ALCHEMY_CHOSEN");
        }
        else {
            // to be changed
            alchemy = new Alchemy(-AlchemicalConstants.SMALL, AlchemicalConstants.SMALL, -AlchemicalConstants.LARGE);
            // 
            GameBoardController.getInstance().getTheoryController().publishTheory(alchemy);
        }
    }



         
}