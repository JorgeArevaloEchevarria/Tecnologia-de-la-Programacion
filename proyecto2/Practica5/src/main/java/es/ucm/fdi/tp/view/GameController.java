package es.ucm.fdi.tp.view;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GamePlayer;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.mvc.GameTable;

public class GameController<S extends GameState<S,A>, A extends GameAction<S,A>> {

	//--------------------------Atributos------------------------------
	
	private GameTable<S,A> game; // Juego actual;

	//-------------------------Constructor-----------------------------
	
	public GameController(GameTable<S, A> game) { 
		this.game = game;
	}
	
	//----------------------------Metodos------------------------------
	
	//----------------Metodos publicos---------------------
	
	/*public void run() { 
		
		int playerCount = 0;
		this.game.start();	//Comenzamos el juego;
	
		//Agregamos los jugadores:
		for (GamePlayer p : this.players) p.join(playerCount++); 
								
		while (!juegoFinalizado()) {	
			A action = this.players.get(this.devuelveTurno()).requestAction(this.game.getState()); //Generamos una accion;
			this.game.execute(action);	//Aplicamos la accion al juego;
			if (juegoFinalizado()) this.game.stop(); 
		}
	}*/
	
	//Hace un movimiento aleatorio:
	public void makeRandomMove(GamePlayer randPlayer) {
		randPlayer.join(this.game.getState().getTurn());
		A action = randPlayer.requestAction(this.game.getState());
		this.game.execute(action);
	}
	
	//Hace un movimiento inteligente:
	public void makeSmartMove(GamePlayer smartPlayer) {
		smartPlayer.join(this.game.getState().getTurn());
		A action = smartPlayer.requestAction(this.game.getState());
		this.game.execute(action);
	}
	
	//Aplica un movimiento manual:
	public void makeManualMove(A action) {
		this.game.execute(action);
	}
	
	public void stop() {
		this.game.stop();
	}
	
	public void start() {
		this.game.start();
	}
	public int getPlayerId(){
		return this.game.getState().getTurn();
	}
	
	public S getState() {
		return this.game.getState();
	}
}
