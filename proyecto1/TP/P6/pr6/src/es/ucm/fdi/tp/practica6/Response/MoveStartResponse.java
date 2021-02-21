package es.ucm.fdi.tp.practica6.Response;

import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

public class MoveStartResponse implements Response {
	
	private static final long serialVersionUID = 1L;
	private Board board;
	private Piece turn;
	/**
	 * 
	 * @param board
	 * @param turn
	 */
	public MoveStartResponse(Board board, Piece turn){
		
		this.board = board;
		this.turn = turn;
		
	}
	/**
	 * 
	 */
	@Override
	public void run(GameObserver o) {
		o.onMoveStart(board, turn);
	}

}
