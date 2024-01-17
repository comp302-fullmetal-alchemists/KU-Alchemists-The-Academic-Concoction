package system.domain;

import system.domain.interfaces.IUsingBehavior;
import system.domain.util.LimitedArtifactBehavior;
import system.domain.util.UnlimitedArtifactBehavior;

public class ArtifactCard extends Cards{

    //ArtifactCard	card name : String
    //card effect: String	chooseEffectUsage()

    private String name;
    private String effect;
    private String usage;
    private IUsingBehavior usingBehavior;

    public ArtifactCard(String name, String effect, String usage) {
        super(name);
        this.name = name;
        this.effect = effect;
        if (usage.equals("immediate")) {
            usingBehavior = new LimitedArtifactBehavior();
        }
        else {
            usingBehavior = new UnlimitedArtifactBehavior();
        }
    
    }

    public String getName() {
        return name;
    }

    public String getEffect() {
        return effect;
    }

    public String getUsage() {
        return usage;
    }

    public IUsingBehavior getUsingBehavior() {
        return this.usingBehavior;
    }

    public void performUseArtifact() {
        usingBehavior.useArtifact(this);
        
    }
}
