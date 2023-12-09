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
    private JButton back;
    private JButton activation;
    private JButton activationPotion;
    private JTextField ingredient1;  //something to display the ingredients chosen
    private JTextField ingredient2;
    private JTextField potion;
    private JButton makePotion;
    private JTextArea AdventurerInfo;
    private JComboBox<String> offerDropdown;
    private JButton sellPotionButton;
    private String[] offerStrings = {"I demand 1 gold - My potion is a gamble of curse, calm or charm.", "I demand 2 golds - My potion contains no malevolence.", "I demand 3 golds - My potion is assured of goodly nature."};


    String deactiveText = "Activate choice";
    String activeText = "Deactivate choice";

    public PotionBrewingArea() {
        super();
        setBackground(new Color(58, 77, 108));
        this.pbaController = GameBoardController.getInstance().getPotionBrewingAreaController();
        pbaController.setObserver(this);

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
        add(AdventurerInfo); //adds the adventurer prompt to the area
        this.activationPotion = createActivationButtonPotion();
        add(activationPotion);
        this.potion = createPotionField("Select Potion to sell");
        add(potion);
        this.sellPotionButton = sellPotionButton("Select Potion to Sell");
        add(sellPotionButton);
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


    //NOT NEEDED?
    public JButton createActivationButtonPotion() {
        JButton button = new JButton("Click to Sell a Potion");
        button.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (pbaController.isActive()) {
                        pbaController.deactivate();
                        button.setText("Click to Sell a Potion");
                    }
                    else {
                        pbaController.activate();
                        button.setText("Select the Potion");
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

    public JTextField createPotionField(String name) {
        JTextField potion = new JTextField(20);
        potion.setEnabled(false);
        potion.setText(name);
        potion.addMouseListener(
            new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    potion.setText(name);
                    if (name.equals("Give a Potion")) pbaController.discardPotion();
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
    return potion;
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

    public JButton sellPotionButton(String text) {
    JButton button = new JButton(text);
    button.addActionListener(
        new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pbaController.sellPotion();
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
        else if (msg.contains("NEW_POTION")) {
            potion.setText(msg.split(":")[1]);
        }
        else if (msg.contains("ABSENT_INGREDIENTS")) {
            showMessageDialog("Please fill ingredients");
        }
        else if (msg.contains("ABSENT_POTION")) {
            showMessageDialog("Please select potion to sell");
        }
        else if (msg.contains("BREWED_POTION")) {
            ingredient1.setText("Give Ingredient1");
            ingredient2.setText("Give Ingredient2");
            showMessageDialog("You have brewed a " + msg.split(":")[1] + " potion!");
        }
        else if (msg.contains("SOLD_POTION")) {
            potion.setText("Select Potion to sell");
            showMessageDialog(offerStrings[Integer.parseInt(msg.split(":")[1])]);
        }

        else if (msg.contains("DISCARD_INGREDIENT1")) {
            ingredient1.setText("Give Ingredient1");
        }
        else if (msg.contains("DISCARD_INGREDIENT2")) {
            ingredient2.setText("Give Ingredient2");
        }
        else if (msg.contains("DISCARD_POTION")) {
            potion.setText("Select Potion to sell");
        }

    }
}
