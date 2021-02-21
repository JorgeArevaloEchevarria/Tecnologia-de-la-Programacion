package es.ucm.fdi.tp.was;
import es.ucm.fdi.tp.base.model.GameAction;

public class WasAction implements GameAction <WasState, WasAction> {

	//--------------------------Atributos------------------------------
	
	private static final long serialVersionUID = 1L;
	
	private int player;
    private int row;
    private int col;
    private int antrow;
    private int antcol;
    
    //------------------------Constructores----------------------------
    
    public WasAction(int player, int row, int col, int antrow, int antcol) {
        this.player = player;
        this.row = row;
        this.col = col;
        this.antrow = antrow;
        this.antcol = antcol;
    }
    
    //---------------------------Metodos-------------------------------

	@Override
	public WasState applyTo(WasState state) {
		if (player != state.getTurn()) {
            throw new IllegalArgumentException("Not the turn of this player");
        }
		
        // make move
        int[][] board = state.getBoard();
    	board[antrow][antcol] = WasState.EMPTY;
        board[row][col] = this.player;

        // update state
        WasState next;
        if (WasState.isWinner(board, state.getTurn())) next = new WasState(state, board, true, state.getTurn());
        else next = new WasState(state, board, false, -1);
        
        return next;
	}

	@Override
	public int getPlayerNumber() { return this.player; }
	public int getRow() { return this.row; }
	public int getCol() { return this.col; }
	public int getAntRow() { return this.antrow; }
	public int getAntCol() { return this.antcol; }
	
	public String toString() { return "place " + player + " at (" + row + ", " + col + ")"; }
}
