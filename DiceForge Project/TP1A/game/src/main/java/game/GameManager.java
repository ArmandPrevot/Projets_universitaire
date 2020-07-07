package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import card.Card;
import card.Cost;
import card.Deck;
import card.ImmediateEffect;
import card.Island;
import client.NamePlayers;
import client.Player;
import event.ChoiceExploit;
import event.ChoiceExploitForge;
import event.ChoiceFace;
import event.ChoiceForge;
import event.Protocol;
import event.TypeChoice;
import face.CompoundFace;
import face.Face;
import face.HybridFace;
import face.SimpleFace;
import forge.Forge;
import forge.TypeBasins;
import resources.Statistic;
import resources.TypeResource;
import game.Display;
import interfaces.IGameManager;
import interfaces.IServer;
import forge.Basin;

/**
  * Class that defines game behaviors and contains all data of a Game.
  */

public class GameManager implements IGameManager {

	/**   listPlayers contains a list of UUID, PlayerManager couple *. */
	Map<UUID, PlayerManager> listPlayers;
	
	/**  listIdPlayers contains a list of UUID, use as a stack to determine the current Player *. */
	List<UUID> listIdPlayers;
	
	/** The current player. */
	PlayerManager currentPlayer;
	
	/**  GameLoop works with GameState to schedule the several steps in the right order *. */
	GameLoop loop;
	
	/** The state. */
	GameState state;
	
	/**  s represents the SocketIOServer *. */
	IServer s;
	
	/** NameList contains a list of already created names in a Enumeration : NamePlayers.java  **/
	List<NamePlayers> Namelist = new ArrayList<NamePlayers>(Arrays.asList(NamePlayers.values()));
	
	/** The forge contains faces that players can buy. **/
	Forge forge;
	
	/** Represents all the basins containing all the cards to sell. */
	List<Island> islands;
	
	/** the counter to manage player's rank *. */
	int counter;
	
	/** The list stat. */
	HashMap<UUID, Statistic> listStat;

	/**
	 * Instantiates a new game manager.
	 *
	 * @param s the Server of the game.
	 */
	public GameManager(IServer s) {
		this.s = s;
		this.listPlayers = new HashMap<UUID, PlayerManager>();
		this.listIdPlayers = new ArrayList<>();
		this.listStat = new HashMap<>();
		init();
		this.setState(GameState.WAITING_PLAYER);
	}
	
	/**
	 * Initialize a game
	 * Useful for launching N games in a row.
	 */
	private void init() {
		this.setState(GameState.INIT);
		this.counter = 0;
		this.forge = new Forge();
		this.islands = new ArrayList<>();
		this.loop = new GameLoop(this);
	}
	
	/**
	 * Reset a game by calling init() and clears player's inventory. 
	 * Useful for launching N games in a row
	 */
	public void reset() {
		this.init();
		HashMap<UUID, PlayerManager> clone = new HashMap<UUID, PlayerManager>(this.listPlayers);
		
		this.listPlayers.clear();
		
		for(UUID id : clone.keySet()) {
			PlayerManager p = clone.get(id);
			this.listPlayers.put(id, new PlayerManager(new Player(id, p.getPlayer().getName()), this));
		}
	}
	
		
	/**
	 *  Sets the current player according to listIdPlayers natural order, stack system *.
	 */
	public void setCurrentPlayer() {
		UUID currentIdPlayer = this.listIdPlayers.remove(0);
        this.currentPlayer = listPlayers.get(currentIdPlayer);
        listIdPlayers.add(currentIdPlayer);
        Display.getInstance().printCurrentPlayer(this.currentPlayer);
	}
	
	/**
	 *  Affect the player id and it's manager into listPlayers *.
	 *
	 * @param p the Player to add
	 */
	public void addPlayer(Player p) {
		this.listStat.put(p.getId(), new Statistic());
		listPlayers.put(p.getId(), new PlayerManager(p, this));
		
		if(this.listPlayers.get(p.getId()).getRank() == 0) {
			this.listPlayers.get(p.getId()).setRank(counter +1);
		}
		counter ++;	
		
		if(this.listPlayers.get(p.getId()).getRank() == 1) {
			this.listPlayers.get(p.getId()).getInventory().addRessourcesByType(TypeResource.GOLD, 3);
		}
		if(this.listPlayers.get(p.getId()).getRank() == 2) {
			this.listPlayers.get(p.getId()).getInventory().addRessourcesByType(TypeResource.GOLD, 2);
		}
		if(this.listPlayers.get(p.getId()).getRank() == 3) {
			this.listPlayers.get(p.getId()).getInventory().addRessourcesByType(TypeResource.GOLD, 1);
		}
		if(this.listPlayers.get(p.getId()).getRank() == 4) {
			this.listPlayers.get(p.getId()).getInventory().addRessourcesByType(TypeResource.GOLD, 0);
		}
	}
	
	/**
	 *  Adds the players to the game by calling addPlayer from PlayerManager.
	 *
	 * @param id the id of the new player
	 */
	public void addNewPlayer(UUID id) {
		if(state == GameState.WAITING_PLAYER) {
			this.addPlayer(new Player(id, this.Namelist.remove(0).name()));
			this.listIdPlayers.add(id);
			
			Display.getInstance().printPlayerId(id);
			Display.getInstance().printListPlayer(this.listPlayers.get(id).getPlayer(), this.listPlayers);
		}
	}
	
	
	/**
	 *  Link the Server.java to GameLoop.java, to emit a message to the Client *
	 *
	 * @param id the UUID of the Client
	 * @param p the Protocol that's describes the event
	 * @param o the object to send
	 */
	public void sendToClient(UUID id, Protocol p, Object o) {
		this.s.sendToClient(id, p, o);
	}
	
	
	/**
	 *  Launch the forge's items and islands instantiation, start the game loop.
	 */
	public void play() {
		
		this.setState(GameState.STARTED);

		/**
		 * This function adds and Display all purchasable faces to the forge
		 */
		
		addFacesToForge();
		
		/**
		 * This function create the 6 Islands
		 */
		
		createIslands();
		
		/**
		 * Fill the islands newly created
		 */
		
		addCardsToIslands(listPlayers.size());
		
		/**
		 * Launches the game loop
		 */
		
		this.loop.execute();

	}
	
	/**
	 * This function adds and Display all purchasable faces to the forge.
	 */
	public void addFacesToForge() {
		
		//Add Basin to the forge
		for(TypeBasins tB: TypeBasins.values()) {
			forge.getShop().add(new Basin(tB.getPriceBasin()));
		}
		
		/* ------ Simple Face ------*/
		
		SimpleFace[] sf = SimpleFace.values();
		
		//Basin 1, 2, 3 and 4
		for(int i=0; i <= 3; i++) {
			forge.addFacesToBasin(new Face(sf[i].getValue(), sf[i].getType(), sf[i].getPrice()), 
				forge.getShop().get(i), this.listPlayers.size());
		}
		
		//Basin 7, 8 and 9
		for(int j=6; j <= 8; j++) {
			forge.addFacesToBasin(new Face(sf[j].getValue(), sf[j].getType(), sf[j].getPrice()), 
					forge.getShop().get(j), this.listPlayers.size());
		}
		
		/* -------------------------*/
		
		/* ------ Compound Face ------*/
		
		CompoundFace[] hf = CompoundFace.values();
		
		//Basin 5 : particular due to the fact that it contains 4 different type faces
		//Can't do a loop
		forge.addFacesToBasin(new Face(sf[4].getValue(), sf[4].getType(), sf[4].getPrice()), 
				forge.getShop().get(4), 1);
		forge.addFacesToBasin(new HybridFace(hf[0].getPrice(), hf[0].isSelectable(), hf[0].getListFace()),
				forge.getShop().get(4), 1);
		forge.addFacesToBasin(new HybridFace(hf[1].getPrice(), hf[1].isSelectable(), hf[1].getListFace()), 
				forge.getShop().get(4), 1);
		forge.addFacesToBasin(new HybridFace(hf[2].getPrice(), hf[2].isSelectable(), hf[2].getListFace()),
				forge.getShop().get(4), 1);
		
		//Basin 6
		forge.addFacesToBasin(new HybridFace(hf[3].getPrice(), hf[3].isSelectable(), hf[3].getListFace()),
				forge.getShop().get(5), 4);
		
		//Basin 10 : same as basin 5
		forge.addFacesToBasin(new Face(sf[9].getValue(), sf[9].getType(), sf[9].getPrice()), 
				forge.getShop().get(9), 1);
		forge.addFacesToBasin(new HybridFace(hf[4].getPrice(), hf[4].isSelectable(), hf[4].getListFace()),
				forge.getShop().get(9), 1);
		forge.addFacesToBasin(new HybridFace(hf[5].getPrice(), hf[5].isSelectable(), hf[5].getListFace()), 
				forge.getShop().get(9), 1);
		forge.addFacesToBasin(new HybridFace(hf[6].getPrice(), hf[6].isSelectable(), hf[6].getListFace()),
				forge.getShop().get(9), 1);
		
		/* ---------------------------*/
		
		Display.getInstance().printBasinFaces(forge);
		
	}
	
	/**
	 * This function create the 6 Islands.
	 */
	public void createIslands() {
		for(int i=0; i<6; i++) {
			this.islands.add(new Island());
		}

	}
	
	/**
	 * this function adds x times the two cards per island. 
	 * x is the number of players
	 * @param nbrPlayers is number of players
	 */
	public void addCardsToIslands(int nbrPlayers) {
		int cardCpt = 0;
		for(int i=0; i < islands.size(); i++) {
			Card c1 = new Card(Deck.values()[cardCpt],Deck.values()[cardCpt].getName(), Deck.values()[cardCpt].getGlory(),Deck.values()[cardCpt].getiE() ,(Deck.values()[cardCpt].getCosts()));
			for(int j=0; j < nbrPlayers; j++) {
				islands.get(i).addCard(c1);
			}
			cardCpt++;
			Card c2 = new Card(Deck.values()[cardCpt],Deck.values()[cardCpt].getName(), Deck.values()[cardCpt].getGlory(), Deck.values()[cardCpt].getiE() ,(Deck.values()[cardCpt].getCosts()));
			for(int n=0; n < nbrPlayers; n++) {
					islands.get(i).addCard(c2);
				}
			cardCpt++;
			}
		}
	
	/**
	 * This function return the island List.
	 *
	 * @return islands the Islands list
	 */
	public List<Island> getIslands() {
		return islands;
	}
	
	/**
	 * This function allow to set the island list.
	 *
	 * @param islands a islands list
	 */
	public void setIslands(List<Island> islands) {
		this.islands = islands;
	}

	/**
	 *  Called when all waves has been cleared, prints the players scores *.
	 */
	public void gameOver() {
		Display.getInstance().printIventoryTitle();
		for(UUID id : listIdPlayers) {
			Display.getInstance().printFinalInventory(this.listPlayers.get(id).getInventory().toString(this.listPlayers.get(id).getPlayer().getName()));
		}
		
		checkWinner();
		
		this.setState(GameState.FINISHED);		
		
	}

	
	/**
	 * Check the winner by comparing Glory Points, handle the equality case.
	 */
	public void checkWinner() {
		List<PlayerManager> listWinners = new ArrayList<>(this.listPlayers.values());
        Collections.sort(listWinners, new SortByGlory());
        List<PlayerManager>listFirst = new ArrayList<>();
        int max = listWinners.get(0).getInventory().getNbGlory();
        
		for(PlayerManager p : listWinners) {
			if(p.getInventory().getNbGlory() == max) {
				listFirst.add(p);
			}
		}
        
		if(listFirst.size() > 1) {
			Display.getInstance().printCheckWinnerEgality(listFirst);
			for(PlayerManager p : listFirst) {
				p.getInventory().addEquality();
			}
		}
		else
			for(PlayerManager p : listFirst) {
				p.getInventory().addVictory();
			}
			Display.getInstance().printCheckWinner(listFirst);
	}
	
	/**
	 *  Handle the event choice Client, previously triggered by the Server Event Listener.
	 *	
	 * @param ch the ChoiceForge object that contains Client choice 
	 */
	public void choiceForge(ChoiceForge ch) {
        Face f = ch.getForge().getShop().get(ch.getIndexBassin()).getBasinFace().remove(ch.getIndexFace());
        		
        this.currentPlayer.getInventory().setFaceOnDice(f, ch.getRandDice(), ch.getRandFace());

        this.currentPlayer.getInventory().setNbrGold(currentPlayer.getInventory().getNbrGold() - f.getPrice());
      
        Display.getInstance().printForgeDone(f, listPlayers.get(ch.getId()).getPlayer().getName(), ch);
    }
	
	/**
	 * Set the player's name to add his AI's name.
	 *
	 * @param AIname the AI's name
	 * @param playerID the player UUID
	 */
	public void addAINameToPlayerName(String AIname, UUID playerID) {
		String currentName = this.listPlayers.get(playerID).getPlayer().getName();
		this.listPlayers.get(playerID).getPlayer().setName(currentName + AIname);
		}
	
	/**
	 *  Returns the state of the game.
	 *
	 * @return the state
	 */
	public GameState getState() {
		return state;
	}
	
	/**
	 *  Set the state of the game *.
	 *
	 * @param state the new state
	 */
	public void setState(GameState state) {
		this.state = state;
		Display.getInstance().printGameState(state);
	}
	
	/** This method handles whether the player wants to buy a card or face or nothing.
	 * 
	 * @param chFE a ChoiceExploitForge contains all the basins (chFE.chE) and all the forge (chFE.chF)
	 */
	public void handleExploitForge(ChoiceExploitForge chFE) {
		if(chFE.getType() == TypeChoice.FORGE) {
			this.choiceForge(chFE.getChF());
		}
		else if(chFE.getType() == TypeChoice.EXPLOIT){
			this.choiceExploit(chFE.getChE());
		}
		else {
			Display.getInstance().printNoForge(listPlayers.get(chFE.getChF().getId()));
		}
		
		this.loop.setStep(GameStepLoop.END_TURN);
	}
	
	/**
	 * This function remove a card from an island to put it in player inventory.
	 *
	 * @param chE a ChoiceExploit containing the card chosen by the client
	 */
	private void choiceExploit(ChoiceExploit chE) {
		
		Card c = islands.get(chE.getRandIsland()).getCardsAvailable().remove(chE.getRandCard());
		
		this.currentPlayer.getInventory().addCard(c);
		
		/* Immediate effect that triggers when the current players buy a card */
		if(c.getiE() == ImmediateEffect.INSTANT_GOLD)
			this.currentPlayer.getInventory().addRessourcesByType(TypeResource.GOLD, 2);
		
		if(c.getiE() == ImmediateEffect.INSTANT_LUNAR)
			this.currentPlayer.getInventory().addRessourcesByType(TypeResource.LUNAR, 2);
		
		if(c.getiE() == ImmediateEffect.INSTANT_SOLAR)
			this.currentPlayer.getInventory().addRessourcesByType(TypeResource.SOLAR, 2); 
		
		
		Display.getInstance().printCardBought(this.currentPlayer.getPlayer().getName(), c.getName());
		Display.getInstance().printCardImmediateEffect(c);
		
		
		for(Cost co : c.getCost()) {
			this.currentPlayer.getInventory().removeRessources(co.getType(), co.getValue());
		}
		
		
	}
	
	/**
	 * Link from Server to PlayerManager in order to transmit the face chosen to the Handler.
	 *
	 * @param chF the choiceFace object containing client's choice
	 * @param id the of the player that has chosen
	 */
	public void choiceFace(ChoiceFace chF, UUID id) {
		this.listPlayers.get(id).handleChoiceFace(chF);
	}
	
	/**
	 * Gets the player stats.
	 *
	 * @param idPlayer the id player
	 * @return the player stats
	 */
	public Statistic getPlayerStats(UUID idPlayer) {
		return this.listStat.get(idPlayer);
	}
	
	/**
	 * Prints the final statistics.
	 *
	 * @return s containing the statistics
	 */
	public String printStats() {
		String s = "\t\t\t";
		
		for(PlayerManager p : this.listPlayers.values()) {
			s += " " + p.getPlayer().getName() + " ";
		}
		
		s += "\n Number of gold earned : ";
		
		for(Statistic stat : this.listStat.values()) {
			s += "\t" + stat.getInventoryStats().getGold() + "\t |";
		}
		
		s += "\n Number of lunar earned : ";
		
		for(Statistic stat : this.listStat.values()) {
			s += "\t" + stat.getInventoryStats().getLunar() + "\t |";
		}
		
		s += "\n Number of solar earned :";
		
		for(Statistic stat : this.listStat.values()) {
			s += "\t" + stat.getInventoryStats().getSolar() + "\t |";
		}
		
		s += "\n Number of glory earned :";
		
		for(Statistic stat : this.listStat.values()) {
			s += "\t" + stat.getInventoryStats().getGlory() + "\t |";
		}
		
		s += "\n Number of cards earned :";
		
		for(Statistic stat : this.listStat.values()) {
			s += "\t" + stat.getInventoryStats().getSavedCards().size() + "\t |";
		}
		
		s += "\n Number of faces forged :";
		
		for(Statistic stat : this.listStat.values()) {
			s += "\t" + stat.getInventoryStats().getForgedFaces() + "\t |";
		}
		
		s+="\n ----------------------------------------------------------------------------------------------------";
		
		for(Deck deck : Deck.values()) {
			s += "\n Number of " + deck.toString() + " acquired :";
			for(Statistic stat : this.listStat.values()) {
				boolean i = stat.getInventoryStats().getOccurencesOfCards().containsKey(deck);
				if(i) {
					s += "\t" + stat.getInventoryStats().getOccurencesOfCards().get(deck) + "\t |";
				}
				else {
					s += "\t0 \t |";
				}
			}
		}
		
		s+="\n ----------------------------------------------------------------------------------------------------";
		
		s += "\n Total number of winning :";
		
		for(Statistic stat : this.listStat.values()) {
			s += "\t" + stat.getInventoryStats().getWinsNbr() + "\t |";
		}
		
		s += "\n Total number of equality :";
		
		for(Statistic stat : this.listStat.values()) {
			s += "\t" + stat.getInventoryStats().getEqualityNbr() + "\t |";
		}
		
		s += "\n Percentage of winning :";
		
		for(Statistic stat : this.listStat.values()) {
			stat.getInventoryStats().percentageRate(this.s.getGameNbr());
			s += "\t" + stat.getInventoryStats().getWinsPercentage() + "%\t |";
		}
		
		s += "\n Percentage of equality :";
		
		for(Statistic stat : this.listStat.values()) {
			s += "\t" + stat.getInventoryStats().getEqualityPercentage() + "%\t |";
		}
		
		return s;
	}


	
}
