package es.ucm.fdi.tp.view;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;

public interface GameViewCtrl<S extends GameState<S,A>,A extends GameAction<S,A>> {
	
	//----------------------Metodos Abstractos-------------------------
	
	public void userActionAvailable(A p);
	public void randomActionButtonPressed();
	public void smartActionButtonPressed();
	public void restartButtonPressed();
	public void quitButtonPressed();
	public void playerModeSelected(GameWindow.PlayerMode pMode);
}