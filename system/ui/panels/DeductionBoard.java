package system.ui.panels;
import javax.swing.ImageIcon;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.SwingConstants;
import java.awt.Font;


public class DeductionBoard extends JPanel implements Observer{

    private JButton back;
    private JButton navBtn;
    private JLabel deductionGrid;
    private DeductionBoardController dbController;
    private JLabel[][] markers = new JLabel[8][8];
    
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
        navBtn.setBounds(10, 10, 150, 30);
        add(navBtn);
        
        deductionGrid = new JLabel();
        deductionGrid.setIcon(new ImageIcon(getClass().getResource("/resources/deductionBoard.png")));

        deductionGrid.setBounds(16, 340, 720, 360);
        add(deductionGrid);
        deductionGrid.setLayout(null);
        for (int i = 0; i < 8; i++) {
        	for (int j = 0; j < 8; j++) {
        		markers[i][j] = new JLabel();
                markers[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                markers[i][j].setFont(new Font("Tahoma", Font.PLAIN, 15));
        		markers[i][j].setBounds(90*j + 45 + 5, 45*i + 5, 30, 30);
        		deductionGrid.add(markers[i][j]);
    		}
    	}
        deductionGrid.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		int row = e.getY() / 45;
        		int col = e.getX() / 90;
        		/*markers[row][col].setOpaque(!markers[row][col].isOpaque());*/
        		markers[row][col].setText(markers[row][col].getText().equals("")? "X" :"");
        		deductionGrid.revalidate();
        		deductionGrid.repaint();
        	}
        });
        
        
        JLabel lblIng1 = new JLabel("Solaris Root");
        lblIng1.setFont(new Font("Tahoma", Font.PLAIN, 8));
        lblIng1.setHorizontalAlignment(SwingConstants.CENTER);
        lblIng1.setOpaque(true);
        lblIng1.setBounds(16, 320, 90, 20);
        add(lblIng1);
        
        JLabel lblIng2 = new JLabel("Bat Wing");
        lblIng2.setFont(new Font("Tahoma", Font.PLAIN, 8));
        lblIng2.setHorizontalAlignment(SwingConstants.CENTER);
        lblIng2.setOpaque(true);
        lblIng2.setBounds(106, 320, 90, 20);
        add(lblIng2);
        
        JLabel lblIng3 = new JLabel("Toad Stool");
        lblIng3.setFont(new Font("Tahoma", Font.PLAIN, 8));
        lblIng3.setHorizontalAlignment(SwingConstants.CENTER);
        lblIng3.setOpaque(true);
        lblIng3.setBounds(196, 320, 90, 20);
        add(lblIng3);
        
        JLabel lblIng4 = new JLabel("Owl Feather");
        lblIng4.setFont(new Font("Tahoma", Font.PLAIN, 8));
        lblIng4.setHorizontalAlignment(SwingConstants.CENTER);
        lblIng4.setOpaque(true);
        lblIng4.setBounds(286, 320, 90, 20);
        add(lblIng4);
        
        JLabel lblIng5 = new JLabel("Snake Venom");
        lblIng5.setFont(new Font("Tahoma", Font.PLAIN, 8));
        lblIng5.setHorizontalAlignment(SwingConstants.CENTER);
        lblIng5.setOpaque(true);
        lblIng5.setBounds(376, 320, 90, 20);
        add(lblIng5);
        
        JLabel lblIng6 = new JLabel("Rat Tail");
        lblIng6.setFont(new Font("Tahoma", Font.PLAIN, 8));
        lblIng6.setHorizontalAlignment(SwingConstants.CENTER);
        lblIng6.setOpaque(true);
        lblIng6.setBounds(466, 320, 90, 20);
        add(lblIng6);
        
        JLabel lblIng7 = new JLabel("Spider Web");
        lblIng7.setFont(new Font("Tahoma", Font.PLAIN, 8));
        lblIng7.setHorizontalAlignment(SwingConstants.CENTER);
        lblIng7.setOpaque(true);
        lblIng7.setBounds(556, 320, 90, 20);
        add(lblIng7);
        
        JLabel lblIng8 = new JLabel("Newt Eye");
        lblIng8.setFont(new Font("Tahoma", Font.PLAIN, 8));
        lblIng8.setHorizontalAlignment(SwingConstants.CENTER);
        lblIng8.setOpaque(true);
        lblIng8.setBounds(646, 320, 90, 20);
        add(lblIng8);
    
    }
    
    public void activate() {
    	dbController.pull();
    }
    
    public void clear() {
    	for (int i = 0; i < 8; i++) {
    		for (int j = 0; j < 8; j++) {
    			if (markers[i][j].getText().equals("X")) dbController.markGrid(i,  j, 1);
    			else dbController.markGrid(i,  j, 0);
    			markers[i][j].setText("");
    		}
    	}
    }

    
    public void markGrid() {
    	for (int loc: dbController.getMarkers()) {
    		markers[loc / 8][loc % 8].setText("X");
    	}
    }

	@Override
	public void update(String msg) {
		if (msg.contains("OPENED")) {
			markGrid();
		}

	}
	
}
