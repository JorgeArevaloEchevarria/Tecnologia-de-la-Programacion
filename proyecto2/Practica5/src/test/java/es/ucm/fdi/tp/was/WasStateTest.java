package es.ucm.fdi.tp.was;
import static org.junit.Assert.*;
import org.junit.Test;

public class WasStateTest {
	
	//Metodo privado para volver un tablero vacio:
	private int[][] dameBoard(int[][] board) {
		
		for (int i = 0; i < WasState.DIM; i++) {
            for (int j = 0; j < WasState.DIM; j++) {
            	board[i][j] = WasState.EMPTY;
            }
        }
		return board;
	}

	@Test //Un lobo rodeado resulta en victoria de las ovejas:
	public void test1() {	
		int[][] board = new int[WasState.DIM][WasState.DIM];
		board = dameBoard(board);
			
		//Situacion lobo rodeado de 4 ovejas:
		board[2][1] = 1;
		board[2][3] = 1;
		board[4][1] = 1;
		board[4][3] = 1;
		board[3][2] = 0;
		assertTrue(WasState.isWinner(board, 1));
		
		//Situacion lobo rodeado de 2 ovejas:
		board = dameBoard(board);
		board[6][1] = 1;
		board[4][1] = 1;
		board[5][0] = 0;
		assertTrue(WasState.isWinner(board, 1));
		
		//Situacion lobo rodeado de 1 oveja:
		board = dameBoard(board);
		board[6][1] = 1;
		board[7][0] = 0;
		assertTrue(WasState.isWinner(board, 1));
	}
	
	@Test //Un lobo en una casilla con y = 0 resulta en victoria del lobo:
	public void test2() {
		
		int[][] board = new int[WasState.DIM][WasState.DIM];
		board = dameBoard(board);
		
		for (int i = 1; i < WasState.DIM; i+=2) {
			board[0][i] = 0;
			assertTrue(WasState.isWinner(board, 0));
			board[0][i] = WasState.EMPTY;
		}
	 }
	
	@Test /*Un lobo en su posición inicial sólo tiene 1 acción válida y tras 
			llevarla a cabo en su siguiente turno, tiene 4 acciones válidas:*/
	public void test3() {
		
		WasState stat = new WasState();
		WasAction act = new WasAction(0, 6, 1, 7, 0);
		//Comprueba que solo tiene un movimiento valido ene la posicion inicial:
		assertEquals(1, stat.validActions(0).size()); 
		
		stat = act.applyTo(stat);
		//Comprueba que tras llevar al cabo el primer movimiento valido tiene 4 nuevos movimientos validos:
		assertEquals(4, stat.validActions(0).size());
	}
	
	@Test /*Una oveja en su posición inicial tiene 2 acciones válidas; 
		  y si está en un lateral, tiene 1 acciones válida*/
	public void test4() {
		
		WasState state = new WasState();
		int[][] board = new int[WasState.DIM][WasState.DIM];
		board = dameBoard(board);
	
		//assertEquals(7 ,state.validActions(1).size());
		
		for(int i = 1; i < 6; i+=2) {
			board[0][i] = 1;
			WasState state2 = new WasState(state, board, false, -1);
			assertEquals(2 ,state2.validActions(1).size());
			board[0][i] = WasState.EMPTY;
		}
		
		board[0][7] = 1;
		WasState state2 = new WasState(state, board, false, -1);
		assertEquals(1 ,state2.validActions(1).size());

		//------------------------------------------------------------------
		
		/*
		int colLeft = 0;
		  for (int i = 1; i < 7; i += 2) {
		   board[i][colLeft] = 1;
		   WasState state3 = new WasState(state, board, false, -1);
		   assertEquals(state3.validActions(1).size(), 1);
		   board[i][colLeft] = -1;
		  }
		  
	  int colRight = 7;
		  for (int i = 1; i < 7; i += 2) {
		   board[i][colRight] = 1;
		   WasState state4 = new WasState(state, board, false, -1);
		   assertEquals(state4.validActions(1).size(), 1);
		   board[i][colRight] = -1;
		  }
		*/
	}
}
