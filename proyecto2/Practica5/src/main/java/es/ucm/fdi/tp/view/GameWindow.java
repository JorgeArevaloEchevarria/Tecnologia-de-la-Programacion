package es.ucm.fdi.tp.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import es.ucm.fdi.tp.base.model.*;
import es.ucm.fdi.tp.mvc.*;

//Esata clase es GUIcontroller en la ayuda en ingles
public class GameWindow<S extends GameState<S,A>,A extends GameAction<S,A>> extends JFrame 
						implements GameViewCtrl<S,A>, GameObserver<S,A> {
	
	//--------------------------SerialUID------------------------------
	
	private static final long serialVersionUID = 3744093245543982870L;
	
	//--------------------------Atributos------------------------------
	
	private GameController<S,A> gameCtrl; // Controlador de la vista grafica;
	private GameView<S, A> gameView; // Vista grafica sin panel de control de los botones;
	private GameObservable<S,A> game; // El juego para agregarse como observador;
	private int playerID;  // El id del jugador;
	private GamePlayer randPlayer; // Jugador random;
	private GamePlayer smartPlayer; // Jugador smart;
	private PlayerMode pMode; // Modo de jugador;
	
	//Botones para el controlador:
	private JButton botonDado; 
	private JButton botonNerd;
	private JButton botonRest;
	private JButton botonExit;
	
	//--------------------------Enumerado------------------------------
	
	public enum PlayerMode{MANUAL, RANDOM, SMART}
	
	//-------------------------Constructor-----------------------------
	
	public GameWindow(int playerId, GamePlayer randPlayer, GamePlayer smartPlayer, GameView<S,A> gameView, 
					  GameController<S,A> gameCtrl, GameObservable<S,A> game){
		
		// Inicializo los atributos:
		this.playerID = playerId;
		this.randPlayer = randPlayer;
		this.smartPlayer = smartPlayer;
		this.gameView = gameView;
		this.gameCtrl = gameCtrl;
		this.game = game;
		this.pMode = PlayerMode.MANUAL;
		
		// Paso a la vista del tablero el controlador y el estado:
		this.gameView.update(this.gameCtrl.getState());
		
		//Paso GameWindow a rectboargameview:
		this.gameView.updateCtrl(this);
		this.gameView.setController(this.gameCtrl);
		
		//Inicializa la GUI:
		this.iniciarGUI();
		
		// Agrego a la vista grafica como observador:
		this.game.addObserver(this); 
	}
	
	//----------------------------Metodos------------------------------
	
	//----------------Metodos privados---------------------
	
	//Crea la barra de botones del panel de control:
	private JToolBar iniciarPanelControl() {
		JToolBar controlBotones = new JToolBar();
		
		//Creamos cada uno de los botones:
		this.botonDado = new JButton();
		this.botonNerd = new JButton();
		this.botonRest = new JButton();
		this.botonExit = new JButton();
		
		//Añadimos los iconos a los botones:
		this.botonDado.setIcon(new ImageIcon(getClass().getResource("/dice.png")));
		this.botonNerd.setIcon(new ImageIcon(getClass().getResource("/nerd.png")));
		this.botonRest.setIcon(new ImageIcon(getClass().getResource("/restart.png")));
		this.botonExit.setIcon(new ImageIcon(getClass().getResource("/exit.png")));
	
		//Colocamos cada boton en la barra de control:
		controlBotones.add(botonDado);
		botonDado.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GameWindow.this.randomActionButtonPressed();
			}
			
		});
		controlBotones.add(botonNerd);
		botonNerd.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GameWindow.this.smartActionButtonPressed();
			}
			
		});
		controlBotones.add(botonRest);
		botonRest.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GameWindow.this.restartButtonPressed();
			}
		});
		controlBotones.add(botonExit);
		botonExit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GameWindow.this.quitButtonPressed();
			}
		});
		
		controlBotones.addSeparator(); //Separador de linea
		
		//Añadimos el ComboBox con su nombre:
		controlBotones.add(new JLabel("Player Mode: "));
		JComboBox<String> caja = new JComboBox<String>();
		caja.setModel(new DefaultComboBoxModel<>(new String[] { "Manual", "Random", "Smart" }));
		controlBotones.add(caja);
		caja.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(caja.getSelectedItem().toString().equalsIgnoreCase("SMART"))
					GameWindow.this.playerModeSelected(PlayerMode.SMART);		
		
				else if(caja.getSelectedItem().toString().equalsIgnoreCase("RANDOM"))
					GameWindow.this.playerModeSelected(PlayerMode.RANDOM);
				
				else GameWindow.this.playerModeSelected(PlayerMode.MANUAL);	 
			}
		});
		
		//Añadimos varios separadores:
		for(int i = 0; i < 18; i++) controlBotones.addSeparator();
		
		return controlBotones;
	}
	
	//Inicia la GUI:
	private void iniciarGUI() {
		JPanel principal = new JPanel(new BorderLayout());
		principal.add(this.gameView);
		JToolBar miControlBotones = iniciarPanelControl();
		this.getContentPane().add(miControlBotones, BorderLayout.NORTH);
		this.add(principal, BorderLayout.CENTER);
		
		// Ajustamos las ventanas:
		if(this.playerID == 0) { this.setBounds(100, 100, 600, 500); } //Tamaño de la ventana player 0;
		else { this.setBounds(700, 100, 600, 500); } //Tamaño de la ventana player 1
		
		this.setResizable(false); //Bloquea la redimension de la ventana;
		this.setVisible(true);	//Hace visible la ventana;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Cierra cierra la aplicación al cerrar ventana;
	}

	
	@SuppressWarnings("incomplete-switch")
	public void decideAutomaticMakeMove(){ 
		
		gameView.update(GameWindow.this.gameCtrl.getState());
		if(!GameWindow.this.gameCtrl.getState().isFinished()){
			if(GameWindow.this.gameCtrl.getState().getTurn() == GameWindow.this.playerID){
				GameWindow.this.gameView.enable();
				
				switch(this.pMode){
				case RANDOM:
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							GameWindow.this.randomActionButtonPressed();
							GameWindow.this.gameView.disable();
					}
				});
				break;
				
				case SMART:
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							GameWindow.this.smartActionButtonPressed();
							GameWindow.this.gameView.disable();
						}
					});
				break;
				}
			}
			else{
				if(GameWindow.this.pMode == GameWindow.PlayerMode.MANUAL){
					GameWindow.this.gameView.showInfoMessage("Turn for player " + 
					(GameWindow.this.gameCtrl.getState().getTurn() + 1));
				}
			}
		}
	}
	
	
	//----------------Metodos publicos---------------------
		
	public void handleEvent(GameEvent<S,A> e){
		switch (e.getType()){
		case Stop: 
		case Error: 
		case Info: 
			this.gameView.showInfoMessage(e.toString());
			break;
		case Start:
		case Change: 
			this.gameView.showInfoMessage(e.toString());
			this.decideAutomaticMakeMove();
			this.gameView.update(e.getState());
			if(gameCtrl.getState().getTurn() != this.playerID) {
				botonDado.setEnabled(false);
				botonNerd.setEnabled(false);
			}
			else {
				botonDado.setEnabled(true);
				botonNerd.setEnabled(true);
			}
			
			break;
		default: System.out.println("ERROR: evento no encontrado");
		}
	}
	
	@Override
	public void notifyEvent(GameEvent<S, A> e) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				handleEvent(e); // actualiza componentes visuales.
			}
		});
	}
	 
	@Override
	public void userActionAvailable(A p) {
		if(gameCtrl.getState().getTurn() == this.playerID) {
			this.gameCtrl.makeManualMove(p);
			this.gameView.disable();
		}
	}

	@Override
	public void randomActionButtonPressed() {
		if(!this.gameCtrl.getState().isFinished())
			this.gameCtrl.makeRandomMove(this.randPlayer);
	}

	@Override
	public void smartActionButtonPressed() {
		if(!this.gameCtrl.getState().isFinished())
			this.gameCtrl.makeSmartMove(this.smartPlayer);
	}

	@Override
	public void restartButtonPressed() {
		this.gameCtrl.start();
	}

	@Override
	public void quitButtonPressed() {
		this.gameCtrl.stop();
		
		String [] opciones ={"Aceptar","Cancelar"};
		int eleccion = JOptionPane.showOptionDialog(rootPane,"¿ Desea cerrar la aplicacion ?","Mensaje de Confirmacion",
		JOptionPane.YES_NO_OPTION,
		JOptionPane.QUESTION_MESSAGE,null,opciones,"Aceptar");
		
		if (eleccion == JOptionPane.YES_OPTION) System.exit(0);
	}

	@Override
	//Cambia el modo de juego:
	public void playerModeSelected(PlayerMode pMode) { 
		this.pMode = pMode;
		if(this.gameCtrl.getState().getTurn() == this.playerID){
			if (pMode.equals(PlayerMode.RANDOM)) {
				this.gameCtrl.makeRandomMove(randPlayer);
				this.gameView.showInfoMessage("Change PlayerMode = RANDOM");
			} else if (pMode.equals(PlayerMode.SMART)) {
				this.gameCtrl.makeSmartMove(randPlayer);
				this.gameView.showInfoMessage("Change PlayerMode = SMART");
			} else {
				this.gameView.showInfoMessage("Change PlayerMode = MANUAL");
			}
		}
	}
	
	// Devuelve el tipo de jugador:
	public PlayerMode getPlayerMode() { return this.pMode; }
		
	// Devuelve el id del jugador:
	public int getPlayerId(){ return this.playerID; }
}
