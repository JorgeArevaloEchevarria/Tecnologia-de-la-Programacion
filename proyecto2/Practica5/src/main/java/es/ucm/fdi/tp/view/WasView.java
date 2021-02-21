package es.ucm.fdi.tp.view;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import es.ucm.fdi.tp.was.WasAction;
import es.ucm.fdi.tp.was.WasState;

public class WasView extends RectBoardGameView<WasState, WasAction> {

	// --------------------------SerialUID------------------------------

	private static final long serialVersionUID = 3159916063454570350L;

	// --------------------------Atributos------------------------------

	private boolean firstClicked;
	private int antrow, antcol;

	// ------------------------Constructores----------------------------

	public WasView() {
		super();
		this.firstClicked = false;
	}

	// ----------------------------Metodos------------------------------

	// ---------Metodos publicos y protegidos --------------

	@Override
	// Devuelve el numero de columnas:
	protected int getNumCols() {
		return this.state.getDim();
	}

	@Override
	// Devuelve el numero de filas:
	protected int getNumRows() {
		return this.state.getDim();
	}

	@Override
	// Devuelve la posicion:
	protected Integer getPosition(int row, int col) {
		if (this.state.isEmpty(row, col)) return null;
		else return this.state.at(row, col);
	}

	protected Color getBackground(int row, int col) {
		return (row + col) % 2 == 0 ? Color.LIGHT_GRAY : Color.BLACK;
	}

	@Override
	protected void mouseClicked(int row, int col, int clickCount, int mouseButton) {
		if(this.enable){
			if (!this.state.isFinished()) {
				// Este es el primer click:
				if (!firstClicked && mouseButton == 1 && this.state.at(row, col) == this.state.getTurn() && this.enable) {
					this.antrow = row;
					this.antcol = col;
					this.firstClicked = true;
					this.showInfoMessage("Clicked on (" + row + "," + col + ")");
				}
	
				// Si es el segundo click:
				else {
					// Devuelve un array de posiciones validas:
					ArrayList<WasAction> accionesVal = (ArrayList<WasAction>) this.state
							.validActions(this.gameCtrl.getPlayerId());
					WasAction act = new WasAction(this.gameCtrl.getPlayerId(), row, col, this.antrow, this.antcol);
	
					for (WasAction action : accionesVal) {
						if (action.getCol() == col && action.getRow() == row && action.getAntCol() == this.antcol
								&& action.getAntRow() == this.antrow) {
							this.gViewCtrl.userActionAvailable(act);
							this.firstClicked = false;
						}
					}
				}
			}
		}
	}

	@Override
	protected void keyTyped(int keyCode) {
		if (firstClicked && (keyCode + 27) == KeyEvent.VK_ESCAPE) {
			this.showInfoMessage("Click canceled (" + this.antrow + "," + this.antcol + ")");
			this.firstClicked = false;
		}
	}
}
