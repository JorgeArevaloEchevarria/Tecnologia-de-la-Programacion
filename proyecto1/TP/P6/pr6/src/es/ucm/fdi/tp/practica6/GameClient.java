package es.ucm.fdi.tp.practica6;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.GameFactory;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.control.commands.Command;
import es.ucm.fdi.tp.basecode.bgame.control.commands.PlayCommand;
import es.ucm.fdi.tp.basecode.bgame.control.commands.QuitCommand;
import es.ucm.fdi.tp.basecode.bgame.control.commands.RestartCommand;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.Game.State;
import es.ucm.fdi.tp.basecode.bgame.model.GameError;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica6.Response.Response;
/**
 * 
 * clase que implementa al cliente en la conexion
 *
 */
public class GameClient extends Controller implements Observable<GameObserver>, GameObserver{
	
	private String host;
	private int port;
	private List<GameObserver> observers;
	private Piece localPiece;
	private GameFactory gameFactory;
	private Connection connectionToServer;
	private boolean gameOver;
	/**
	 * Constructor de GameClient
	 * 
	 * @param host
	 * @param port
	 * @throws Exception
	 */
	public GameClient(String host,int port) throws Exception {
		super(null,null);
		this.host = host;
		this.port = port;
		
		observers = new ArrayList<GameObserver>();
		gameOver = false;
		
		connect();
		
	}
	/**
	 * conecta con el servidor
	 * @throws IOException
	 */
	private void connect() throws IOException{
		connectionToServer = new Connection(new Socket(host,port));
		connectionToServer.sendObject("Connect");//envia al servidor
		
		Object response = null;
		try{
			response = connectionToServer.getObject();//recibe un objeto del servidor
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		if(response instanceof Exception){
			try{
				throw (Exception) response;
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		try{
			gameFactory = (GameFactory) connectionToServer.getObject();//crea la factoria que recibe del servidor
			localPiece = (Piece) connectionToServer.getObject();// y las piezas
		}catch(Exception e){
			throw new GameError("Unknown server response: " + e.getMessage());
		}
	}
	
	/**
	 * Añade un observador a la lista observers
	 */
	public void addObserver(GameObserver o) {
		observers.add(o);
	}

	/**
	 * Elimina un observador de la lista observers
	 */
	public void removeObserver(GameObserver o) {
		observers.remove(o);
	}
	/**
	 * devuelve el atributo gameFactory
	 * @return gameFactory
	 */
	public GameFactory getGameFactory(){
		return gameFactory;
	}
	/**
	 * devuelve el atributo localPiece
	 * @return localPiece
	 */
	public Piece getPlayerPiece(){
		return localPiece;
	}
	
	/**
	 * manda el comando jugar al servidor
	 */
	public void makeMove(Player p){
		forwardCommand(new PlayCommand(p));
	}
	/**
	 * manda el comando salir al servidor
	 */
	public void stop(){
		forwardCommand(new QuitCommand());
	}
	/**
	 * manda el comando reiniciar al servidor
	 */
	public void restart(){
		forwardCommand(new RestartCommand());
	}
	/**
	 * manda un comando al servidor
	 * @param cmd
	 */
	private void forwardCommand(Command cmd) {
		if(!gameOver)
			try {
				connectionToServer.sendObject(cmd);
			} catch (IOException e){
				e.printStackTrace();
			}
		
	}
	/**
	 * realiza las ordenes del servidor
	 */
	public void start(){
		this.observers.add(this);
		gameOver = false;
		
		while(!gameOver){
			try{
				Response res = (Response)connectionToServer.getObject();
				for(GameObserver o: observers)
					res.run(o);//ejecuta lo que le manda el servidor
				
			}catch(ClassNotFoundException | IOException e){}
		}
	}

	@Override
	public void onGameStart(Board board, String gameDesc, List<Piece> pieces, Piece turn){
	}

	@Override
	public void onGameOver(Board board, State state, Piece winner){
	}

	@Override
	public void onMoveStart(Board board, Piece turn){
	}

	@Override
	public void onMoveEnd(Board board, Piece turn, boolean success){
	}

	@Override
	public void onChangeTurn(Board board, Piece turn){
	}

	@Override
	public void onError(String msg){
	}

}
