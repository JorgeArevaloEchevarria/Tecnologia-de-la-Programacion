package es.ucm.fdi.tp.practica4.ataxx;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.Utils;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.Game.State;
import es.ucm.fdi.tp.basecode.bgame.model.FiniteRectBoard;
import es.ucm.fdi.tp.basecode.bgame.model.GameError;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.GameRules;
import es.ucm.fdi.tp.basecode.bgame.model.Pair;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

/**
 * Rules for Ataxx game.
 * <ul>
 * <li>The game is played on an NxN board (with N>=3) and odd dimension.</li>
 * <li>The number of players is between 2 and 4.</li>
 * <li>The player turn in the given order, each placing a piece on an empty
 * 	   cell. The winner is the one who has more pieces on the board or be the only one with pieces.</li>
 * </ul>
 * 
 * <p>
 * Reglas del juego Ataxx.
 * <ul>
 * <li>El juego se juega en un tablero NxN (con N>=5) y siendo la dimension impar.</li>
 * <li>El numero de jugadores esta entre 2 y 4.</li>
 * <li>Los jugadores juegan en el orden proporcionado, cada uno colocando una
 * ficha en una casilla vacia. El ganador es el que consigua tener mas fichas al acabar la partida,
 * .o llegar a eliminar al resto de jugadores.
 * </li>
 * </ul>
 *
 */
public class AtaxxRules implements GameRules {
	
	// This object is returned by gameOver to indicate that the game is not
		// over. Just to avoid creating it multiple times, etc.
		//
	
	private int dim;
	private int obstacles;
	
	protected final Pair<State, Piece> gameInPlayResult = new Pair<State, Piece>(State.InPlay, null);
	
	public AtaxxRules(int dim,int obstacles){
		//dimension mayor o igual que 5 y siendo impar
		if(dim < 5 || dim%2 != 1 )
			throw new GameError("Dimension must be at least 5 and an odd number: " + dim);
		else
			this.dim = dim;
			this.obstacles = obstacles;
	}
	
	@Override
	public String gameDesc() {
		return "Ataxx " + dim + "x" + dim;
	}

	@Override
	public Board createBoard(List<Piece> pieces) {
		int cont = 0;
		Board board = new FiniteRectBoard(dim, dim);
		while(cont < pieces.size()){
			//contador de la posicion de los jugadores
			if(cont == 0){
				board.setPosition(0, 0,pieces.get(cont));
				board.setPosition(dim-1, dim-1,pieces.get(cont));
			}else if(cont == 1){
				board.setPosition(dim-1, 0, pieces.get(cont));
				board.setPosition(0, dim-1, pieces.get(cont));
			}else if(cont == 2){
				board.setPosition((dim-1)/2, 0, pieces.get(cont));
				board.setPosition((dim-1)/2,dim-1, pieces.get(cont));
			}else if(cont == 3){
				board.setPosition(0, (dim-1)/2, pieces.get(cont));
				board.setPosition(dim-1, (dim-1)/2, pieces.get(cont));
			}
			cont++;
		}
		//inicializa obstaculos
		iniObstacles(board);
		
		return board;
	}

	
	@Override//jugador inicial
	public Piece initialPlayer(Board board, List<Piece> pieces) {
		return pieces.get(0);
	}

	
	@Override
	public int minPlayers() {
		return 2;
	}

	
	@Override
	public int maxPlayers() {
		return 4;
	}
	
	private int contPieces(Board board,Piece p){
		int cont = 0;
		
		for(int i= 0;i<board.getRows();i++)
			for(int j= 0;j<board.getCols();j++)
				if(board.getPosition(i, j).equals(p))
					cont++;
		
		return cont;
	}
	@Override
	public Pair<State, Piece> updateState(Board board, List<Piece> pieces,
			Piece turn){
		
		int cont = 0;
		int nPieces = 0,aux;
		Piece p = null;
		boolean draw=false;
		
		if(board.isFull()){
			while(cont < pieces.size()){
				aux = contPieces(board,pieces.get(cont));
				if(aux > nPieces){
					nPieces = aux;
					p = pieces.get(cont);
					draw = false;
				}else if(aux == nPieces)
					draw = true;
				
				cont++;
			}		
			if(!draw)
				return new Pair<State,Piece>(State.Won,p);
			else
				return new Pair<State,Piece>(State.Draw,null);
		
		}else{
			int i = -1,j=0;
			while(p == null && i< board.getRows()){
				i++;
				j=0;
				while(p == null && j<board.getCols()){
					if(board.getPosition(i, j) != null && board.getPosition(i, j).getId() != "*")
						p = board.getPosition(i, j);
					j++;
				}
			}
			
			Piece pAux = p;
			boolean morePieces = false;
			
			while(i<board.getRows()&& !morePieces){
				while(j<board.getCols() && !morePieces){
					p = board.getPosition(i, j);
					if(pAux != p && p!= null && p.getId() != "*")
						morePieces = true;
					j++;
				}
				j=0;
				i++;
			}
			
			if(!morePieces)
				return new Pair<State,Piece>(State.Won,pAux);
			else if(nextPlayer(board,pieces,turn) == turn)
				return new Pair<State,Piece>(State.Won,turn);
		}
			
		return gameInPlayResult;
	}
	
	public void iniObstacles(Board board){
		int n = 0;
		while(n<obstacles){
			int randomRow = Utils.randomInt(board.getRows());
			int randomCol = Utils.randomInt(board.getCols());
			
			if(board.getPosition(randomRow,randomCol) == null){
				board.setPosition(randomRow, randomCol,new Piece("*"));
				n++;
			}
		}
	}
	
	@Override
	public Piece nextPlayer(Board board, List<Piece> playersPieces, Piece turn) {
		List<Piece> pieces = playersPieces;
		int i = pieces.indexOf(turn);
		
		List<GameMove> availableMoves = validMoves(board,playersPieces,pieces.get((i + 1) % pieces.size()));
		
		while(availableMoves.isEmpty()){
			i++;
			availableMoves = validMoves(board,playersPieces,pieces.get((i + 1) % pieces.size()));
		}
			return pieces.get((i + 1) % pieces.size());
	}

	
	@Override
	
	public double evaluate(Board board, List<Piece> pieces, Piece turn) {
		return 0;
	}

	@Override
	public List<GameMove> validMoves(Board board, List<Piece> playersPieces,
			Piece turn) {
		
		
		List<GameMove> moves = new ArrayList<GameMove>();
		
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getCols(); j++) {
				Piece p = board.getPosition(i, j);
				if (p == turn) {
					for (int a = i-2; a <= i+2; a++) {
						for (int b = j-2; b <= j+2; b++)
							if((a >= 0 && a < board.getRows()) && (b >=0 && b < board.getCols()))
								if(board.getPosition(a,b) == null )
									moves.add(new AtaxxMove(i, j, a, b, turn));
					}
				}
			}
		}
		
		return moves;
	}

}
