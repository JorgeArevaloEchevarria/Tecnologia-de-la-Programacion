package es.ucm.fdi.tp.practica5.grafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import es.ucm.fdi.tp.basecode.bgame.Utils;
import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.Game.State;
import es.ucm.fdi.tp.basecode.bgame.model.GameError;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;



public abstract class SwingView extends JFrame implements GameObserver {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Controller ctrl;
	protected Observable<GameObserver> obs;
	
	private Piece localPiece;
	protected Piece turn;
	private List<Piece> pieces;
	
	private Board board;

	protected Map<Piece, Color> pieceColors;
	private Map<Piece, PlayerMode> playerTypes;
	
	Iterator<Color> colorsIter;
	
	private JComboBox<PlayerMode> modesCB;
	private JComboBox<Piece> playerModesCB;
	private JComboBox<Piece> colorsCB;
	
	private PlayerInfoTableModel pInfoTable;
	
	private Player aiPlayer;
	private Player randomPlayer;
	
	private boolean inPlay;
	private boolean inMovExec;
	

	private JPanel rightPanel;
	private JPanel boardPanel;
	private JTextArea status;
	
	private JButton quit; 
	private JButton restart;
	private JButton rand;
	private JButton intelligent; 
	
	enum PlayerMode {
		MANUAL("Manual"), RANDOM("Random"), AI("Inteligent");
		private String name;
		PlayerMode(String name) {
			this.name = name;
		}
		@Override
		public String toString() {
			return name;
		}
	}
	
	public SwingView(Observable<GameObserver>g,Controller c, Piece localPiece, Player randPlayer, Player aiPlayer){
		  
		  this.ctrl = c;
		  this.obs = g;
		  this.localPiece = localPiece;
		  this.pieceColors = new HashMap<Piece, Color>();
		  this.playerTypes = new HashMap<Piece, PlayerMode>();
		  this.randomPlayer = randPlayer;
		  this.aiPlayer = aiPlayer;
		  this.inPlay = false;
		  this.colorsIter = Utils.colorsGenerator();

		  SwingUtilities.invokeLater(new Runnable() {
			  @Override
			   public void run() {
			    obs.addObserver(SwingView.this);
			   }
		  });
		  initGUI();
	}

	private void initGUI(){
		
		JPanel main = new JPanel(new BorderLayout());
		
		this.setContentPane(main);
		
		main.setOpaque(true);
		
		boardPanel = new JPanel(new BorderLayout());
		
		main.add(boardPanel, BorderLayout.CENTER);
		
		initBoardGui();
		
		rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel,BoxLayout.Y_AXIS));
		
		main.add(rightPanel,BorderLayout.LINE_END);
		
		iniRightPanel();
		

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setResizable(false);	
		this.setVisible(true);
	}
	
	@SuppressWarnings("serial")
	class PlayerInfoTableModel extends DefaultTableModel {
		private String[] colNames;
		
		PlayerInfoTableModel() {
			this.colNames = new String[] { "Player", "Mode", "#Pieces" };
		}
		
		public String getColumnName(int col) {
			return colNames[col];
		}
		public int getColumnCount() {
			return colNames.length;
		}
		public int getRowCount() {
			return pieces == null ? 0 : pieces.size();
		}
		public Object getValueAt(int rowIndex, int columnIndex) {
			if (pieces == null) {
				return null;
			}
			Piece p = pieces.get(rowIndex);
			if (columnIndex == 0) {
				return p;
			} else if (columnIndex == 1) {
				return playerTypes.get(p);
			} else {
				return getCountPieces(p);
			}
		}
		public void refresh() {
			fireTableDataChanged();
		}
	};
	
	protected int getCountPieces(Piece p){
		int cont = 0;
		for(int i = 0; i < board.getRows(); i++)
            for(int j = 0; j < board.getCols(); j++)
                if (p.equals(board.getPosition(i,j)))
                    cont ++;
        
        return cont;
	}

	final protected Piece getTurn(){return turn;}
	final protected Board getBoard(){return board;}
	final protected List<Piece> getPieces() {return  pieces;}
	protected Color getPieceColor(Piece p){
		Color c = pieceColors.get(p); 
		if(c==null){
			c = colorsIter.next();
			pieceColors.put(p,c);
	}

	return c;}
	final protected Color setPieceColor(Piece p,Color c){
		return pieceColors.put(p,c); 
	}
	final protected void setBoardArea(JComponent c){boardPanel.add(c, BorderLayout.CENTER);}
	final protected void addContentToStatus(String msg) {
		status.append("* " + msg + "\n");
	}

	final protected void decideMakeManualMove(Player manualPlayer){
		if(inMovExec || !inPlay)
			return;
		
		if(localPiece != null && !localPiece.equals(turn))
			return;
		
		if(playerTypes.get(turn) != PlayerMode.MANUAL)
			return;
		
		ctrlMove(manualPlayer);
	}
	
	private void decideMakeAutomaticMove(){
		if (inMovExec || !inPlay){
			return;
		}
		if (localPiece != null && !localPiece.equals(turn)){
			return;
		}
		
		switch (playerTypes.get(turn)){
		case AI:
			ctrlMove(aiPlayer);
			break;
		case RANDOM:
			ctrlMove(randomPlayer);
			break;
		default :
			break;
		}
		
	}

	protected abstract void initBoardGui();
	protected abstract void activateBoard();
	protected abstract void deActivateBoard();

	
	private void ctrlMove(final Player p){
		disableView(); 
		
		SwingUtilities.invokeLater(new Runnable(){
			
			@Override
			public void run(){
				try{
					ctrl.makeMove(p);
				}catch(GameError e){
						
				}
			}
		});
	}

	//GameObserver methods
	public void onGameOver(final Board board, final State state, final Piece winner) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				handleGameOver(board, state, winner);
			}
		});
	}

	public void onChangeTurn(Board board, final Piece turn) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				handleTurnChange(turn);
			}
		});
	}

	public void onGameStart(final Board board, final String gameDesc, final List<Piece> pieces, final Piece turn) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() { 
				handleGameStart(board,gameDesc,pieces,turn);
			}
		});
	}

	public void onMoveStart(Board board, Piece turn){
		
		if(this.turn == turn)
			inMovExec = true;

	}

	public void onMoveEnd(Board board, Piece turn,boolean achieve){
		
		if(this.turn == turn)
			inMovExec = false;
		
		if(!achieve)
			handleTurnChange(turn);
	}
	
	public void onError(String msg){
		 if(!inPlay || localPiece == null || turn.equals(localPiece))
		   JOptionPane.showMessageDialog(new JFrame(), msg, "Error", JOptionPane.ERROR_MESSAGE);
	}

	private void handleGameOver(Board board, State state, Piece winner) {
		
		this.board=board;
		this.turn = winner;
		this.inPlay=false;
		
		disableView();
		
		addContentToStatus("Game Over: " + turn);
		
		if(state == State.Draw)
			addContentToStatus("Draw");
		else if(state == State.Won)
			addContentToStatus("The winner is: " + turn);		
	}
	
	private void handleTurnChange(Piece turn) {
		
		this.turn = turn;
		
		addContentToStatus("Turn for " + (turn.equals(localPiece) ? "You (" + turn + ")" : turn));
		
		if(localPiece == null || localPiece.equals(turn) && playerTypes.get(turn) == PlayerMode.MANUAL)
			enableView();
		else
			disableView();
		

		decideMakeAutomaticMove();
		
	}

	private void handleGameStart(Board board, String gameDesc, List<Piece> pieces, Piece turn) {
		this.setTitle("Board Games: " + gameDesc + (localPiece == null ?"" : " (" + localPiece + ")"));
		
		this.turn = turn;
		this.board = board;
		this.pieces = pieces;
		this.inPlay = true;
		
		initializePlayersTypes();
		initializePiecesColors();
		
		disableView();
		
		handleTurnChange(turn);
	}
	
	private void initializePlayersTypes() {
		
		for (Piece p : pieces) {
			if (playerTypes.get(p) == null) {
				playerTypes.put(p, PlayerMode.MANUAL);//Starting Manual as default
				playerModesCB.addItem(p);
			}
		}
	}

	private void initializePiecesColors() {
		
		colorsCB.removeAllItems();
		
		for (Piece p : pieces) {
			if (pieceColors.get(p) == null)
				pieceColors.put(p, colorsIter.next());
			
			colorsCB.addItem(p);
		}
	}

	private void iniRightPanel(){
		addStatus();
		addPlayerInfo();
		addPieces();
		addPlayerModes();
		addAutoMoves();
		addButtons();
	}
	
	private void addToRightPanel(Component c){
			rightPanel.add(c);
	}
	
	private void addStatus(){
		
		JPanel pStatus = new JPanel(new BorderLayout());
		Border borderStatusPanel = new TitledBorder(new EtchedBorder(), "Status Message");
		pStatus.setBorder(borderStatusPanel);
		
		status = new JTextArea(5,10);
		status.setEditable(false);
		status.setLineWrap(true);
		
		JScrollPane sp = new JScrollPane(status);
		pStatus.add(sp,BorderLayout.CENTER);
		addToRightPanel(pStatus);
		
	}
			
		@SuppressWarnings("serial")
	private void addPlayerInfo(){
			
		JPanel pInfoPlayer = new JPanel(new BorderLayout());
		
		Border borderStatusPanel = new TitledBorder(new EtchedBorder(), "Player Information");
		pInfoPlayer.setBorder(borderStatusPanel);
		
		pInfoTable = new PlayerInfoTableModel();
		JTable tab = new JTable(pInfoTable){
			public Component prepareRenderer(TableCellRenderer renderer, int row, int col){
				Component comp = super.prepareRenderer(renderer,row, col);
				comp.setBackground(pieceColors.get(pieces.get(row)));
				pInfoTable.refresh();

				return comp;
			}
		};


		tab.setFillsViewportHeight(true);
		JScrollPane sp = new JScrollPane(tab);

		tab.setEnabled(false);
		pInfoPlayer.setPreferredSize(new Dimension(100,100));
		pInfoPlayer.add(sp);
		
		addToRightPanel(pInfoPlayer);

	}	
	
	private void addPieces(){
			
		JPanel piecePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Border borderPiecePanel = new TitledBorder(new EtchedBorder(), "Piece Colors");
		
		piecePanel.setBorder(borderPiecePanel);
		colorsCB = new JComboBox<Piece>();
			
		JButton cc = new JButton("Choose Color");
		cc.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Piece p = (Piece) colorsCB.getSelectedItem();
				ColorChooser c = new ColorChooser(new JFrame(),"Select Piece Color", pieceColors.get(p));
				if(c.getColor() != null) {
					pieceColors.put(p, c.getColor());
					repaint();
				}
			}	
		});
	
		piecePanel.add(colorsCB);
		piecePanel.add(cc);
		
		addToRightPanel(piecePanel);
	}
		
	@SuppressWarnings("serial")
	private void addPlayerModes(){	
		
		JPanel modesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Border borderModesPanel = new TitledBorder(new EtchedBorder(), "Player Modes");
		
		modesPanel.setBorder(borderModesPanel);	

		modesCB = new JComboBox<PlayerMode>();
		modesCB.addItem(PlayerMode.MANUAL);
		
		if(PlayerMode.RANDOM != null)
			modesCB.addItem(PlayerMode.RANDOM);
	
		if (PlayerMode.AI != null)
			modesCB.addItem(PlayerMode.AI);
		
		playerModesCB = new JComboBox<Piece>(new DefaultComboBoxModel<Piece>() {
			public void setSelectedItem(Object o) {
				super.setSelectedItem(o);
				if (playerTypes.get(o) != PlayerMode.MANUAL) 
					modesCB.setSelectedItem(PlayerMode.AI);
				else 
					modesCB.setSelectedItem(PlayerMode.MANUAL);
				
			}
		});
	
		JButton set = new JButton("Set");
		set.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Piece p = (Piece) playerModesCB.getSelectedItem();
				PlayerMode m = (PlayerMode) modesCB.getSelectedItem();
				PlayerMode currentM = playerTypes.get(p);
				playerTypes.put(p, m);
				
				pInfoTable.refresh();
				
				if( currentM == PlayerMode.MANUAL && m != PlayerMode.MANUAL)
					decideMakeAutomaticMove();
				
			}
			
		});
		
		modesPanel.add(playerModesCB);
		modesPanel.add(modesCB);
		modesPanel.add(set);
		
		addToRightPanel(modesPanel);
	}
	
	private void addAutoMoves(){
		
		JPanel automaticPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Border borderAutoPanel = new TitledBorder(new EtchedBorder(), "Automatic Moves");
		
		automaticPanel.setBorder(borderAutoPanel);
		
		rand = new JButton("Random");
		intelligent = new JButton("Intelligent");
		
		rand.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					ctrlMove(randomPlayer);
				}
				
			});
			
			intelligent.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e){
					ctrlMove(aiPlayer);
				}
				
			});
			
			automaticPanel.add(rand);
			automaticPanel.add(intelligent);
			
			addToRightPanel(automaticPanel);
		
	}
	
	private void addButtons(){
		
		JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.LEFT));
		quit = new JButton("Quit");
		restart = new JButton("Restart");
			
		
		quit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				quit();
			}
				
		});
			
		restart.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
					restart();
					
			}
				
		});
			
		panelButtons.add(quit);
		panelButtons.add(restart);
		
		addToRightPanel(panelButtons);
		
	}

	private void enableView(){

		rand.setEnabled(true);
        intelligent.setEnabled(true);
        restart.setEnabled(true);
        
        activateBoard();
	}
	
	private void disableView(){
		
		rand.setEnabled(false);   
		intelligent.setEnabled(false);
	    
	    deActivateBoard();
	}
	
	final protected void quit() {
		
		int n = JOptionPane.showOptionDialog(new JFrame(), "Are sure you want to quit?", "Quit",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

		if (n == 0) {
			
			try {
				ctrl.stop();
			} catch (GameError _e) {}
			
			this.setVisible(false);
			this.dispose();
			System.exit(0);
			
		}
		
	}
	
	final protected void restart() {
		
		int n = JOptionPane.showOptionDialog(new JFrame(), "Are sure you want to restart?", "Restart",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

		if (n == 0)
			try {
				status.setText("");
				ctrl.restart();
			} catch (GameError _e) {}
		
	}
	
}



