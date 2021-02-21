package es.ucm.fdi.tp.mvc;

import java.util.ArrayList;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameError;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.mvc.GameEvent.EventType;

/**
 * An event-driven game engine.
 * Keeps a list of players and a state, and notifies observers
 * of any changes to the game.
 */
public class GameTable<S extends GameState<S, A>, A extends GameAction<S, A>> implements GameObservable<S, A> {

	//--------------------------Atributos------------------------------
	
	private S initState;
    private S currentState;
    private ArrayList<GameObserver<S, A>> observers;
    private boolean startGame;
    
    //------------------------Constructores----------------------------
    
    public GameTable(S initState) {
        this.initState = initState; //Guardamos el estado inicial (para poder reiniciar)
        this.currentState = initState; //Inicializamos el estado actual
        this.startGame = false;	
        this.observers = new ArrayList<GameObserver<S, A>>();
    }
    
    //----------------------------Metodos------------------------------
    
    //----------------Metodos privados---------------------
    
    //Recorre el array y notifica cada observador:
    private void notifyObservers(GameEvent <S,A> e){
    	for(int i = 0; i < this.observers.size(); i++){
    		this.observers.get(i).notifyEvent(e);
    	}
    }
    
    //----------------Metodos publicos---------------------
      
    public void start() {
    	this.currentState = this.initState; //Reiniciamos el estado actual;
    	
    	GameEvent<S,A> start = new GameEvent<S, A>(EventType.Start, null, this.currentState, null, "The game start");
    	this.startGame = true;
    	notifyObservers(start);
    }
    
    public void stop() {
    	if(this.startGame){
    		GameEvent<S,A> stop = new GameEvent<S, A>(EventType.Stop, null, this.currentState, null, "The game has ended");
    		this.startGame = false;	
    		notifyObservers(stop);
    		
    		if(this.currentState.getWinner() == -1){
    			GameEvent<S,A> draw = new GameEvent<S, A>(EventType.Info, null, this.currentState, null, "Draw");
    			notifyObservers(draw);
    		}else{
    			GameEvent<S,A> win = new GameEvent<S, A>(EventType.Info, null, this.currentState, null, 
    					"player " + (this.currentState.getWinner() + 1 )+ " won!");
    			notifyObservers(win);
    		}
    	}
    }
    
    public void execute(A action) {
    	
    	S newState = action.applyTo(this.currentState);
    	
    	if(!newState.equals(this.currentState)){
    		this.currentState = newState;
    		GameEvent<S,A> execute = new GameEvent<S, A>(EventType.Change, action, this.currentState, null, 
    				"The state has changed for player " + (currentState.getTurn() + 1));
    		notifyObservers(execute);
    	}else{
    		GameError error = new GameError("Error Execute");
    		GameEvent<S,A> execute = new GameEvent<S, A>(EventType.Error, action, this.currentState, error, "Error the state isn't aplicated");
    		notifyObservers(execute);
    		throw error;
    	}	
    }
    
    public S getState() {	
    	return this.currentState;
    }

    public void addObserver(GameObserver<S, A> o) {
       this.observers.add(o);
    }
    
    public void removeObserver(GameObserver<S, A> o) {
       this.observers.remove(o);
    }
}