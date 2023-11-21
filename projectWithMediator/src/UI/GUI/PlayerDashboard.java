package UI.GUI;

import Domain.Player;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.GridLayout;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class PlayerDashboard extends JPanel {
    
    private Player player;
    private JLabel label;
    private infoLabel prop1;
    private infoLabel prop2;
    private Mediator mediator;

    public PlayerDashboard(Player player, Mediator mediator) {
        super();
        this.player = player;
        this.label = new JLabel(player.getName());
        this.mediator = mediator;
        this.prop1 = new infoLabel(new JLabel(player.getProp1()), "prop1");
        this.prop2 = new infoLabel(new JLabel(player.getProp2()), "prop2");

        addMouseListeners(prop1);
        addMouseListeners(prop2);

        setLayout(new GridLayout(3,1));
        add(label);
        add(prop1.label);
        add(prop2.label);

    }

    public Player getPlayer() {
        return player;
    }

    public Boolean isInTurn() {
        return player.isInTurn();
    }


    public void addMouseListeners(infoLabel prop) {
        prop.label.addMouseListener(
                new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (prop.active) {
                            mediator.sendChoice(prop.name);
                            mediator.sendIntChoice(1);
                        }
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
    }

    public void readyArea(String AreaName) {
        if (AreaName.equals("GameArea1")) {
            prop1.active = true;
            prop2.active = false;
        }
        else if (AreaName.equals("GameArea2")) {
            prop1.active = false;
            prop2.active = true;
        }
    }

    private class infoLabel {
        private JLabel label;
        private String name;
        private Boolean active;

        private infoLabel(JLabel label, String name) {
            this.label = label;
            this.name = name;
            this.active = false;
        }


    }

}
