package it.game.of.life.client;

public class State {
	private boolean stato;
	
	public State(boolean state) {
		this.stato = state;
	}
	
	public boolean getStato() {
		return this.stato;
	}
	
	public boolean isAlive() {
		return stato;
	}
	
	public void setAlive(boolean alive) {
		this.stato=alive;
	}
}
