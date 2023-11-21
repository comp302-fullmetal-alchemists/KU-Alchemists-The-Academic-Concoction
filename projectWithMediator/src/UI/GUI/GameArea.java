package UI.GUI;

import Domain.Controllers.GameAreaController;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.GridLayout;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class GameArea extends JPanel{
    
    private int code;
    private String name;
    private JLabel label;
    private JTextField text;
    private String[] names = {"GameArea1", "GameArea2"};
    private Mediator mediator;
    private GameAreaController controller;

    public GameArea(int code, Mediator mediator){
        super();
        this.code = code;
        this.name = names[code];
        this.label = new JLabel(name); 
        this.text = new JTextField();
        this.mediator = mediator;
        text.addKeyListener(
            new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        GameArea.this.text.setText("");
                        // this can be also handled by a mediator but focuses on UI components rather than player-UI interaction
                        ((GamePanel) GameArea.this.getParent()).changeToCard("environment");
                        ((GameBoard) GameArea.this.getParent().getParent()).changeTurns();
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {
                }   
            }
        );
        setLayout(new GridLayout(2,1));
        add(label);
        add(text);
        this.controller = new GameAreaController();
    }

    public String getName(){
        return name;
    }

    public void takePlayerChoice(String choice){
        text.setText(choice);
        controller.setChoice(choice);
        controller.printChoice();
    }

    public void takePlayerIntChoice(int choice) {
        System.out.println(choice);
    }

    
}
