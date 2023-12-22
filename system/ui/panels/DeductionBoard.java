package system.ui.panels;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;


import system.ui.frame.GameContentPane;


public class DeductionBoard extends JPanel{

    private JButton back;
    private JButton navBtn;
    private JPanel deductionGrid;

    public DeductionBoard() {
        super();
        setBackground(new Color(58, 77, 108));
        setLayout(null);
        
        navBtn = new JButton("Back to the Village");
        navBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		((GameContentPane) DeductionBoard.this.getParent()).changeView("village");
        	}
        });
        navBtn.setBounds(20, 10, 150, 30);
        add(navBtn);
        
        deductionGrid = new JPanel();
        deductionGrid.setBounds(76, 290, 600, 400);
        add(deductionGrid);
    }
}
