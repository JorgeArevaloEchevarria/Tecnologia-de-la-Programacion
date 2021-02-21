package es.ucm.fdi.tp.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

import es.ucm.fdi.tp.base.Utils;
import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.extra.jboard.JBoard;
import es.ucm.fdi.tp.extra.jcolor.ColorChooser;
import es.ucm.fdi.tp.extra.jcolor.MyTableModel;

public abstract class RectBoardGameView<S extends GameState<S,A>,A extends GameAction<S,A>> extends GameView<S ,A>{

	//--------------------------SerialUID------------------------------
	
	private static final long serialVersionUID = 4739477885758238739L;
	
	//--------------------------Atributos------------------------------
	
	protected boolean enable;
	protected GameViewCtrl<S, A> gViewCtrl;
	protected S state;
	protected JBoard board;
	protected JTextArea textArea;
	protected BorderLayout forma;
	protected GameController<S,A> gameCtrl;
	
	protected ColorChooser miColor;
	protected Map<Integer, Color> misColores;
	protected Iterator<Color> miIterador;

	//----------------------Metodos Abstractos-------------------------
	
	protected abstract int getNumCols();
	protected abstract int getNumRows();
	protected abstract Integer getPosition(int row, int col);
	protected abstract void mouseClicked(int row, int col, int clickCount, int mouseButton);
	protected abstract void keyTyped(int keyCode);
	protected abstract Color getBackground(int row, int col);
	
	//------------------------Constructores----------------------------
	
	public RectBoardGameView(){
		super();
		this.enable = true;
		this.textArea = new JTextArea();
		this.forma = new BorderLayout();
		this.miIterador = Utils.colorsGenerator();
		this.setOpaque(true);
		iniciarTablero();
	}

	//----------------------------Metodos------------------------------

	//----------------Metodos privados---------------------
	
	private void changeColor(int row) {
		miColor.setSelectedColorDialog(misColores.get(row));
		miColor.openDialog();
		if (miColor.getColor() != null) {
			misColores.put(row, miColor.getColor());
			this.board.repaint();
		}
	}

	private Color getPlayerColor(int player) {
		Color clorDado = misColores.get(player);
		if (clorDado == null) 
			clorDado = asigColor(player);
		
		return clorDado;
	}

	private Color asigColor(int player) {
		Color colorss = miIterador.next();
		misColores.put(player, colorss);
		return colorss;
	}
	
	//---------Metodos publicos y protegidos --------------

	protected void iniciarTablero(){
	
		this.board = new JBoard(){	
			
			//------------------SerialUID----------------------
			private static final long serialVersionUID = 8108454021041573992L;

			@Override
			// Llama a keyTyped implementado en la view de cada juego:
			protected void keyTyped(int keyCode) {
				RectBoardGameView.this.keyTyped(keyCode);
			}

			@Override
			// Llama a mouseCLicked implementado en la view de cada juego:
			protected void mouseClicked(int row, int col, int clickCount, int mouseButton) {
				RectBoardGameView.this.mouseClicked(row, col, clickCount, mouseButton);
			}

			@Override
			// Devuelve una forma (circulo) de la clase JBoard
			protected Shape getShape(int player) {
				return Shape.CIRCLE;
			}
		
			@Override
			 // Devuelve un color:
			protected Color getColor(int player) {
				return getPlayerColor(player);
			}

			@Override
			// Llama a getPosition implementado en la view de cada juego:
			protected Integer getPosition(int row, int col) {
				return RectBoardGameView.this.getPosition(row, col);
			}

			@Override
			// Llama a getNumRows implementado en la view de cada juego:
			protected int getNumRows() { 
				return RectBoardGameView.this.getNumRows();
			}

			@Override
			// Llama a getNumCols implementado en la view de cada juego:
			protected int getNumCols() {
				return RectBoardGameView.this.getNumCols();
			}
			
			@Override
			// Devuelve el color del tablero:
			protected  Color getBackground(int row, int col){
				return RectBoardGameView.this.getBackground(row, col);
			}
			
			@Override
			// Modifico el espacio entre tablas:
			protected int getSepPixels() {
				return 1; 
			}
		};
		
		//Añadimos el tablero al panel GameView (hereda de JPanel):
		this.add(this.board, BorderLayout.CENTER);
		
		//Creamos nuevo panel:
		JPanel panelEste = new JPanel(new BorderLayout());
		
		//Definimos el JTextArea:
		JPanel panelTextArea = new JPanel();
		panelTextArea.setBorder(BorderFactory.createTitledBorder("Status Messages"));
		this.textArea.setEditable(false); // Desactiva modo escritura del JTextArea
		this.textArea.setLineWrap(true);  // Activamos salto de linea en el JTextArea
		JScrollPane barraScrollTextArea = new JScrollPane(this.textArea); // Creamos una barra de scroll
		barraScrollTextArea.setPreferredSize(new Dimension(150, 250));
		panelTextArea.add(barraScrollTextArea);
		
		//Definimos el JTable:
		JPanel panelTablePlayers = new JPanel();
		panelTablePlayers.setBorder(BorderFactory.createTitledBorder("Player Information"));
		this.miColor = new ColorChooser(new JFrame(), "Choose Line Color", Color.BLACK);
		this.misColores = new HashMap<Integer, Color>();
		MyTableModel tModel = new MyTableModel();
		tModel.getRowCount();
		JTable infoPlayers = new JTable(tModel){
			
			//------------------SerialUID----------------------
			private static final long serialVersionUID = -972289266120585468L;

			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
				Component comp = super.prepareRenderer(renderer, row , col);

				if (col == 1) comp.setBackground(misColores.get(row));
				else comp.setBackground(Color.BLACK);
		    
				comp.setForeground(Color.WHITE);
				
				return comp;
		   }
		};
		infoPlayers.setToolTipText("Elige la fila para cambiar el color de un jugador");
		infoPlayers.addMouseListener(new MouseAdapter() {		
			@Override
			public void mouseClicked(MouseEvent evt) {
				int row = infoPlayers.rowAtPoint(evt.getPoint());
				int col = infoPlayers.columnAtPoint(evt.getPoint());
				if (row >= 0 && col >= 0) {
					changeColor(row);
				}
			}
		  });
		JScrollPane barraInfoPlayers = new JScrollPane(infoPlayers);
		barraInfoPlayers.setPreferredSize(new Dimension(150, 89));
		panelTablePlayers.add(barraInfoPlayers);
		
		//Añadimos los dos paneles al Panel ESTE
		panelEste.add(panelTextArea, BorderLayout.CENTER);
		panelEste.add(panelTablePlayers, BorderLayout.SOUTH);
		
		//Añadimos el panel este en la zona ESTE al panel principal (RectBoardGameView)
		this.add(panelEste, BorderLayout.EAST);
	}
	
	@Override
	public void enable() { this.enable = true; }

	@Override
	public void disable() { this.enable = false; }

	public void updateCtrl(GameWindow<S, A> gViewCtrl){
		this.gViewCtrl = gViewCtrl;
	}
	
	@Override
	public void update(S state) {
		 if (state.isFinished() && state.getWinner() == this.state.getTurn()) 
			 this.gameCtrl.stop();
		this.state = state;
		this.board.repaint();
	}
	
	@Override
	public void setController(GameController<S, A> gameCtrl) {
		this.gameCtrl = gameCtrl;
	}

	@Override
	public void showInfoMessage(String msg) {
		this.textArea.setText(this.textArea.getText() + 
				System.getProperty("line.separator") + (msg));
	}
}
