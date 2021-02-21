package es.ucm.fdi.tp.practica6;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.GameFactory;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.control.commands.Command;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.Game;
import es.ucm.fdi.tp.basecode.bgame.model.Game.State;
import es.ucm.fdi.tp.practica6.Response.ChangeTurnResponse;
import es.ucm.fdi.tp.practica6.Response.ErrorResponse;
import es.ucm.fdi.tp.practica6.Response.GameOverResponse;
import es.ucm.fdi.tp.practica6.Response.GameStartResponse;
import es.ucm.fdi.tp.practica6.Response.MoveEndResponse;
import es.ucm.fdi.tp.practica6.Response.MoveStartResponse;
import es.ucm.fdi.tp.practica6.Response.Response;
import es.ucm.fdi.tp.basecode.bgame.model.GameError;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
/**
 * 
 * clase que implementa al servidor en la conexion
 *
 */
public class GameServer extends Controller implements GameObserver {
	
	private int port;
	private int numPlayers;
	private int numOfConnectedPlayers;
	private GameFactory gameFactory;
	private List<Connection> clients;
	private JTextArea infoArea;
	
	volatile private ServerSocket server;
	volatile private boolean stopped;
	volatile private boolean gameOver;
	/**
	 * Constructor de GameServer
	 * @param gameFactory
	 * @param pieces
	 * @param port
	 */
	public GameServer(GameFactory gameFactory, List<Piece> pieces, int port) {
		super(new Game(gameFactory.gameRules()), pieces);
		
		// Inicializa los campos con los valores correspondientes
		this.port=port;
		this.gameFactory = gameFactory;
		numPlayers = pieces.size();
		clients = new ArrayList<Connection>();
		gameOver = false;
		
		game.addObserver(this);
	}
	/**
	 * manda al cliente para jugar
	 */
	@Override
	public void onGameStart(Board board, String gameDesc, List<Piece> pieces, Piece turn) {
		forwardNotification(new GameStartResponse(board, gameDesc, pieces, turn));
	}
	/**
	 * manda una notificacion al cliente
	 * @param r
	 */
	void forwardNotification(Response r){
		for(Connection c : clients)
			try {
				c.sendObject(r);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	/**
	 * manda al cliente parar
	 */
	@Override
	public void onGameOver(Board board, State state, Piece winner) {
		forwardNotification(new GameOverResponse(board,state, winner));
		stop();
	}
	/**
	 * manda una notificacion al cliente que empieza su turno
	 */
	@Override
	public void onMoveStart(Board board, Piece turn) {
		forwardNotification(new MoveStartResponse(board, turn));
	}
	/**
	 * manda una notificacion al cliente que acaba su turno
	 */
	@Override
	public void onMoveEnd(Board board, Piece turn, boolean success) {
		forwardNotification(new MoveEndResponse(board, turn, success));
	
	}
	/**
	 * manda al cliente el cambio de turno
	 */
	@Override
	public void onChangeTurn(Board board, Piece turn) {
		forwardNotification(new ChangeTurnResponse(board, turn));
		
	}
	/**
	 * manda una notificacion de error al cliente
	 */
	@Override
	public void onError(String msg) {
		forwardNotification(new ErrorResponse(msg));
	}
	/**
	 * 
	 */
	public synchronized void makeMove(Player player) {
		try { super.makeMove(player); } catch (GameError e) { }
	}
	/**
	 * 
	 */
	public synchronized void stop() {//mirar player
		try { super.stop(); } catch (GameError e) { }
	}
	
	public synchronized void restart() {//mirar player
		try { super.restart(); } catch (GameError e) { }
	}
	/**
	 * inicia el servidor
	 */
	public void start() {
		 controlGUI();
		 try {
			startServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 */
	private void controlGUI(){
		
		try{
			SwingUtilities.invokeAndWait(new Runnable(){
				
				public void run(){constructGUI();}
			});	
		}catch (InvocationTargetException | InterruptedException e) { 
			throw new GameError("Something went wrong when constructing the GUI");	
		}
	}
	/**
	 * 
	 */
	private void constructGUI(){
		
		JFrame window = new JFrame("GameServer");
		
		infoArea = new JTextArea(5,30);//crea un area donde escribir
		infoArea.setEditable(false);//no permitimos que el usuario la pueda editar
		JScrollPane sp = new JScrollPane(infoArea);
		
		window.add(sp,BorderLayout.NORTH);
		
		JButton quitButton = new JButton("Stop Server");//boton para parar el servidor
		
		quitButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				quit();
			}
			
		});
		window.add(quitButton,BorderLayout.SOUTH);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
	}
	/**
	 * envia  datos al panel de informacion
	 * @param msg
	 */
	private void log(String msg){
		
		SwingUtilities.invokeLater(new Runnable(){
			
			public void run(){infoArea.append("*" + msg + "\n");}
		});	
	}
	/**
	 * incia el servidor
	 * @throws IOException
	 */
	private void startServer() throws IOException{
	
		try{	
			server = new ServerSocket(port);
		}catch(IOException e){
			throw new GameError("The Server is not enabled");
		}
		stopped = false;
		
		while(!stopped){
			try{
				Socket s = server.accept();
				log("A player wants to join the game");
				handleRequest(s);
				
			}catch (IOException e){
				
				if(!stopped)
					log("error while waiting for a connection: " + e.getMessage());
				
			}
				
		}
		
	}
	/**
	 * realiza la conexion con el cliente
	 * @param s
	 */
	private void handleRequest(Socket s){
		
		try{
			
			Connection c = new Connection(s);;
			Object clientRequest = c.getObject();
			if(!(clientRequest instanceof String)&&!((String)clientRequest).equalsIgnoreCase("Connect")){
				
				c.sendObject(new GameError("Invalid Request"));
				c.stop();
				return;
				
			}
			
			//1. Comprobamos numero maximo de jugadores
			if(numPlayers == numOfConnectedPlayers){
				log("Server is full, the maximum number of players is reached");
				c.sendObject(new GameError("Server is full"));
				c.stop();
			}
			//2.Incrementar el numero de clientes conectados
			numOfConnectedPlayers++;
			clients.add(c);
			log("A player just connected");
			
			//3. enviamos el Ok, el juego y su ficha
			c.sendObject("OK");
			c.sendObject(gameFactory);
			c.sendObject(pieces.get(numOfConnectedPlayers-1));
			
			//4. iniciar el juego
			if(numPlayers ==  numOfConnectedPlayers){
				if(game.getState() == State.Starting){
					System.out.println("The game is about to start... Go!");
					game.start(pieces);
				
				}else
					game.restart();
			}
			startClientListener(c);
			
		} catch (IOException | ClassNotFoundException _e) { }
		
	}
	/**
	 * ejecuta la informacion que le envia el cliente
	 * @param c
	 */
	private void startClientListener(Connection c) {
		gameOver = false;
		
		Thread t = new Thread(){
			
			public void run(){
				
				while(!stopped && !gameOver){
					Command cmd;
					try{
						//leer el comando
						cmd= (Command) c.getObject();
						//ejecutar el comando
						cmd.execute(GameServer.this);
					}catch (ClassNotFoundException | IOException e) {
						if(!stopped && !gameOver)
							stopTheGame();//se para el juego, pero no el servidor
					}
				}
			}

		};
		t.start();
	}
	/**
	 * para el juego
	 */
	private void stopTheGame() {
		stopped = true;
	}
	/**
	 * quita el la conexion
	 */
	private void quit(){
		int n = JOptionPane.showOptionDialog(new JFrame(),"Are you sure you want to quit?",
				"Yes", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
		
		if(n==0){
			try{
				gameOver = true;
				stop();
				dcClient();
				stopServer();
				
			}catch(GameError _e){}
			
			System.exit(0);
		}
	}
	/**
	 * 
	 */
	private void dcClient(){
		for(Connection c : clients){
			try {
				c.stop();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * para el servidor
	 */
	private void stopServer(){
		stopped = true;
		
		try{
			server.close();
		}catch(IOException e){}
	}
	
}
