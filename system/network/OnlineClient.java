package system.network;

import system.domain.ArtifactCard;
import system.domain.IngredientCard;
import system.domain.controllers.Player;

public class OnlineClient implements IClientAdapter {

    @Override
    public void connectToServer() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'connectToServer'");
    }

    @Override
    public void startAuthentication() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'startAuthentication'");
    }

    @Override
    public boolean validateUserChoices(String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'validateUserChoices'");
    }

    @Override
    public void registerPlayer(Player p) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'registerPlayer'");
    }

    @Override
    public void initialize() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'initialize'");
    }

    @Override
    public void authorize() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'authorize'");
    }

    @Override
    public void deauthorize() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deauthorize'");
    }

    @Override
    public void endPlayerTurn() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'endPlayerTurn'");
    }

    @Override
    public boolean ingPileIsEmpty() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ingPileIsEmpty'");
    }

    @Override
    public IngredientCard drawIngredient() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'drawIngredient'");
    }

    @Override
    public boolean artifactPileIsEmpty() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'artifactPileIsEmpty'");
    }

    @Override
    public ArtifactCard drawArtifact() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'drawArtifact'");
    }
    //her client player burda
}
