 package es.ucm.fdi.tp.view;

import es.ucm.fdi.tp.base.model.*;
import es.ucm.fdi.tp.mvc.GameEvent;
import es.ucm.fdi.tp.mvc.GameObservable;
import es.ucm.fdi.tp.mvc.GameObserver;

public class ConsoleView <S extends GameState<S,A>, A extends GameAction<S,A>> implements GameObserver<S,A>{

	//------------------------Constructores----------------------------
	
	public ConsoleView(GameObservable<S,A> gameTable) {
		gameTable.addObserver(this); // Registramos la vista como observador;
	}
	
	//----------------------------Metodos------------------------------
	
	@Override
	public void notifyEvent(GameEvent<S, A> e) {
		
		switch (e.getType()){
		case Stop:
		case Error:
		case Info: System.out.println(e); break;
		case Start:
		case Change: 
			System.out.println(e);
			System.out.println(e.getState());
			break;
		default: System.out.println("ERROR: evento no encontrado");
		}
	}
}
