package system.domain.util;

import system.domain.Player;
import system.domain.interfaces.Collector;
import system.domain.interfaces.Mediator;
import system.domain.IngredientCard;
import system.domain.ArtifactCard;
import system.domain.Potion;

public class ConcreteMediator implements Mediator {

    private Player player;
    private Collector collector;


    public ConcreteMediator(Player player) {
        this.player = player;
    }

    @Override
    public void connectPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void connectCollector(Collector collector) {
        this.collector = collector;
    }

    @Override
    public void disconnectCollector() {
        this.collector = null;
    }

    @Override
    public <T> void sendToPlayer(T item) {
        if (item instanceof IngredientCard) {
            player.getInventory().addIngredient((IngredientCard) item);
        }
        else if (item instanceof ArtifactCard) {
            player.getInventory().addArtifact((ArtifactCard) item);
        }
        else if (item instanceof Potion) {
            player.getInventory().addPotion((Potion) item);
        }
    }

    @Override
    public <T> boolean sendToCollector(T item) {
        if (collector != null) {
            if (item instanceof IngredientCard) {
                collector.collectIngredient((IngredientCard) item);
            }
            else if (item instanceof ArtifactCard) {
                collector.collectArtifact((ArtifactCard) item);
            }
            else if (item instanceof Potion) {
                collector.collectPotion((Potion) item);
            }
            return true;
        }
        return false;
    }
    
}
