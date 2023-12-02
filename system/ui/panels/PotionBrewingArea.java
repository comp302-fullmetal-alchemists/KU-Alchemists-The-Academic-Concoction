package system.ui.panels;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import system.ui.frame.GameContentPane;
import system.ui.interfaces.PlayerMediator;
import system.domain.controllers.PotionBrewingAreaController;
import system.domain.controllers.GameBoardController;
import system.domain.interfaces.Observer;

public class PotionBrewingArea extends JPanel implements Observer {

    private PlayerMediator mediator;
    private PotionBrewingAreaController pbaController;
    private JButton back;
    private JButton activation;
    private boolean active = false;
    private JTextField ingredient1;  //something to display the ingredients chosen
    private JTextField ingredient2;

    public PotionBrewingArea(PlayerMediator mediator) {
        super();
        this.mediator = mediator;
        this.pbaController = GameBoardController.getInstance().getPotionBrewingAreaController();
        pbaController.setObserver(this);
        this.back = createNavButton("village", "Back to the village");
        add(back);
        this.activation = createActivationButton();
        add(activation);
        this.ingredient1 = new JTextField(20);
        add(ingredient1);
        this.ingredient2 = new JTextField(20);
        add(ingredient2);
    }


    public JButton createNavButton(String nav, String text) {
        JButton button = new JButton(text);
        button.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ((GameContentPane) PotionBrewingArea.this.getParent()).changeView(nav);
                }

            }
        );
        return button;
    }

    public JButton createActivationButton() {
        String deactiveText = "Activate choice";
        String activeText = "Deactivate choice";
        JButton button = new JButton(deactiveText);
        button.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (active){
                        pbaController.deactivate();
                        active = false;
                        button.setText(deactiveText);
                    }
                    else {
                        pbaController.activate();
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
        if (msg.contains("NEW_INGREDIENT1")) {
            ingredient1.setText(msg.substring(17));
        }
        else if (msg.contains("NEW_INGREDIENT2")) {
            ingredient2.setText(msg.substring(17));
        }
    }
}
