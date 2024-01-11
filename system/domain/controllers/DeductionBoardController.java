package system.domain.controllers;

import system.domain.interfaces.Collector;
import system.domain.interfaces.Observer;
import system.domain.interfaces.Mediator;
import system.domain.ResultsTriangle;

public class DeductionBoardController implements Collector{

    //build: deduction grid

    //function: markDeductionGrid() when user learns an alchemy formula
	private int[][] deductionGrid;
	private Observer deductionBoardUI;
	private Mediator mediator;
	private boolean active = false;
	
	public DeductionBoardController() {
		this.deductionGrid = new int[8][8];
		this.mediator = GameBoardController.getInstance().getMediator();
	}

	public void setObserver(Observer observer) {
		this.deductionBoardUI = observer;
	}

	@Override
	public <T> void collectItem(T item) {
		if (item instanceof ResultsTriangle) {
			ResultsTriangle trig = (ResultsTriangle) item;
			deductionBoardUI.update(trig.getResultList());
		}
		
	}

	@Override
	public void activate() {
		mediator.connectCollector(this);
        active = true;
        mediator.getPlayer().sendResults();
	}

	@Override
	public void deactivate() {
		mediator.disconnectCollector();
        active = false;
	}

	@Override
	public boolean isActive() {
		 return active;
	}
	




}
