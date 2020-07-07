 package game;

import java.util.EnumMap;
import java.util.UUID;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import event.ChoiceForge;
import event.ChoiceExploit;
import event.ChoiceExploitForge;
import event.Protocol;
import resources.TypeResource;

/** 
 * Class that schedule the GameManager, switching between various game state. 
 **/

public class GameLoop {
	
	/** Represents the game's engine **/
	private GameManager engine;
	
	/** The step is used to know in which step of the game we are currently in */
	private GameStepLoop step;
	
	/** The turnNb represents the number of turns in a wave. **/
	private int turnNb;
	
	/** The waveNb represents the number of waves in the game. **/
	private int waveNb;
	
	/** The waveNbMax represents the maximum waves number. **/
	private int waveNbMax;
	
	/**
	 * Lock is for synchronization, here we synchronize the choiceForge event
	 */
	private Lock lock = new ReentrantLock();
	
	/**
	 * Condition is for synchronization too, works with a lock, allows to wait/notify between locks
	 */
	private Condition condition = this.lock.newCondition();
	
	/**
	 * Instantiates a new game loop.
	 * Initialize the game. 
	 *
	 * @param gameManager the engine of the game
	 */
	public GameLoop(GameManager gameManager) {
		this.step = GameStepLoop.NOT_STARTED;
		this.engine = gameManager;
		this.turnNb = 1;
		this.waveNb = 1;
		this.waveNbMax = 9;
	}
	
	/**
	 * This method represents the game loop, describes how a game should run in the right order : 
	 * 
	 * If the GameState is set to STOP, the game stops. 
	 * 
	 * If number of turn is superior than the number of players, we end the current wave by incrementing nbWaves and reseting nbTurn.
	 * Else call executeTurn() that execute a game turn
	 * 
	 * If number of waves is inferior than the maximum of waves, recursive call to execute().
	 * Else launch the gameOver()
	 * 
	 */
	public void execute() {

        if(engine.getState() == GameState.STOP) return;


        if(this.turnNb > engine.listPlayers.size()) {this.endWave();}
        else {
        	Display.getInstance().printWaveTurn(this.waveNb, this.turnNb);
            this.executeTurn();
        }

        if (this.waveNb < this.waveNbMax+1) {this.execute();}
        else {this.engine.gameOver();}

    }
	
	/**
	 * Execute a turn : in a turn, each player rolls his dices.
	 *  Then, in the same turn, the engine choose the current Player. 
	 *  The current player attributes are stored in choiceForge object.
	 *  Send to the player the ChoiceForge event with the choiceForge object filled, that will determines whether he buys an Item or not. 
	 */
	private void executeTurn() {
		
		this.step = GameStepLoop.ROLL_DICE;
		
		for(UUID id : engine.listPlayers.keySet()) {
			PlayerManager p = engine.listPlayers.get(id);
			p.rollDices();	
		}
		
		this.engine.setCurrentPlayer();
		
		this.step = GameStepLoop.FORGE;
		
		handleForgeExploit();
		
		while(this.step == GameStepLoop.FORGE) {
			this.lock.lock();
			
			try {
				this.condition.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			this.lock.unlock();
		}
				
		this.turnNb++;
		this.step = GameStepLoop.END_TURN;
	}
	/**
	 * handleForgeExploit create a ChoiceExploitForge with the current forge and islands,
	 * in order to send it to the client. 
	 */
	private void handleForgeExploit() {
		
		ChoiceExploitForge chFE = new ChoiceExploitForge();
		ChoiceExploit chE = new ChoiceExploit();
		ChoiceForge ch = new ChoiceForge();
		
		chE.setIslands(this.engine.getIslands());
		chE.setListResourcesPlayer(new EnumMap<TypeResource, Integer>(TypeResource.class));
		chE.getListResourcesPlayer().put(TypeResource.GOLD, this.engine.currentPlayer.getInventory().getNbrGold());
		chE.getListResourcesPlayer().put(TypeResource.LUNAR, this.engine.currentPlayer.getInventory().getNbrLunar());
		chE.getListResourcesPlayer().put(TypeResource.SOLAR, this.engine.currentPlayer.getInventory().getNbrSolar());
		chE.setListCard(this.engine.currentPlayer.getInventory().getMyCards());
		
		ch.setForge(this.engine.forge);
		
		ch.setGoldAmount(this.engine.currentPlayer.getInventory().getNbrGold());
		ch.setD1(this.engine.currentPlayer.getInventory().getD1());
		ch.setD2(this.engine.currentPlayer.getInventory().getD2());
		ch.setId(this.engine.currentPlayer.getPlayer().getId());
		chFE.setChE(chE);
		chFE.setChF(ch);
		
		Display.getInstance().printFaces(ch.getD1(), ch.getD2());
		
		this.engine.sendToClient(this.engine.currentPlayer.getPlayer().getId(), Protocol.CHOICE_FORGE, chFE);
	}
	
	/**
	 * End the current wave.
	 */
	private void endWave() {
		this.waveNb++;
		this.turnNb = 1;
	}
	
	/**
	 * Gets the step.
	 *
	 * @return the step
	 */
	public GameStepLoop getStep() {
		return step;
	}

	/**
	 * Sets the step.
	 *
	 * @param step the new step
	 */
	public void setStep(GameStepLoop step) {
		this.lock.lock();
		this.step = step;
		this.condition.signal();
		this.lock.unlock();
	}
	
}
