package es.ucm.fdi.tp.practica5.JBoard;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.Game.State;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

@SuppressWarnings("serial")
public class JBoard extends JComponent implements GameObserver {
	
	private Board board; // Nuestro tablero, una matriz de piezas

	
	private int _CELL_HEIGHT; // Alto de la casilla del tablero
	private int _CELL_WIDTH; //Ancho de la casilla del tablero
	
	
	private HashMap< Piece, Color> pieceColors = new HashMap<>();
	
	Iterator <Color> colorsIter;
	
	public enum Shapes{
		CIRCLE, RECTANGLE;
	}
	
	
	
	
	public JBoard (final Observable<GameObserver> game){
		initGUI();
		
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				game.addObserver(JBoard.this); // Registramos la JComponente como Observador del Modelo
			}
		});
	}
	
	private void initGUI(){
		addMouseListener(new MouseListener(){
			//Mouse Listener captura los eventos del ratón
			
			@Override
			public void mouseClicked(MouseEvent e){
				int col = (e.getX() / _CELL_WIDTH);
				int row = (e.getY() / _CELL_HEIGHT);
				
				int mouseButton = 0;
				
				
				if (SwingUtilities.isLeftMouseButton(e)){ // Botón izquierdo
					mouseButton = 1;
				}
				else if(SwingUtilities.isMiddleMouseButton(e)){ // Botón central
					mouseButton = 2;
					
				}
				else if(SwingUtilities.isRightMouseButton(e)){ // Botón derecho del ratón
					mouseButton = 3;
					
				}
				
				if (mouseButton == 0){
					return; // Unknown button
				}
				
				JBoard.this.mouseClicked(row, col, e.getClickCount(), mouseButton);
			}
			
			
			@Override
			public void mouseReleased(MouseEvent e) {
				//System.out.println("Mouse Released: " + "(" + e.getX() + "," + e.getY() + ")");
			}

			@Override
			public void mousePressed(MouseEvent e) {
				//System.out.println("Mouse Pressed: " + "(" + e.getX() + "," + e.getY() + ")");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				//System.out.println("Mouse Exited Component: " + "(" + e.getX() + "," + e.getY() + ")");
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				//System.out.println("Mouse Entered Component: " + "(" + e.getX() + "," + e.getY() + ")");
			}
		});
		
		
		
		this.setPreferredSize(new Dimension(400, 400));
		repaint();
	}
	
	
	protected void mouseClicked(int row, int col, int clickCount, int mouseButton){}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g); // SIEMPRE llamar a esto antes de dibujar
		fillBoard(g); // En fillBoard ponemos el codigo que dibuja el tablero usando Graphics
	}
	
	
	
	private void fillBoard(Graphics g) {
		
		_CELL_WIDTH = this.getWidth() / board.getRows();
		_CELL_HEIGHT = this.getHeight() / board.getCols();
		for(int i = 0; i < board.getRows(); i++){
			for(int j = 0; j < board.getCols(); j++){
				drawCell(i,j, g); // Pintamos cada celda
			}
		}
		
	}
	
	private void drawCell(int row, int col, Graphics g) {

		int x = col * _CELL_WIDTH;
		int y = row * _CELL_HEIGHT;

		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(x + 2, y + 2, _CELL_WIDTH - 4, _CELL_HEIGHT - 4);

		Piece p = board.getPosition(row, col);
		
		
		if (p != null){
		switch(getPieceShape(p)){
		case RECTANGLE:		
			//Dibuja los obstaculos
			g.setColor(Color.RED);//Interior
			g.fillRect(x + 4, y + 4, _CELL_WIDTH - 8, _CELL_HEIGHT - 8);
	
			g.setColor(Color.YELLOW);//Borde
			g.drawRect(x + 4, y + 4, _CELL_WIDTH - 8, _CELL_HEIGHT - 8);
			
			break;
		case CIRCLE:
			Color c = getPieceColor(p);
				
			g.setColor(c);
			g.fillOval(x + 4, y + 4, _CELL_WIDTH - 8, _CELL_HEIGHT - 8);
	
			g.setColor(Color.black);
			g.drawOval(x + 4, y + 4, _CELL_WIDTH - 8, _CELL_HEIGHT - 8);
			
			break;
		}
		}

	}

	protected Shapes getPieceShape(Piece position){
			return this.getPieceShape(position);
		
		

	}
	
	
	protected Color getPieceColor(Piece position) {
		return this.pieceColors.get(position);
	}

	
	
	
	/** GAME OBSERVER CALLBACKS **/
	
	@Override
	public void onGameStart(Board board, String gameDesc, List<Piece> pieces,
			Piece turn) {
		
		this.board = board; // Para obtner el estado inicial del tablero
		
		repaint(); // Para redibujar de nuevo la componente visual con los cambios
		
	}

	@Override
	public void onGameOver(Board board, State state, Piece winner) {
		this.board = board; // Obtenemos el estado final del tablero
		
		repaint();
		
	}

	@Override
	public void onMoveStart(Board board, Piece turn) {
		this.board = board;
		
		repaint();
		
	}

	@Override
	public void onMoveEnd(Board board, Piece turn, boolean success) {
		this.board = board;
		
		repaint();
		
	}

	@Override
	public void onChangeTurn(Board board, Piece turn) {
		this.board = board;
		
		repaint();
	}

	@Override
	public void onError(String msg) {
		
	}

}