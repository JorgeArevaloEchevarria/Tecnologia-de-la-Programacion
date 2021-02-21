package es.ucm.fdi.tp.launcher;

//clases predefinidas:
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.swing.SwingUtilities;


//clases del programa:
import es.ucm.fdi.tp.base.console.ConsolePlayer;
import es.ucm.fdi.tp.base.model.*;
import es.ucm.fdi.tp.base.player.*;
import es.ucm.fdi.tp.mvc.GameTable;
import es.ucm.fdi.tp.ttt.*;
import es.ucm.fdi.tp.view.*;
import es.ucm.fdi.tp.was.*;

public class Main {

	// --------------------------Atributos------------------------------

	private static Scanner scan = new Scanner(System.in);

	// ----------------------------Metodos------------------------------

	// ----------------Metodos privados---------------------

	private static GameTable<?, ?> createGame(String gType) {

		if (gType.equalsIgnoreCase("TTT")) {
			System.out.println("Enter the size: ");
			return new GameTable<TttState, TttAction>(new TttState(
					scan.nextInt()));
		} else if (gType.equalsIgnoreCase("WAS"))
			return new GameTable<WasState, WasAction>(new WasState());
		else
			throw new GameError("Error: game " + gType + " not defined"
					+ System.getProperty("line.separator"));
	}

	private static GamePlayer createPlayer(String gameName, String playerType,
			String playerName) {
		if (playerType.equalsIgnoreCase("Manual"))
			return new ConsolePlayer(playerName, scan);
		else if (playerType.equalsIgnoreCase("Smart"))
			return new SmartPlayer(playerName, 5);
		else if (playerType.equalsIgnoreCase("Random"))
			return new RandomPlayer(playerName);
		else
			throw new GameError("Error: game " + playerName + " not defined"
					+ System.getProperty("line.separator"));
	}

	private static <S extends GameState<S, A>, A extends GameAction<S, A>> void startConsoleMode(
			String gType, GameTable<S, A> game, String playerModes[]) {

		List<GamePlayer> players = new ArrayList<GamePlayer>();

		players.add(createPlayer(gType, playerModes[0], "jugador 1"));
		players.add(createPlayer(gType, playerModes[1], "jugador 2"));

		new ConsoleView<S, A>(game);
		new ConsoleController<S, A>(players, game).run();
	}
	
	@SuppressWarnings("unchecked")
	private static <S extends GameState<S,A>, A extends GameAction<S,A>> 
	 void startGuiMode(String gType, GameTable<S,A> game) throws InvocationTargetException, InterruptedException {
		
		try {

			GameController<S,A> controlador = new GameController<S,A>(game);
			GamePlayer rand = new RandomPlayer(gType);
			GamePlayer smart = new SmartPlayer(gType, 5);
			
			SwingUtilities.invokeAndWait(new Runnable(){
				@Override
				public void run() {
					GameView<S, A> view = null;
		  
					for(int i = 0; i < game.getState().getPlayerCount(); i++) {
			  
						if(gType.equalsIgnoreCase("TTT")) view = (GameView<S, A>) new TttView();
						else if(gType.equalsIgnoreCase("WAS")) view = (GameView<S, A>) new WasView();
		  
						GameWindow<S,A> window = new GameWindow<S,A>(i, rand, smart, view, controlador, game);
						window.setTitle(gType + " view for player " + (i + 1));
					}
				}});
			
		} catch(InvocationTargetException | InterruptedException e) {
			System.err.println("ERROR: Ha ocurrido un error mientras se creaba la vista");
			System.exit(1);
		}
		
	  try {
			SwingUtilities.invokeLater(new Runnable() {
				public void run(){
					game.start();
				}
			});
		} catch(Exception e) {
			System.err.println("ERROR: Ha ocurrido un error mientras se creaba la vista");
			System.exit(1);
		}
	 }
	
	private static void usage() {
		System.out.println("ERROR: Modo de vista no valido");
	}

	// ----------------Metodos publicos---------------------

	public static void main(String[] args) {

		try {

			if (args.length < 2) {
				usage();
				System.exit(1);
			}

			GameTable<?, ?> game = createGame(args[0]);

			if (game == null) {
				System.err.println("Invalid game");
				System.exit(1);
			}

			String[] playerModes = Arrays.copyOfRange(args, 2, args.length);

			switch (args[1]) {
			case "console":
				startConsoleMode(args[0], game, playerModes);
				break;
			case "gui":
				startGuiMode(args[0], game);
				break;
			default:
				System.err.println("Invalid view mode: " + args[1]);
				usage();
				System.exit(1);
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
