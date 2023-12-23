package system.domain.controllers;

import system.domain.interfaces.Collector;
import system.domain.interfaces.Observer;
import system.domain.interfaces.Mediator;
import system.domain.ResultsTriangle;

public class DeductionBoardController {

    //build: deduction grid

    //function: markDeductionGrid() when user learns an alchemy formula
	private int[][] deductionGrid;
	private Observer deductionBoardUI;
	private Mediator mediator;
	private boolean active;
	
	public DeductionBoardController() {
		this.deductionGrid = new int[8][8];
		this.mediator = GameBoardController.getInstance().getMediator();
	}

	public void setObserver(Observer observer) {
		this.deductionBoardUI = observer;
	}
	
	
	public void activate() {
		deductionBoardUI.update(mediator.getPlayersResults());
	}




}
