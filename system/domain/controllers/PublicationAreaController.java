package system.domain.controllers;

import java.util.List;

import system.domain.Alchemy;
import system.domain.Theory;

public class PublicationAreaController {

    // theory board
    // alchemy markers for each alchemy formula, players will use them to publish or debunk theory
    
    private List<Alchemy> alchemyMarkers;
    private List<Theory> theories;

    public PublicationAreaController() {
        
    }

    public PublicationAreaController(List<Alchemy> alchemyMarkers, List<Theory> theories) {
        this.alchemyMarkers = alchemyMarkers;
        this.theories = theories;
    }



         
}
