package it.game.of.life.client;

import java.util.HashSet;
import java.util.Set;

public class Grid8N extends Grid {

	public Grid8N(int size_x, int size_y) {
		super(size_x, size_y);
	}

	@Override
	protected Set<Coordinates> computeNeigboorsPositions(int i, int j) {
		Set<Coordinates> retVal = new HashSet<>();
		
		//left neighboors
		if(i == 0) {
			retVal.add(new Coordinates(getMaxX()-1, j));
		} else {
			retVal.add(new Coordinates(i-1, j));
		}
		
		//right neighboors
		if(i == (getMaxX()-1)) {
			retVal.add(new Coordinates(0, j));
		} else {
			retVal.add(new Coordinates(i+1, j));
		}
		
		//bottom neighboors
		if(j == 0) {
			retVal.add(new Coordinates(i, getMaxY()-1));
		} else {
			retVal.add(new Coordinates(i, j-1));
		}
		
		//top neighboors
		if(j == (getMaxY()-1)) {
			retVal.add(new Coordinates(i, 0));
		} else {
			retVal.add(new Coordinates(i, j+1));
		}
		
		//top-left
		if (i == 0 && j < (getMaxY()-1)){
			retVal.add(new Coordinates(getMaxX()-1, j+1));
		} else if(i == 0 && j == (getMaxX()-1)){
			retVal.add(new Coordinates(getMaxX()-1, 0));
		} else if(i != 0 && j < (getMaxY()-1)){
			retVal.add(new Coordinates(i-1, j+1));
		} else if(i != 0 && j == (getMaxY()-1)){
			retVal.add(new Coordinates(i-1, 0));
		}
		
		//top-right
		if (i < (getMaxX()-1) && j < (getMaxY()-1)){
			retVal.add(new Coordinates(i+1, j+1));
		} else if(i < (getMaxX()-1) && j == (getMaxY()-1)){
			retVal.add(new Coordinates(i+1, 0));
		} else if(i == (getMaxX()-1) && j < (getMaxY()-1)){
			retVal.add(new Coordinates(0, j+1));
		} else if(i == (getMaxX()-1) && j == (getMaxY()-1)){
			retVal.add(new Coordinates(0, 0));
		}
		
		//bottom-left
		if (i > 0 && j > 0){
			retVal.add(new Coordinates(i-1, j-1));
		} else if(i == 0 && j > 0){
			retVal.add(new Coordinates(getMaxX()-1, j-1));
		} else if(i == 0 && j == 0){
			retVal.add(new Coordinates(getMaxX()-1, getMaxY()-1));
		} else if(i != 0 && j == 0){
			retVal.add(new Coordinates(i-1, getMaxY()-1));
		}
		
		//bottom-right
		if (i < (getMaxX()-1) && j > 0){
			retVal.add(new Coordinates(i+1, j-1));
		} else if(i < (getMaxX()-1) && j == 0){
			retVal.add(new Coordinates(i+1, getMaxY()-1));
		} else if(i == (getMaxX()-1) && j > 0){
			retVal.add(new Coordinates(0, j-1));
		} else if(i == (getMaxX()-1) && j == 0){
			retVal.add(new Coordinates(0, getMaxY()-1));
		}
		
		return retVal;
	}

}
