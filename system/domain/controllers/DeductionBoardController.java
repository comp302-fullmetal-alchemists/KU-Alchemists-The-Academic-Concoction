package system.domain.controllers;


import system.domain.interfaces.Observer;
import system.domain.DeductionGrid;
import system.domain.ResultsTriangle;

import java.util.List;

public class DeductionBoardController {

    //build: deduction grid

    //function: markDeductionGrid() when user learns an alchemy formula
	private Observer deductionBoardUI;
	private ResultsTriangle resultsTrig;
	private DeductionGrid deductionGrid;
	public DeductionBoardController() {}

	public void setObserver(Observer observer) {
		this.deductionBoardUI = observer;
	}
	
	public void pull() {
		resultsTrig = GameBoardController.getInstance().getPlayer().getResultsTriangle();
		deductionGrid = GameBoardController.getInstance().getPlayer().getDeductionGrid();
		deductionBoardUI.update("OPENED");
	}
	
	public List<String> getResultList() {
		return resultsTrig.getResultList();
	}
	
	public List<Integer> getMarkers() {
		return deductionGrid.getMarkers();
	}
	
	public void markGrid(int i, int j, int val) {
		deductionGrid.mark(i, j, val);
	}
	

}
