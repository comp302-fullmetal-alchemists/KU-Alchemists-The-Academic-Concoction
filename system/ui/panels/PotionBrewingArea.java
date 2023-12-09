package system.ui.panels;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;


import java.awt.event.ActionListener;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.awt.event.MouseEvent;
import system.ui.frame.GameContentPane;
import system.domain.controllers.PotionBrewingAreaController;
import system.domain.Potion;
import system.domain.controllers.GameBoardController;
import system.domain.controllers.IngredientStorageController;
import system.domain.interfaces.Observer;

public class PotionBrewingArea extends JPanel implements Observer {

    private PotionBrewingAreaController pbaController;
    private IngredientStorageController ingController;
    private JButton back;
    private JButton activation;
    private JTextField ingredient1;  //something to display the ingredients chosen
    private JTextField ingredient2;
    private JButton makePotion;
    private JTextArea AdventurerInfo;
    private JTextField potionToSell;
    private JComboBox<String> offerDropdown;
    private JButton sellPotionButton;

    String deactiveText = "Activate choice";
    String activeText = "Deactivate choice";

    public PotionBrewingArea() {
        super();
        setBackground(new Color(58, 77, 108));
        this.pbaController = GameBoardController.getInstance().getPotionBrewingAreaController();
        this.ingController = GameBoardController.getInstance().getIngredientStorageController();
        pbaController.setObserver(this);
        ingController.setObserver(this);

        this.back = createNavButton("village", "Back to the village");
        add(back);
        this.activation = createActivationButton();
        add(activation);
        this.ingredient1 = createIngredientField("Give Ingredient1");
        add(ingredient1);
        this.ingredient2 = createIngredientField("Give Ingredient2");
        add(ingredient2);
        this.makePotion = createPotionButton("Make Potion");
        add(makePotion);

        this.AdventurerInfo = new JTextArea(pbaController.giveOffer(), 5,12);
        Font f = new Font("Serif", Font.ITALIC, 15);
        AdventurerInfo.setFont(f);
        add(AdventurerInfo);

        this.offerDropdown = optionsDropdown();
        add(offerDropdown);
        this.sellPotionButton = sellPotionButton();
        add(sellPotionButton);
    }

    public JComboBox<String> optionsDropdown() {
        String[] quantityOptions = {"I demand 1 gold - My potion is a gamble of curse, calm or charm.", "I demand 2 golds - My potion contains no malevolence.", "I demand 3 golds - My potion is assured of goodly nature."};
        return new JComboBox<>(quantityOptions);
    }

    public JButton sellPotionButton(){
        JButton sellPotionButton = new JButton("Select a Potion to sell");
        sellPotionButton.setBounds(27, 142, 179, 29);
        sellPotionButton.addActionListener(new ActionListener() {
                //int selectedOffer = offerDropdown.getSelectedIndex();
                //pbaController.agreeOffer(selectedOffer + 1);
            public void actionPerformed(ActionEvent e) {
                if (ingController.isActive()){
                    ingController.deactivate();
                    sellPotionButton.setText("Stop Selling");
                }
                else {
                    ingController.activate();
                    sellPotionButton.setText("Select a Potion to sell");
                }
            }


        });
        return sellPotionButton;
    }

    public void initialize() {
        pbaController.deactivate();
        activation.setText(deactiveText);
    }


    public JButton createNavButton(String nav, String text) {
        JButton button = new JButton(text);
        button.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ((GameContentPane) PotionBrewingArea.this.getParent()).changeView(nav);
                    if (!ingredient1.getText().equals("Give Ingredient1")) pbaController.discardIngredient(1);
                    if (!ingredient2.getText().equals("Give Ingredient2")) pbaController.discardIngredient(2);
                    initialize();
                }

            }
        );
        return button;
    }

    public JButton createActivationButton() {
        JButton button = new JButton(deactiveText);
        button.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (pbaController.isActive()) {
                        pbaController.deactivate();
                        button.setText(deactiveText);
                    }
                    else {
                        pbaController.activate();
                        button.setText(activeText);
                    }
                }

            }
        );
        return button;
    }

    public JTextField createIngredientField(String name) {
        JTextField ing = new JTextField(20);
        ing.setEnabled(false);
        ing.setText(name);
        ing.addMouseListener(
            new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    ing.setText(name);
                    if (name.equals("Give Ingredient1")) pbaController.discardIngredient(1);
                    else if (name.equals("Give Ingredient2")) pbaController.discardIngredient(2);
                }

                @Override
                public void mousePressed(MouseEvent e) {}

                @Override
                public void mouseReleased(MouseEvent e) {}

                @Override
                public void mouseEntered(MouseEvent e) {}

                @Override
                public void mouseExited(MouseEvent e) {}

            }
        );
        return ing;
    }

    public JButton createPotionButton(String text) {
        JButton button = new JButton(text);
        button.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    pbaController.makePotion();
                }

            }
        );
        return button;
    }


    public void showMessageDialog(String displayMsg) {
        JOptionPane.showMessageDialog(this, displayMsg);
    }

    @Override
    public void update(String msg) {
        if (msg.contains("NEW_INGREDIENT1")) {
            ingredient1.setText(msg.split(":")[1]);
        }
        else if (msg.contains("NEW_INGREDIENT2")) {
            ingredient2.setText(msg.split(":")[1]);
        }
        else if (msg.contains("ABSENT_INGREDIENTS")) {
            showMessageDialog("Please fill ingredients");
        }
        else if (msg.contains("BREWED_POTION")) {
            ingredient1.setText("Give Ingredient1");
            ingredient2.setText("Give Ingredient2");
            showMessageDialog("You have brewed a " + msg.split(":")[1] + " potion!");

        }
        else if (msg.contains("DISCARD_INGREDIENT1")) {
            ingredient1.setText("Give Ingredient1");
        }
        else if (msg.contains("DISCARD_INGREDIENT2")) {
            ingredient2.setText("Give Ingredient2");
        }

    }
}
