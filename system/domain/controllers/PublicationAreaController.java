package system.domain.controllers;

import java.util.List;

import system.domain.Alchemy;
import system.domain.GameAction;
import system.domain.Theory;

public class PublicationAreaController {

    // theory board
    // alchemy markers for each alchemy formula, players will use them to publish or debunk theory
    
    private GameLogController gameLog;
    private List<Alchemy> alchemyMarkers;
    private List<Theory> theories;

    public PublicationAreaController() {
        this.gameLog = GameBoardController.getInstance().getGameLog();

    }

    public PublicationAreaController(List<Alchemy> alchemyMarkers, List<Theory> theories) {
        this.gameLog = GameBoardController.getInstance().getGameLog();
        this.alchemyMarkers = alchemyMarkers;
        this.theories = theories;
    }



         
}