package system.domain.controllers;

import java.util.List;
import system.domain.Alchemy;
import system.domain.Theory;
import system.domain.interfaces.Observer;

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
            alchemy = Alchemy.getAlchemy(alchemyIndex - 1);
            // 
            GameBoardController.getInstance().getTheoryController().publishTheory(alchemy);
        }
    }

    public void debunkTheory() {
        if (alchemyIndex == 0) {
            publicationAreaUI.update("NO_ALCHEMY_CHOSEN");
        }
        else {
            // to be changed
            alchemy = Alchemy.getAlchemy(alchemyIndex - 1);
            // 
            GameBoardController.getInstance().getTheoryController().debunkTheory(alchemy);
        }
    }

    public void serverUpdateUIForPublishedd(Theory theory) {
        String alchemyName = String.format("Alchemy %d", theory.getAlchemy().findAlchemyIndex(theory.getAlchemy().toString()));
        publicationAreaUI.update("Server updated:publish," + alchemyName +","+ theory.getIngredient() +","+ theory.getOwner());
    }

    public void serverUpdateUIForEndorsedd(Theory theory) {
        String alchemyName = String.format("Alchemy %d", theory.getAlchemy().findAlchemyIndex(theory.getAlchemy().toString()));
        publicationAreaUI.update("Server updated:endorse," + alchemyName +","+ theory.getIngredient() +","+ theory.getOwner() +","+ theory.getEndorser());
    }

    public void serverUpdateUIForDebunkedd(Theory theory) {
        String alchemyName = String.format("Alchemy %d", theory.getAlchemy().findAlchemyIndex(theory.getAlchemy().toString()));
        publicationAreaUI.update("Server updated:debunk," + alchemyName +","+ theory.getIngredient() +","+ theory.getOwner());
    }
         
}