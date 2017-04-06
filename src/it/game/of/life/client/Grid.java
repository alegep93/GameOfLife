package it.game.of.life.client;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gwt.core.client.GWT;

public abstract class Grid {
	private Map<Coordinates, Cell> griglia = new HashMap<>();
	private int maxX, maxY;

	public Grid(int size_x, int size_y) {
		
		this.maxX = size_x;
		this.maxY = size_y;
		//
		System.out.println("Init grid ... ");
		//Window.alert("x:"+maxX+" Y: "+maxY);
		for(int i=0;i<maxX;i++) {
			for(int j=0;j<maxY;j++) {
				Coordinates coord = new Coordinates(i, j);
				//Le celle sono tutte con valore false di default ... puo' essere modificato
				Cell tmp = new Cell(coord);
				griglia.put(coord, tmp);
			}
		}
		GWT.log("Computing neighboors ... ");
		//System.out.println("Computing neighboors ... ");
		//Andiamo a calcolare i neighboors
		
		
		for(Coordinates elem:griglia.keySet()) {
			//System.out.println(""+griglia.size());
			Set<Coordinates> neighboors = computeNeigboorsPositions(elem.getX(),elem.getY());
			Cell localCell = griglia.get(elem);
			Set<BaseCell> vicinato = computeNeighboors(neighboors);
			localCell.setNeighboors(vicinato);
			griglia.put(elem, localCell);
		}
		
		
	}

	private Set<BaseCell> computeNeighboors(Set<Coordinates> neighboors) {
		Set<BaseCell> retVal = new HashSet<>();
		//
		for(Coordinates coord:neighboors) {
			boolean a = griglia.containsKey(coord);
			if(!a) {
				GWT.log("non contiene ("+coord.getX()+", "+coord.getY()+")");
				continue;
			}
			retVal.add(griglia.get(coord));
		}
		return retVal;
	}

	protected abstract Set<Coordinates> computeNeigboorsPositions(int i, int j);

	public void iteration() {
		//Ciclo sulla griglia per calcolare il nuovo stato
		for(Entry<Coordinates, Cell> entry:griglia.entrySet()) {
			entry.getValue().iterate();
		}

		//Ciclo sulla griglia per aggiornare lo stato
		for(Entry<Coordinates, Cell> entry:griglia.entrySet()) {
			entry.getValue().updateState();
		}
	}
	
	public void setCellStatus(boolean status, Coordinates coord) {
		Cell elem = griglia.get(coord);
		if(elem!=null) {
			elem.getState().setAlive(status);
		}
	}
	
	public Collection<Cell> getCells() {
		return griglia.values();
	}
	
	public int getMaxX() {
		return maxX;
	}

	public int getMaxY() {
		return maxY;
	}

	public boolean getCellStatus(Coordinates coordinates) {
		return griglia.get(coordinates).getState().isAlive();
	}

	public void clearStatus() {
		for(Cell elem:griglia.values()) {
			elem.getState().setAlive(false);
		}
	}
}
