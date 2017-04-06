package it.game.of.life.client;

public class BaseCell {
	private Coordinates coord;
	private State state;
	private State stateTmp;
	
	public BaseCell(State state) {
		this.coord = new Coordinates(0, 0);
		this.state = state;
		this.stateTmp = state;
	}
	
	public BaseCell(Coordinates coord) {
		this.coord = coord;
		this.state = new State(false);//Default state
		this.stateTmp = new State(false);
	}
	
	public BaseCell(Coordinates coord, State state) {
		this.coord = coord;
		this.state = state;
		this.stateTmp = state;
	}
	
	public State getState() {
		return state;
	}
	
	public void setState(State state) {
		this.stateTmp = state;
	}
	
	public void updateState() {
		this.state = stateTmp;
	}
	
	public Coordinates getCoordinates() {
		return this.coord;
	}
}
