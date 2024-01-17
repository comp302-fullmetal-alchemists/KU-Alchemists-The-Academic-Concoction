package system.domain.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import system.domain.ArtifactCard;


public class ArtifactFactory {

    private String[] artifacts = {"Magic Mortar", "Elixir of Insight", "Discount Card", "Printing Press", "Wisdom Idol"};
    private String[] effects = {"With Magic Mortar, you can keep one of the ingredients that you have already used so that you can use it later to make another potion!", 
    "Elixir of Insight Allows you to view the top three cards of the ingredient deck and rearrange them in any order.", 
    "With Discount Card your next artifact costs 2 gold less. After that, artifacts cost you 1 gold less.", 
    "This artifact allows the player to publish a theory free of charge. So, the player does not need to pay 1 gold to the bank.",
    "Wisdom Idol help you to keep/increase your reputation points. You may choose to use this artifact card whenever another player wants to debunk your theory. Using this artifact,  you do not not lose any reputation points even if your theory has been proven to be wrong. If you choose to keep this artifact until the end of the game, you gain an additional 1 reputation point."
};
    private String[] usages = {"permanent","immediate", "permanent", "immediate", "permanent" };
    
    public ArtifactFactory() {}
    
    public List<ArtifactCard> createArtifacts() {
        List<ArtifactCard> artifactPile = new ArrayList<ArtifactCard>();
        int j= 0;
        for (int i = 0; i < artifacts.length - 1; i++) {
            j = i % 5;
            artifactPile.add(new ArtifactCard(artifacts[j], effects[j], usages[j]));
        }

        Collections.shuffle(artifactPile);
        return artifactPile;
    }
}

