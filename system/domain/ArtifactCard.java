package system.domain;


import java.util.Map;
public class ArtifactCard extends Cards{

    //ArtifactCard	card name : String
    //card effect: String	chooseEffectUsage()

    private String name;
    private String effect;
    private String usage;

    public ArtifactCard(String name, String effect, String usage) {
        super(name);
        this.name = name;
        this.effect = effect;
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
}
