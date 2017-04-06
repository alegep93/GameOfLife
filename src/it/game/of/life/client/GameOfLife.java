package it.game.of.life.client;

import java.util.Collection;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import it.game.of.life.client.Manager.StepHandler;
import it.game.of.life.client.golcanvas.GoLCanvas;
import it.game.of.life.client.golcanvas.GoLCanvas.CellClickHandler;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GameOfLife implements EntryPoint {

	private GoLCanvas canvas;
	private Manager manager;

	private StepHandler stepHandler = new StepHandler() {

		@Override
		public void stepDone(Grid griglia) {
			Collection<Cell> cells = griglia.getCells();
			draw(cells);
		}
	};

	private CellClickHandler canvasClickHandler = new CellClickHandler() {
		@Override
		public void onCellSelected(int i, int j) {
			if(manager.getCellStatus(i, j)) {
				canvas.clear(i,j);
				manager.setCellStatus(false, i, j);
			} else {
				canvas.drawCell(i, j);
				manager.setCellStatus(true, i, j);
			}
		}
	};

	private void draw(Collection<Cell> cells) {
		canvas.clear();
		for(Cell elem:cells) {
			if(elem.getState().isAlive()) {
				canvas.drawCell(elem.getCoordinates().getX(),elem.getCoordinates().getY());
			}
		}
	}
	
	@Override
	public void onModuleLoad() {
		
		int width = 10, height = 5;
		int iterations = 0;

		manager = new Manager(width,height,iterations);
		canvas = new GoLCanvas(width, height, 1500, 700);

		canvas.addCellCLickHandler(canvasClickHandler );

		draw(manager.getCells());
		
/*
		Scheduler.get().scheduleFixedDelay( 
				new RepeatingCommand() {
					@Override
					public boolean execute() {
						manager.step(stepHandler);
						return true;
					}
				}
				, 1000);
*/
		
		Button stepBtn = new Button("step");
		stepBtn.addClickHandler(e->manager.step(stepHandler));

		Button clearCanvas = new Button("clear");
		clearCanvas.addClickHandler(e->{
			canvas.clear();
			manager.clearGridStatus();
		});
		
		VerticalPanel vPanel = new VerticalPanel();
		vPanel.add(canvas);
		vPanel.add(stepBtn);
		vPanel.add(clearCanvas);
//		RootPanel.get().add(canvas);
//		RootPanel.get().add(stepBtn);
		RootPanel.get().add(vPanel);
	}

}
