package it.game.of.life.client.golcanvas;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class GoLCanvas implements IsWidget, ClickHandler {

	private Canvas canvas = Canvas.createIfSupported();
	private int cellsX, cellsY, width, height;
	private double dx, dy;
	private String backgroundColor="gray";
	private String cellColor="green";
	private String strokeColor="black";
	
	public GoLCanvas(int cellsX, int cellsY, int width, int height) {
		this.cellsX=cellsX;
		this.cellsY=cellsY;
		this.width=width;
		this.height=height;
		
		dx=(double)width/(double)cellsX;
		dy=(double)height/(double)cellsY;
		
		canvas.setCoordinateSpaceWidth(width);
		canvas.setCoordinateSpaceHeight(height);
		canvas.setPixelSize(width, height);
		
		//Default rect
		this.clear();
		canvas.addClickHandler(this);
	}
	
	public interface CellClickHandler {
		public void onCellSelected(int i, int j);
	}
	
	List<CellClickHandler> canvasClickHandlers = new ArrayList<>();
	
	public void addCellCLickHandler(CellClickHandler handler) {
		if(handler!=null)
			this.canvasClickHandlers.add(handler);
		
	}
	
	public void clear() {
		canvas.getContext2d().setFillStyle(backgroundColor);
		canvas.getContext2d().fillRect(0,0,width,height);
		
		canvas.getContext2d().setStrokeStyle(strokeColor);
		for(int i=0;i<=cellsX;i++) {
			canvas.getContext2d().beginPath();
			canvas.getContext2d().moveTo(i*dx,0);
			canvas.getContext2d().lineTo(i*dx,height);
			canvas.getContext2d().setLineWidth(0.5);
			canvas.getContext2d().stroke();
			//canvas.getContext2d().strokeRect(i*dx,0,1,height);
		}
		for(int j=0;j<=cellsY;j++) {
			canvas.getContext2d().beginPath();
			canvas.getContext2d().moveTo(0,j*dy);
			canvas.getContext2d().lineTo(width,j*dy);
			canvas.getContext2d().setLineWidth(0.5);
			canvas.getContext2d().stroke();
			//canvas.getContext2d().strokeRect(0,j*dy,width,1);
		}
	}


	public void clear(int i, int j) {
		canvas.getContext2d().setFillStyle(backgroundColor);
		canvas.getContext2d().fillRect(i*dx,j*dy,dx,dy);
		canvas.getContext2d().setStrokeStyle(strokeColor);
		canvas.getContext2d().strokeRect(i*dx,j*dy,dx,dy);
	}

	public void drawCell(int i, int j) {
		canvas.getContext2d().setFillStyle(cellColor);
		canvas.getContext2d().fillRect(i*dx,j*dy,dx,dy);
		canvas.getContext2d().setStrokeStyle(strokeColor);
		canvas.getContext2d().strokeRect(i*dx,j*dy,dx,dy);
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public String getCellColor() {
		return cellColor;
	}

	public void setCellColor(String cellColor) {
		this.cellColor = cellColor;
	}
	
	public String getStrokeColor() {
		return strokeColor;
	}

	public void setStrokeColor(String strokeColor) {
		this.strokeColor = strokeColor;
	}
	
	@Override
	public Widget asWidget() {
		return canvas;
	}

	@Override
	public void onClick(ClickEvent event) {
		int relX = (int) Math.floor(event.getX()/dx);
		int relY = (int) Math.floor(event.getY()/dy);
		for(CellClickHandler handler:canvasClickHandlers) {
			handler.onCellSelected(relX, relY);
		}
	}

}
