package es.ucm.fdi.tp.launcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import es.ucm.fdi.tp.base.console.ConsolePlayer;
import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameError;
import es.ucm.fdi.tp.base.model.GamePlayer;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.base.player.RandomPlayer;
import es.ucm.fdi.tp.base.player.SmartPlayer;
import es.ucm.fdi.tp.ttt.TttState;
import es.ucm.fdi.tp.was.WasState;

public class MainPr4 {

	//--------------------------Atributos------------------------------
	
	private static Scanner scan = new Scanner(System.in);
	
	//------------------------Constructores----------------------------
	
	public static <S extends GameState<S, A>, A extends GameAction<S, A>> int playGame(GameState<S, A> initialState,
			List<GamePlayer> players) {
		int playerCount = 0;
		for (GamePlayer p : players) {
			p.join(playerCount++); // welcome each player, and assign
									// playerNumber
		}
		@SuppressWarnings("unchecked")
		S currentState = (S) initialState;
		System.out.println(currentState);
		
		while (!currentState.isFinished()) {
			// request move
			A action = players.get(currentState.getTurn()).requestAction(currentState);
			// apply move
			currentState = action.applyTo(currentState);
			System.out.println("After action:\n" + currentState);

			if (currentState.isFinished()) {
				// game over
				String endText = "The game ended: ";
				int winner = currentState.getWinner();
				if (winner == -1) {
					endText += "draw!";
				} else {
					endText += "player " + (winner + 1) + " (" + players.get(winner).getName() + ") won!";
				}
				System.out.println(endText);
			}
		}
		return currentState.getWinner();
	}
	
	public static GameState<?,?> createInitialState(String gameName) {

		if(gameName.equalsIgnoreCase("TTT")) {
			System.out.println("Enter the size: ");
			return new TttState(scan.nextInt());
		}
		else if(gameName.equalsIgnoreCase("WAS")) return new WasState();
		else throw new GameError("Error: game " + gameName + " not defined"+ System.getProperty("line.separator"));
	}
	
	public static GamePlayer createPlayer(String gameName, String playerType, String playerName) {
		if(playerType.equalsIgnoreCase("Console")) return new ConsolePlayer(playerName, scan);
		else if(playerType.equalsIgnoreCase("Smart")) return new SmartPlayer(playerName, 5);
		else if(playerType.equalsIgnoreCase("Random")) return new RandomPlayer(playerName);
		else throw new GameError("Error: game " + playerName + " not defined"+ System.getProperty("line.separator"));
	}
	
	//Hemos dividido el main para poder hacer los test de los argumentos:
	public static void mainAux(String[] args) throws Exception {
		
		if(args.length == 3) {
			List<GamePlayer> players = new ArrayList<GamePlayer>();
			GameState<?, ?> game = createInitialState(args[0]);
			players.add(createPlayer(args[0], args[1], "jugador1"));
			players.add(createPlayer(args[0], args[2], "jugador2"));
			playGame(game, players);
		}
		else throw new IllegalArgumentException("The number of arguments is wrong.");
	}
	
	public static void main(String[] args){
		
		try {
			mainAux(args);
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}		
	}
}
