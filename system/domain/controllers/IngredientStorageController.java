package system.domain.controllers;

import java.text.CollationElementIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import system.domain.ArtifactCard;
import system.domain.IngredientCard;
import system.domain.interfaces.Observer;
import system.domain.interfaces.Mediator;
import system.domain.Cards;

public class IngredientStorageController {
    // public storage 
    //IngredientStorage	ingredient pile: List<ingredientCard>
    //artifact pile: List<artifactCard>	transmuteIngredient(ingredientCard)
    //buyArtifact()

    private List<IngredientCard> ingredientPile;
    private List<ArtifactCard> artifactPile;
    private Observer ingredientStorageUI;
    private Mediator mediator;


    public IngredientStorageController() {
        this.ingredientPile = new ArrayList<IngredientCard>();
        this.artifactPile = new ArrayList<ArtifactCard>();
        this.mediator = GameBoardController.getInstance().getMediator();
        
    }

    public void setObserver(Observer observer) {
        this.ingredientStorageUI = observer;
    }

    public void initializePiles() {
        Random random = new Random();   
        for (int i = 0; i < 24; i++) {
            String ingredientName = GameBoardController.getInstance().getIngredients()[i%8];
            ingredientPile.add(new IngredientCard(ingredientName,GameBoardController.getInstance().getAlchemyMap().get(ingredientName)));
        }
        Collections.shuffle(ingredientPile);

        for (int i = 0; i < GameBoardController.getInstance().getArtifactStrings().length - 1; i++) {
            artifactPile.add(new ArtifactCard(GameBoardController.getInstance().getArtifactStrings()[i], GameBoardController.getInstance().getArtifactEffect()[i], GameBoardController.getInstance().getArtifactUsage()[i]));
        }


        
        for (int i = 0; i < 2; i++) {
            GameBoardController.getInstance().getPlayer(0).getInventory().addIngredient(ingredientPile.remove(0));
        }
        for (int i = 0; i < 2; i++) {
            GameBoardController.getInstance().getPlayer(1).getInventory().addIngredient(ingredientPile.remove(0));
        }
    }


    public IngredientCard transmuteIngredient(int index, IngredientCard card) {
        //this just yielded an error but it is not my use case so I just commented it out
        //GameBoardController.getInstance().getPlayer(index).getInventory().giveIngredient(card);
        GameBoardController.getInstance().getPlayer(index).getInventory().updateGold(2);
        
        return card;

    }

    public ArtifactCard buyArtifact() {
        // control if the player has enough gold
        /*if (GameBoardController.getInstance().getCurrentPlayer().getInventory().getGold() < 3) {
            return null;
           
        }*/
        //draw an artifact card object from the pile and add it to the artifact card list of the corresponding players inventory
        ArtifactCard artifact = drawArtifact();
        System.out.println("Artifact bought: " + artifact.getName());
        
        if (artifact == null) {
            return null;
        }
        GameBoardController.getInstance().getCurrentPlayer().getInventory().getArtifactCards().add(artifact);
        return artifact;
    }

    public void drawIngredient() {
        if (ingredientPile.isEmpty()) {
            ingredientStorageUI.update("Pile is empty!");
        }
        else {
            IngredientCard drawn = ingredientPile.remove(0);
            GameBoardController.getInstance().getCurrentPlayer().getInventory().addIngredient(drawn);
            ingredientStorageUI.update(String.format("CARDREMOVAL: %s", drawn.getName()));
            GameBoardController.getInstance().getCurrentPlayer().playedTurn();
        }
    }

    public ArtifactCard drawArtifact() {
        if (artifactPile.isEmpty()) {
            return null;
        }
        ArtifactCard drawed = artifactPile.remove(artifactPile.size() - 1);
        return drawed;
    }

    public ArrayList<IngredientCard> useArtifact(ArtifactCard card) {  // void dondur switch case yap 
        if (card.getName().equals("Elixir of Insight")) {
            // player can see 3 ingredient cards from the pile
            ArrayList<IngredientCard> cards = new ArrayList<IngredientCard>();
            String cardNames = "";
            /*if (ingredientPile.size() < 3) {
                return null;
            }*/
            for (int i = 1; i <= 3; i++) {
                cards.add(ingredientPile.get(ingredientPile.size() - i));
                cardNames += ingredientPile.get(ingredientPile.size() - i).getName() + ", ";

            }
            ingredientStorageUI.update(String.format("ELIXIR_OF_INSIGHT: %s", cardNames));
            //ingredientStorageUI.update(String.format("CARDREMOVAL: %s", cards.toArray()));
            return cards;
        }
        return null;
    }
//Alchemy alch1 = new Alchemy(-AlchemicalConstants.SMALL, AlchemicalConstants.SMALL, -AlchemicalConstants.LARGE); 
//Alchemy alch2 = new Alchemy(AlchemicalConstants.LARGE, AlchemicalConstants.LARGE, AlchemicalConstants.LARGE);
//Alchemy alch3 = new Alchemy(-AlchemicalConstants.LARGE, -AlchemicalConstants.LARGE, -AlchemicalConstants.LARGE);
//Alchemy alch4 = new Alchemy(-AlchemicalConstants.SMALL, AlchemicalConstants.LARGE, AlchemicalConstants.SMALL);
//Alchemy alch5 = new Alchemy(AlchemicalConstants.LARGE, AlchemicalConstants.SMALL, -AlchemicalConstants.SMALL);
//Alchemy alch6 = new Alchemy(AlchemicalConstants.SMALL,- AlchemicalConstants.LARGE, -AlchemicalConstants.SMALL);
//Alchemy alch7 = new Alchemy(AlchemicalConstants.SMALL, -AlchemicalConstants.SMALL, AlchemicalConstants.LARGE);
//Alchemy alch8 = new Alchemy(-AlchemicalConstants.LARGE, -AlchemicalConstants.SMALL, AlchemicalConstants.SMALL);





    

}
