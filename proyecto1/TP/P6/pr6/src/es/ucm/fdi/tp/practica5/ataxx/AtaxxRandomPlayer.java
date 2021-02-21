package es.ucm.fdi.tp.practica5.ataxx;

import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.Utils;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameError;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.GameRules;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

/**
 * A random player for Ataxx.
 * <p>
 * Un jugador aleatorio para Ataxx.
 *
 */

public class AtaxxRandomPlayer extends Player {


	private static final long serialVersionUID = 1L;

	@Override
	public GameMove requestMove(Piece p, Board board, List<Piece> pieces,
			GameRules rules) {
		
		if (board.isFull()) 
			throw new GameError("The board is full, cannot make a random move!!");

		List<GameMove> availableMoves = rules.validMoves(board, pieces, p);
		
		return availableMoves.get(Utils.randomInt(availableMoves.size()));
				
		}

	
	/**
	 * Creates the actual move to be returned by the player. Separating this
	 * method from {@link #requestMove(Piece, Board, List, GameRules)} allows us
	 * to reuse it for other similar games by overriding this method.
	 * 
	 * <p>
	 * Crea el movimiento concreto que sera devuelto por el jugador. Se separa
	 * este metodo de {@link #requestMove(Piece, Board, List, GameRules)} para
	 * permitir la reutilizacion de esta clase en otros juegos similares,
	 * sobrescribiendo este metodo.
	 * 
	 * @param oRow
	 * 			  Number of origin row.
	 *            <p>
	 *            Numero de la fila origen.
	 * @param oCol
	 * 			  Number of origin column.
	 *            <p>
	 *            Numero de la columna origen.
	 * @param dRow
	 * 			  Number of destiny row.
	 * 			  <p>
	 * 			  Numero de la fila destino. 	
	 * @param dCol
	 * 			  Number of destiny columns.
	 * 			  <p>
	 * 			  Numero de la columna destino.
	 * @param p
	 *            A piece to be place at ({@code row},{@code col}).
	 *            <p>
	 *            Ficha a colocar en ({@code row},{@code col}).
	 * @return
	 */
	protected GameMove createMove(int oRow, int oCol,int dRow,int dCol, Piece p) {
		return new AtaxxMove(oRow, oCol,dRow,dCol, p);
	}
}
