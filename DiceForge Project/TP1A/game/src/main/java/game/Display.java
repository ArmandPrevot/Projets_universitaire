package game;

import java.util.UUID;
import java.util.HashMap;
import java.util.List;
import com.corundumstudio.socketio.SocketIOClient;

import card.Card;
import card.Deck;
import card.ImmediateEffect;
import client.Inventory;
import client.Player;

import java.util.Map;

import event.ChoiceForge;
import face.Face;
import forge.Basin;
import forge.Dice;
import forge.Forge;
import game.GameState;
import game.PlayerManager;
import resources.Statistic;
import resources.TypeResource;

/**
 * The Class Display handle the game display
 */
public class Display {

	/**
	 * Instantiates a new display object.
	 */
	private Display() {
	}

	/** 
	 * Design Pattern : Singleton, 
	 * instance represents the display object.
	 */
	private static Display instance = null;
	
	/** 
	 * Instantiates the display instance
	 * Getter of the display instance
	 * @return the display instance
	 */
	public static Display getInstance() {
		if(instance == null) {
			instance = new Display();
		}
		return instance;
	}
	
	/**
	 * Boolean used to switch between single game printing or multiple games printing.
	 */
	private boolean verbose;

	/**
	 * Instantiates the single if v is true, else instantiates multiple display instance  
	 * 
	 * @param v the boolean that determines printing modes
	 * @return the correct Display instance depending on v.
	 */
	public static Display getInstance(boolean v) {
		if(instance == null) {
			instance = new Display();
			instance.verbose = v;
		}
		return instance;
	}
	
	/**
	 * Prints the player id.
	 *
	 * @param id the UUID of the Player
	 */
	public void printPlayerId(UUID id) {
		if(verbose) System.out.println("Création du joueur : " + id);
	}

	/**
	 * Prints the game state.
	 *
	 * @param state the state of the Game
	 */
	public void printGameState(GameState state) {
		if (verbose) System.out.println("\n" + "Game state : " + state);
	}

	/** displays the rank of the players and their capital in gold 
	 * 
	 * @param p the player
	 * @param listPlayers the list of the players
	 * */

	public void printListPlayer(Player p, Map<UUID, PlayerManager> listPlayers) {
		if (verbose) System.out.println("joueur " + listPlayers.get(p.getId()).getPlayer().getName() + "\n" + "Rang n°"
				+ listPlayers.get(p.getId()).getRank() + "\n" + listPlayers.get(p.getId()).getPlayer().getName() + " a "
				+ listPlayers.get(p.getId()).getInventory().getNbrGold() + " OR en début de partie \n");
	}

	/**
	 * Prints in which wave and in which turn the game is currently in
	 *
	 * @param wavenb the wave's number the game is currently in
	 * @param turnNb the turn's number the game is currently in
	 */
	public void printWaveTurn(int wavenb, int turnNb) {
		if (verbose) System.out.println("\n" + "Manche " + wavenb + " Tour : " + turnNb + "\n");
	}

	/**
	 * Prints the rolled dices.
	 *
	 * @param i     the inventory of the player
	 * @param dice1 the dice 1 of the player
	 * @param dice2 the dice 2 of the player
	 * @param name  the name of the player
	 */
	public void printRollDices(Inventory i, int dice1, int dice2, String name) {
		if(verbose) {
			System.out.println("C'est au tour du joueur " + name + "\n");
			System.out.println("Résultat De 1 : " + i.getD1().getFaceByNumF(dice1).toString());
			System.out.println("Résultat De 2 : " + i.getD2().getFaceByNumF(dice2).toString());
			System.out.println("\n" + i.toString(name));
		}
	}

	/**
	 * Prints the forge event ending
	 *
	 * @param f the face bought
	 * @param name the name of the buyer
	 * @param ch the ChoiceForge object that contains Player's choice
	 */
	// displays the result of the forge
	public void printForgeDone(Face f, String name, ChoiceForge ch) {
		if (verbose) System.out.println("\n" + "Le joueur " + name + " a acheté " + f.toString() + " et l'a fixé sur le De "
				+ ch.getRandDice() + "\n");
	}

	/**
	 * Prints that the player hasn't bought anything
	 * 
	 * @param p the PlayerManger containing the player
	 */
	public void printNoForge(PlayerManager p) {
		if (verbose) System.out.println("\n" + p.getPlayer().getName() + " n'a rien acheté !" + "\n");
	}

	/**
	 * Prints the inventory title final
	 */
	public void printIventoryTitle() {
		if(verbose) System.out.println("\n" + "RESUME DES INVENTAIRES DES JOUEURS" + "\n");
	}

	/**
	 * Prints the final inventory.
	 *
	 * @param score the final score
	 */
	public void printFinalInventory(String score) {
		if(verbose) {System.out.println(score);}
	}

	/**
	 * Prints the check winner equality.
	 *
	 *@param list the list of winners
	 */
	public void printCheckWinnerEgality(List<PlayerManager> list) {
		if(verbose) {
			System.out.println("\n Certains joueur sont à égalité, les voici : \n");
			for (PlayerManager player : list) {
				System.out.println(player.getPlayer().getName());
			}
		}
	}

	/**
	 * Prints the winner.
	 *
	 * @param listFirst the of winners
	 */
	public void printCheckWinner(List<PlayerManager> listFirst) {
		if(verbose) {
			System.out.println("\n" + "Le gagnant est : ");
			for (PlayerManager p : listFirst) {
				System.out.println(p.getPlayer().getName());
			}
		}
	}

	/**
	 * Prints the on disconnected client.
	 *
	 * @param client the client
	 */
	public void printOnDisconnectClient(SocketIOClient client) {
		System.out.println("\n"+ client.getSessionId() + " disconnected\n");
	}

	/**
	 * This function print the current player of the round
	 * 
	 * @param p a PlayerManager containing the player
	 */
	public void printCurrentPlayer(PlayerManager p) {
		if (verbose) System.out.println("Le joueur actif est : " + p.getPlayer().getName());
	}

	/**
	 * Display all the faces of the dices of the current player
	 * 
	 * @param d1 the player's first dice
	 * @param d2 the player's second dice
	 */
	public void printFaces(Dice d1, Dice d2) {
		if(verbose) {
			System.out.println("\nDé 1 : \n" + d1.toString());
			System.out.println("Dé 2 : \n" + d2.toString());
		}
	}

	/**
	 * Display the immediate effect of a card
	 * @param c the card
	 */
	public void printCardImmediateEffect(Card c) {
		if(verbose) {
			if (c.getiE() == ImmediateEffect.NONE)
				System.out.println("L'exploit ne produit aucun effet immédiat");
			else {
				if (c.getiE() == ImmediateEffect.INSTANT_GOLD)
					System.out.println("L'exploit ajoute instantanément 2 d'or au joueur");
				if (c.getiE() == ImmediateEffect.INSTANT_LUNAR)
					System.out.println("L'exploit ajoute instantanément 2 Point lunaire au joueur");
				if (c.getiE() == ImmediateEffect.INSTANT_SOLAR)
					System.out.println("L'exploit ajoute instantanément 2 Point solaire au joueur");
			}
		}
	}

	/**
	 * Display all the faces
	 * 
	 * @param f the forge containing all faces of the game
	 */
	public void printBasinFaces(Forge f) {
		if(verbose) {
			int basinCount = 1;
			System.out.println("\n Legend : || => OR ; && => AND \n");
			for (Basin b : f.getShop()) {
				System.out.println("Bassin " + basinCount + " : " + b.toString() + "");
				basinCount++;
			}
		}
	}

	/**
	 * Prints the number of launched games
	 * 
	 * @param gameNbr the number of games
	 */
	public void startGame(int gameNbr) {
		System.out.println("\n Il y a " + gameNbr + " parties lancées\n");
	}

	/**
	 *  Display which card has been bought by who
	 * @param name of the player
	 * @param name2 of the card
	 */
	public void printCardBought(String name, String name2) {
		if(verbose) System.out.println("\n Le joueur " + name + " a acheté la carte " + name2);
	}

	/**
	 * Prints which face has been chosen by the player
	 * @param choiceFace the face
	 */
	public void choiceFace(Face choiceFace) {
		if(verbose) {
			System.out.println("J'ai choisi la face " + choiceFace);
		}

	}
	/**
	 * Prints the final statistics of multiple games
	 * 
	 * @param g the gameManager containing statistics 
	 */
	public void printStats(GameManager g) {
		if(!verbose)
			System.out.println(g.printStats());
	}

}
