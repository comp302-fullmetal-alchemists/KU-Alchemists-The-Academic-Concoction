package system.domain;

import java.util.List;

public class PublicationArea {

    // theory board
    // alchemy markers for each alchemy formula, players will use them to publish or debunk theory
    
    private List<AlchemyMarker> alchemyMarkers;
    private List<Theory> theories;

    public PublicationArea(List<AlchemyMarker> alchemyMarkers, List<Theory> theories) {
        this.alchemyMarkers = alchemyMarkers;
        this.theories = theories;
    }



         
}
