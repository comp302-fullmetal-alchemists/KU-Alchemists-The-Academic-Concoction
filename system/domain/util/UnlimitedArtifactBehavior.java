package system.domain.util;

import system.domain.interfaces.IUsingBehavior;
import system.domain.ArtifactCard;

public class UnlimitedArtifactBehavior implements IUsingBehavior{
    @Override
    public void useArtifact(ArtifactCard ac) {
       if (ac.getCardName().equals("Wisdom Idol")) {

       }
       else if (ac.getCardName().equals("Discount Card")) {

       }
    }
}
