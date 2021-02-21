package es.ucm.fdi.tp.practica5.ataxx;

import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.GameRules;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

@SuppressWarnings("serial")
public class AtaxxSwingPlayer extends Player{
	
	private int oRow;
	private int oCol;
	private int dRow;
	private int dCol;

	@Override
	public GameMove requestMove(Piece p, Board board, List<Piece> pieces, GameRules rules) {
		return createMove(oRow,oCol,dRow,dCol, p);
	}

	protected GameMove createMove(int oRow, int oCol,int dRow, int dCol, Piece p){
		return new AtaxxMove(oRow, oCol, dRow, dCol, p);
	}

	public void setMoveValue(int oRow, int oCol, int dRow, int dCol) {
		this.oRow=oRow;
		this.oCol=oCol;
		this.dRow=dRow;
		this.dCol=dCol;
		}
}

