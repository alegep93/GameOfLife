package it.game.of.life.client;

import java.util.Collection;

import com.google.gwt.core.client.GWT;

public class Manager {

	int width, height;
	int iterations;

	//
	Grid griglia;

	public Manager(int width, int height, int iterations) {
		this.width = width;
		this.height = height;
		this.iterations = iterations;
		griglia = new Grid4N(width, height);
		//griglia = new Grid8N(width, height);
	}

	
	public void step(StepHandler stepHandler) {
		//stepHandler.stepDone(griglia);

		//for(int i=0;i<iterations;i++) {
		//GWT.log("Iteration nr: "+(i+1));
		GWT.log("Iteration");
		griglia.iteration();
		stepHandler.stepDone(griglia);
		//}
		//GWT.log("Iteration finished.");
	}

	public interface StepHandler {
		public void stepDone(Grid griglia);
	}
	
	public void setCellStatus(boolean status, int i, int j) {
		this.griglia.setCellStatus(status, new Coordinates(i,j));
	}

	public boolean getCellStatus(int i, int j) {
		return this.griglia.getCellStatus(new Coordinates(i,j));
	}

	public Collection<Cell> getCells() {
		return griglia.getCells();
	}

	public void clearGridStatus() {
		griglia.clearStatus();
	}
}
