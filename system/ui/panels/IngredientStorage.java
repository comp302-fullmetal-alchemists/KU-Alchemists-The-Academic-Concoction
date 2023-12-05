package system.ui.panels;

import system.ui.frame.GameContentPane;
import system.domain.controllers.IngredientStorageController;
import system.domain.ArtifactCard;
import system.domain.controllers.GameBoardController;
import system.ui.interfaces.PlayerMediator;
import system.domain.interfaces.Observer;


import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import java.awt.Font;

public class IngredientStorage extends JPanel implements Observer {
    
    private IngredientStorageController ingController;
    private PlayerMediator mediator;
    private JButton back;
    private JButton ingredientButton;
    private JButton artifactButton;
    private JButton transmuteIngButton;
    private Boolean active = false ;
    
    
    public IngredientStorage(PlayerMediator mediator) {
        super();
        setBackground(new Color(58, 77, 108));
        this.mediator = mediator;
        this.ingController = GameBoardController.getInstance().getIngredientStorageController(); 
        ingController.setObserver(this);
        setLayout(null);
        this.back = createNavButton("village", "Back to the village");
        add(back);
        this.ingredientButton = createIngButton("Draw Ingredient");
        add(ingredientButton);
        this.artifactButton = createArtifactButton("Draw Artifact Card");
        add(artifactButton);
        this.transmuteIngButton = createTransmuteIngButton();
        add(transmuteIngButton);
        
        JTextPane txtpnActions = new JTextPane();
        txtpnActions.setFont(new Font("Apple Chancery", Font.PLAIN, 20));
        txtpnActions.setForeground(UIManager.getColor("List.selectionForeground"));
        txtpnActions.setBackground(new Color(58, 77, 108));
        txtpnActions.setText("Actions");
        txtpnActions.setBounds(27, 19, 144, 42);
        add(txtpnActions);
    }
    

    public JButton createIngButton(String text) {
        JButton ingButton = new JButton(text);
        ingButton.setBounds(27, 60, 179, 29);
        ingButton.addActionListener(
            new ActionListener() {
                @Override 
                public void actionPerformed(ActionEvent e) {
                    ingController.drawIngredient();
                }
            }
        );
        return ingButton;
    }

    public JButton createNavButton(String nav, String text) {
        JButton button = new JButton(text);
        button.setBounds(271, 19, 159, 29);
        button.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ((GameContentPane) IngredientStorage.this.getParent()).changeView(nav);
                    ingController.deactivate();
                    active = false;
                    transmuteIngButton.setText("Trasnmute Ingredient");
                }
            }
        );
        return button;
    }

    public JButton createArtifactButton(String text) {
        JButton artifactButton = new JButton(text);
        artifactButton.setBounds(27, 101, 179, 29);
        artifactButton.addActionListener(
            new ActionListener() {
                @Override 
                public void actionPerformed(ActionEvent e) {
                    ArtifactCard drawn = ingController.buyArtifact();
                    JOptionPane.showMessageDialog(IngredientStorage.this, String.format("You have drawn %s!",drawn.getName()));
                    ingController.useArtifact(drawn);
                    
                    
                }
            }
        );
        return artifactButton;}
    
        public JButton createTransmuteIngButton() {
            String deactiveText = "Transmute Ingredient";
            String activeText = "finish action";
            JButton button = new JButton(deactiveText);
            button.setBounds(27, 142, 179, 29);
            button.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (active){
                            ingController.deactivate();
                            active = false;
                            button.setText(deactiveText);
                        }
                        else {
                            ingController.activate();
                            active = true;
                            button.setText(activeText);
                        }
                    }
    
                }
            );
            
            return button;
        }






    @Override
    public void update(String msg) {
        if (msg.equals("Pile is empty!")) {
            JOptionPane.showMessageDialog(IngredientStorage.this, "Pile is empty!");
        }
        else if (msg.contains("CARDREMOVAL")) {
            JOptionPane.showMessageDialog(IngredientStorage.this, String.format("You have drawn %s!", msg.substring(13)));
        }
        else if (msg.contains("ELIXIR_OF_INSIGHT")) {
            JOptionPane.showMessageDialog(IngredientStorage.this, String.format("You have drawn the elixir of insight card! The last 3 cards in the ingredient pile:  %s!", msg.substring(19)));
        }
    }
}