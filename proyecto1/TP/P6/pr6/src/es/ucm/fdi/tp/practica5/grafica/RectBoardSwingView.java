package es.ucm.fdi.tp.practica5.grafica;

import java.awt.Color;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica5.JBoard.JBoard;
import es.ucm.fdi.tp.practica5.JBoard.JBoard.Shapes;


@SuppressWarnings("serial")
public abstract class RectBoardSwingView extends SwingView {
	
	private JBoard board;
	public RectBoardSwingView(Observable<GameObserver> g, Controller c, Piece localPiece, Player randomPlayer,
			Player autoPlayer) {
		
		super(g, c, localPiece, randomPlayer, autoPlayer);
		
	}

	@Override
	protected void initBoardGui() {
		
		board = new JBoard(obs){
			
			@Override
			protected void mouseClicked(int row, int col, int clickCount, int mouseButton){
				handleMouseClick(row, col , clickCount, mouseButton);
			}
			
			@Override
			protected Color getPieceColor(Piece p){
				return RectBoardSwingView.this.getPieceColor(p);
			}
			
			@Override
			protected Shapes getPieceShape(Piece p){
				return RectBoardSwingView.this.getPieceShape(p);
			}
				
		};
		setBoardArea(board);
		
	}

	public Shapes getPieceShape(Piece p){
		if (p.getId().equals("*"))
			return Shapes.RECTANGLE;
		else
			return Shapes.CIRCLE;
	}
	
	public Color getPieceColor(Piece p){
		Color c = pieceColors.get(p);
		
		if(c==null){
			c=colorsIter.next();
			pieceColors.put(p,c);
		}
		return c;
	}
	
	protected abstract void handleMouseClick(int row, int col, int clickCount, int mouseButton);

}
