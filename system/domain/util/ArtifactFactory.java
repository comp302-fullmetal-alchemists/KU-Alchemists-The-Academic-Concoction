package system.domain.util;

import java.util.ArrayList;
import java.util.List;

import system.domain.ArtifactCard;
import system.domain.controllers.GameBoardController;

public class ArtifactFactory {

    private String[] artifacts = {"Philosopher's Compass", "Elixir of Insight", "Discount Card", "Amulet of Rhetoric"};
    private String[] effects = {"Once per round, the player can swap the position of two alchemy markers on the Deduction Board.","Allows a player to view the top three cards of the ingredient deck and rearrange them in any order.", "Your next artifact costs 2 gold less. After that, artifacts cost you 1 gold less.", "Gain 5 points of reputation." };
    private String[] usages = {null,null,"Immediate effect." };
    
    public ArtifactFactory() {}
    
    public List<ArtifactCard> createArtifacts() {
        List<ArtifactCard> artifactPile = new ArrayList<ArtifactCard>();
        for (int i = 0; i < artifacts.length - 1; i++) {
            artifactPile.add(new ArtifactCard(artifacts[i], effects[i], usages[i]));
        }
        return artifactPile;
    }
}
