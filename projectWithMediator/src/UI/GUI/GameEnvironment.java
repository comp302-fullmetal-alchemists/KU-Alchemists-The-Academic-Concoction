package UI.GUI;


import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

public class GameEnvironment extends JPanel {
    
    private JButton g1Button;
    private JButton g2Button;


    public GameEnvironment(){
        super();
        this.g1Button = createButton("GameArea1");
        this.g2Button = createButton("GameArea2");
        setLayout(new GridLayout(2,1));
        add(g1Button);
        add(g2Button);   
}

    public JButton createButton(String GameAreaName) {
        JButton areaButton = new JButton(GameAreaName);
        areaButton.addActionListener(
            new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    ((GamePanel) GameEnvironment.this.getParent()).changeToCard(GameAreaName);
                }
            }
        );
        return areaButton;
    }
}
