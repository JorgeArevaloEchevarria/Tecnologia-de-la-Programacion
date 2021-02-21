package es.ucm.fdi.tp.view;

import javax.swing.JPanel;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;

public abstract class GameView<S extends GameState<S,A>,A extends GameAction<S,A>> extends JPanel {

	//--------------------------SerialUID------------------------------
	
	private static final long serialVersionUID = 2503519518703484819L;

	//----------------------Metodos Abstractos-------------------------
	
	public abstract void enable();
	public abstract void disable();
	public abstract void update(S state);
	public abstract void updateCtrl(GameWindow<S, A> gViewCtrl);
	public abstract void showInfoMessage(String msg);
	public abstract void setController(GameController<S,A> gameCtrl);
	
	//------------------------Constructores----------------------------
	
	public GameView() {
		super();
	}
}