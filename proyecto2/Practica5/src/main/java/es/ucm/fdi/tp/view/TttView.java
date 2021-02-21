package es.ucm.fdi.tp.view;

import java.awt.Color;

import es.ucm.fdi.tp.ttt.TttAction;
import es.ucm.fdi.tp.ttt.TttState;

public class TttView extends RectBoardGameView<TttState, TttAction>{

	//--------------------------SerialUID------------------------------
	
	private static final long serialVersionUID = 6790133092248491037L;

	//------------------------Constructores----------------------------
	
	public TttView() {
		super();
	}

	//----------------------------Metodos------------------------------
	
	//---------Metodos publicos y protegidos --------------
	
	@Override
	protected int getNumCols() {
		return this.state.getDim();
	}

	@Override
	protected int getNumRows() {
		return this.state.getDim();
	}

	@Override
	protected Integer getPosition(int row, int col) {
		if(this.state.isEmpty(row, col)) return null;
		else return this.state.at(row, col);
	}

	protected Color getBackground(int row, int col) {
		return Color.LIGHT_GRAY;
	}
	
	@Override
	protected void mouseClicked(int row, int col, int clickCount,
			int mouseButton) {
		if(this.enable){
			TttAction action = new TttAction(this.state.getTurn(), row, col);
			if(this.state.isValid(action)) {
				this.gViewCtrl.userActionAvailable(action);
				this.showInfoMessage("Clicked on (" + row + "," + col + ")");
			}
		}
	}

	@Override
	protected void keyTyped(int keyCode) {
	}

}
