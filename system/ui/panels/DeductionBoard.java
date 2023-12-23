package system.ui.panels;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;

import system.domain.controllers.DeductionBoardController;
import system.domain.controllers.GameBoardController;
import system.domain.interfaces.Observer;
import system.ui.frame.GameContentPane;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class DeductionBoard extends JPanel implements Observer{

    private JButton back;
    private JButton navBtn;
    private JPanel deductionGrid;
    private DeductionBoardController dbController;

    public DeductionBoard() {
        super();
        setBackground(new Color(58, 77, 108));
        setLayout(null);
        this.dbController = GameBoardController.getInstance().getDeductionBoardController();
        dbController.setObserver(this);
        
        navBtn = new JButton("Back to the Village");
        navBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		((GameContentPane) DeductionBoard.this.getParent()).changeView("village");
        	}
        });
        navBtn.setBounds(303, 10, 150, 30);
        add(navBtn);
        
        deductionGrid = new JPanel();
        deductionGrid.setBounds(62, 370, 628, 320);
        add(deductionGrid);
        deductionGrid.setLayout(null);
        
        JLabel lblIng1 = new JLabel("New label");
        lblIng1.setOpaque(true);
        lblIng1.setBounds(62, 280, 75, 80);
        add(lblIng1);
        
        JLabel lblIng2 = new JLabel("New label");
        lblIng2.setOpaque(true);
        lblIng2.setBounds(141, 280, 75, 80);
        add(lblIng2);
        
        JLabel lblIng3 = new JLabel("New label");
        lblIng3.setOpaque(true);
        lblIng3.setBounds(220, 280, 75, 80);
        add(lblIng3);
        
        JLabel lblIng4 = new JLabel("New label");
        lblIng4.setOpaque(true);
        lblIng4.setBounds(299, 280, 75, 80);
        add(lblIng4);
        
        JLabel lblIng5 = new JLabel("New label");
        lblIng5.setOpaque(true);
        lblIng5.setBounds(378, 280, 75, 80);
        add(lblIng5);
        
        JLabel lblIng6 = new JLabel("New label");
        lblIng6.setOpaque(true);
        lblIng6.setBounds(457, 280, 75, 80);
        add(lblIng6);
        
        JLabel lblIng7 = new JLabel("New label");
        lblIng7.setOpaque(true);
        lblIng7.setBounds(536, 280, 75, 80);
        add(lblIng7);
        
        JLabel lblIng8 = new JLabel("New label");
        lblIng8.setOpaque(true);
        lblIng8.setBounds(615, 280, 75, 80);
        add(lblIng8);
    }
    
    public void activate() {
    	dbController.activate();
    }

	@Override
	public void update(String msg) {
		System.out.println(msg);
	}
}
