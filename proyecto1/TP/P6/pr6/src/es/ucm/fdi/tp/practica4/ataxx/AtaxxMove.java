package es.ucm.fdi.tp.practica4.ataxx;

import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameError;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;


/**
 * A Class representing a move for Ataxx.
 * 
 * <p>
 * Clase para representar un movimiento del juego Ataxx.
 * 
 */
public class AtaxxMove extends GameMove {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The row where is place the piece return by {@link GameMove#getPiece()}.
	 * <p>
	 * Fila en la que esta la ficha por {@link GameMove#getPiece()}.
	 */
	protected int oRow;
	
	/**
	 * The column where is place the piece return by {@link GameMove#getPiece()}.
	 * <p>
	 * Columna en la que esta la ficha por {@link GameMove#getPiece()}.
	 */
	protected int oCol;
	
	/**
	 * The row where to place the piece return by {@link GameMove#getPiece()}.
	 * <p>
	 * Fila en la que se coloca la ficha devuelta por
	 * {@link GameMove#getPiece()}.
	 */
	protected int dRow;
	
	/**
	 * The column where to place the piece return by {@link GameMove#getPiece()}
	 * .
	 * <p>
	 * Columna en la que se coloca la ficha devuelta por
	 * {@link GameMove#getPiece()}.
	 */
	protected int dCol;
	
	/**
	 * This constructor should be used ONLY to get an instance of
	 * {@link AtaxxMove} to generate game moves from strings by calling
	 * {@link #fromString(String)}
	 * 
	 * <p>
	 * Solo se debe usar este constructor para obtener objetos de
	 * {@link AtaxxMove} para generar movimientos a partir de strings usando
	 * el metodo {@link #fromString(String)}
	 * 
	 */
	public AtaxxMove(){
	}
	
	
	/**
	 * Constructs a move for placing a piece of the type referenced by {@code p}
	 * at position ({@code row},{@code col}).
	 * 
	 * <p>
	 * Construye un movimiento para colocar una ficha del tipo referenciado por
	 * {@code p} en la posicion ({@code row},{@code col}).
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
	 */
	public AtaxxMove(int oRow, int oCol,int dRow, int dCol, Piece p) {
		super(p);
		this.oRow = oRow;
		this.oCol = oCol;
		this.dRow = dRow;
		this.dCol = dCol;
	}
	
	@Override
	public void execute(Board board, List<Piece> pieces) {
		
		if(board.getPosition(oRow, oCol) == null)
			throw new GameError("Position (" + oRow + "," + oCol+") is empty!");
		
		else if(!(this.getPiece() == (board.getPosition(oRow, oCol))))
			throw new GameError("You can not move that piece.");
		
		else if(Math.abs(dCol-oCol) > 2 || Math.abs(dRow-oRow) > 2)
			throw new GameError("Invalid movement!");
		else
			if (board.getPosition(dRow, dCol) == null) {
				board.setPosition(dRow, dCol, getPiece());
		
				if(Math.abs(oRow-dRow) > 1 || Math.abs(oCol-dCol) > 1)//No Adjacent position
					board.setPosition(oRow, oCol, null);
				
				for(int i=-1;i<=1; i++)
					for(int j=-1; j<=1; j++)
						if((dRow+i >= 0 && dRow+i < board.getRows()) && (dCol+j >=0 && dCol+j < board.getCols())){
							Piece p = board.getPosition(dRow+i, dCol+j);
							if(p != null && p.getId() != "*")
								board.setPosition(dRow+i, dCol+j, getPiece());
						}
				
			}else {
				throw new GameError("position (" + dRow + "," + dCol + ") is already occupied!");
			}
	}
	
	/**
	 * This move can be constructed from a string of the form "row SPACE col"
	 * where row and col are integers representing a position.
	 * 
	 * <p>
	 * Se puede construir un movimiento desde un string de la forma
	 * "row SPACE col" donde row y col son enteros que representan una casilla.
	 */
	@Override
	public GameMove fromString(Piece p, String str) {
		String[] words = str.split(" ");
		if (words.length != 4) {
			return null;
		}

		try {
			int oRow, oCol,destRow,destCol;
			oRow = Integer.parseInt(words[0]);
			oCol = Integer.parseInt(words[1]);
			destRow = Integer.parseInt(words[2]);
			destCol = Integer.parseInt(words[3]);
			return createMove(oRow, oCol,destRow,destCol,p);
		} catch (NumberFormatException e) {
			return null;
		}
	}
	
	
	/**
	 * Creates a move that is called from {@link #fromString(Piece, String)}.
	 * Separating it from that method allows us to use this class for other
	 * similar games by overriding this method.
	 * 
	 * <p>
	 * Crea un nuevo movimiento con la misma ficha utilizada en el movimiento
	 * actual. Llamado desde {@link #fromString(Piece, String)}; se separa este
	 * metodo del anterior para permitir utilizar esta clase para otros juegos
	 * similares sobrescribiendo este metodo.
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
	 */
	protected GameMove createMove(int oRow, int oCol,int dRow,int dCol, Piece p) {
		return new AtaxxMove(oRow, oCol,dRow,dCol, p);
	}

	@Override
	public String help() {
		return "'Origin row Origin column Destiny row Destiny column', to place a piece at the corresponding position.";
	}

	public String toString() {
		if (getPiece() == null) {
			return help();
		} else {
			return "Place a piece '" + getPiece() + " from ("+ oRow+ ","+ oCol + ")" + "' at (" + dRow + "," + dCol + ")";
		}
	}
}