package system.domain.controllers;

import java.util.List;

import system.domain.AlchemyMarker;
import system.domain.Theory;

public class PublicationAreaController {

    // theory board
    // alchemy markers for each alchemy formula, players will use them to publish or debunk theory
    
    private List<AlchemyMarker> alchemyMarkers;
    private List<Theory> theories;

    public PublicationAreaController() {
        
    }

    public PublicationAreaController(List<AlchemyMarker> alchemyMarkers, List<Theory> theories) {
        this.alchemyMarkers = alchemyMarkers;
        this.theories = theories;
    }



         
}
