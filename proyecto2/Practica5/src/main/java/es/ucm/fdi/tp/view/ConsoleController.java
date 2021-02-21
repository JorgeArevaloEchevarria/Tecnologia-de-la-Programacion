package es.ucm.fdi.tp.view;

import java.util.List;
import es.ucm.fdi.tp.base.model.*;
import es.ucm.fdi.tp.mvc.GameTable;


public class ConsoleController<S extends GameState<S,A>, A extends GameAction<S,A>> extends GameController<S,A> implements Runnable {

	//--------------------------Atributos------------------------------
	
	private List<GamePlayer> players; // Lista de jugadores;
	private GameTable<S,A> game;	// Juego actual;
	
	//------------------------Constructores----------------------------
	
	public ConsoleController(List<GamePlayer> players, GameTable<S,A> game){
		
		super(game);
		this.game = game;
		this.players = players;
	}
	
	//----------------------------Metodos------------------------------

	//----------------Metodos privados---------------------
	
	 // Devuelve turno del jugador:
	private int devuelveTurno() { return this.game.getState().getTurn(); }
	
	// Comprueba si el juego ha finalizado
	private boolean juegoFinalizado() { return this.game.getState().isFinished(); }
	
	//----------------Metodos publicos---------------------
	
	@Override
	public void run() { 
		
		int playerCount = 0;
		this.game.start();	//Comenzamos el juego;
	
		//Agregamos los jugadores:
		for (GamePlayer p : this.players) p.join(playerCount++); 
								
		while (!juegoFinalizado()) {	
			A action = this.players.get(this.devuelveTurno()).requestAction(this.game.getState()); //Generamos una accion;
			this.game.execute(action);	//Aplicamos la accion al juego;
			if (juegoFinalizado()) this.game.stop(); 
		}
	}
}
