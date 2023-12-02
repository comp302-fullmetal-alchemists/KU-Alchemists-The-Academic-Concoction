package system.domain;


import java.util.Map;
public class ArtifactCard extends Cards{

    //ArtifactCard	card name : String
    //card effect: String	chooseEffectUsage()

    private String name;
    private String effect;

    public ArtifactCard(String name, String effect) {
        super(name);
        this.name = name;
        this.effect = effect;
    }
    public String chooseEffectUsage() {
        return this.effect;
    }

    public String getName() {
        return name;
    }
}
