package es.ucm.fdi.tp.was;
import java.util.ArrayList;
import java.util.List;
import es.ucm.fdi.tp.base.model.GameState;

@SuppressWarnings("serial")
public class WasState extends GameState<WasState, WasAction> {
	
	//--------------------------Atributos------------------------------
	
	private final int turn;
    private final boolean finished;
    private final int[][] board;
    private final int winner;
    final static int DIM = 8;
	final static int EMPTY = -1; 
	
    //------------------------Constructores----------------------------
  	
	public WasState() {
		super(2);
	
        board = new int[DIM][DIM];
        
        for (int i=0; i<DIM; i++) {
            for (int j = 0; j < DIM; j++) {
            	board[i][j] = EMPTY;
            	if(j < 4) board[0][2 * j + 1] = 1; //ovejas
            }
        }
   
        board[7][0] = 0; //lobo
        
        this.turn = 0;
        this.winner = -1;
        this.finished = false;
	}

	public WasState(WasState state, int[][] board2, boolean finished, int winner) {
		super(2);
        this.board = board2;
        this.turn = (state.turn + 1) % 2;
        this.finished = finished;
        this.winner = winner;
	}
	
	//---------------------------Metodos-------------------------------
	
	//Comprueba si una casilla esta en el tablero:
	private static boolean onTheBoard(int fil, int col) {
		return (fil >= 0 && fil <= 7 && col >= 0 && col <= 7);
	}
	
	@Override
	public List<WasAction> validActions(int playerNumber) {
		ArrayList<WasAction> valid = new ArrayList<>();
		int sheepRow = 0, sheepCol = 0;
        if (finished) {
            return valid;
        }
        
    	for (int i = 0; i < DIM; i++) {
    		for (int j = 0; j < DIM; j++) {
    			if(at(i, j) == playerNumber) {
    				if(onTheBoard(i+1, j-1) && board[i+1][j-1] == EMPTY) valid.add(new WasAction(playerNumber, i+1, j-1, i, j));
					if(onTheBoard(i+1, j+1) && board[i+1][j+1] == EMPTY) valid.add(new WasAction(playerNumber, i+1, j+1, i, j));
					
					if(playerNumber == 0) {
						if(onTheBoard(i-1, j-1) && board[i-1][j-1] == EMPTY) valid.add(new WasAction(playerNumber, i-1, j-1, i, j));
						if(onTheBoard(i-1, j+1) && board[i-1][j+1] == EMPTY) valid.add(new WasAction(playerNumber, i-1, j+1, i, j));
					}
					
					if(playerNumber == 1) {
						sheepRow = i;
						sheepCol = j;
					}
    			}
    		}
    	}
    	
    	if(playerNumber == 1 && valid.isEmpty()) valid.add(new WasAction(playerNumber, sheepRow, sheepCol, sheepRow, sheepCol));

        return valid;
	}
	
	public static boolean isWinner(int[][] board2, int turn2) {
		boolean ok = false;
		
		if(turn2 == 0) {	
			for(int i = 1; i < DIM; i+=2) {
				if(board2[0][i] == 0) ok = true;
			}
		}
		
		else if(turn2 == 1) {
			for(int i = 0; i < DIM; i++) {
				for(int j = 0; j < DIM; j++) {
					if((board2[i][j] == 0) && 
						(!onTheBoard(i+1, j-1) || board2[i+1][j-1] != EMPTY) &&
						(!onTheBoard(i+1, j+1) || board2[i+1][j+1] != EMPTY) &&
						(!onTheBoard(i-1, j-1) || board2[i-1][j-1] != EMPTY) &&
						(!onTheBoard(i-1, j+1) || board2[i-1][j+1] != EMPTY)) ok = true;			
				}
			}
		}
		return ok;
	}
 
	public int[][] getBoard() {
		int[][] copy = new int[board.length][];
        for (int i=0; i<board.length; i++) copy[i] = board[i].clone();
        return copy;
	}
	
	public int at(int row, int col) { return board[row][col]; }
	
	@Override
	public boolean isFinished() { return finished; }

	@Override
	public int getWinner() { return winner; }
	
	@Override
	public int getTurn() { return turn; }
	
	public int getDim() { return DIM; }
	
	public boolean isEmpty(int row, int col) {
		if(this.board[row][col] == EMPTY) return true;
		else return false;
	}
	
	 public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<board.length; i++) {
            sb.append("|");
            for (int j=0; j<board.length; j++) {
                sb.append(board[i][j] == EMPTY ? "   |" : board[i][j] == 1 ? " O |" : " X |");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
