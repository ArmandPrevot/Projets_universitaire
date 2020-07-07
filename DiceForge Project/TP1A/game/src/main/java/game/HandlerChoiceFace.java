package game;

import java.util.UUID;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import event.ChoiceFace;
import event.Protocol;
import face.HybridFace;
import interfaces.IGameManager;
import interfaces.IPlayerManager;

/**
 * HandlerChoiceFace handles the choiceFace event.
 */
public class HandlerChoiceFace {
	
	/**
	 * Lock is for synchronization, here we synchronize the choiceFace event
	 */
	private Lock lock = new ReentrantLock();
	
	/**
	 * Condition is for synchronization too, works with a lock, allows to wait/notify between locks
	 */
	private Condition condition = this.lock.newCondition();
	
	
    /**
     * ChoiceFace is the object that will be sent to the client (also the one that'll be received from him).
     * </br>
     * </br>
     * It contains the face with a choice for the sending, and the face chosen on receiving.
     */
    private ChoiceFace chFace;
    
    /**
     * Represents the face containing two faces to choose between
     */
    private HybridFace hybridFace;
    
    /**
     * idPlayer is the id of the player
     */
    private UUID idPlayer;
    
    /**
     * Here to transmit responsibility to the GameManager of the choiceFace sending 
     */
    private IGameManager g;
    
    /**
     * Here to transmit responsibility to the PlayerManager of adding resources linked to the selected face 
     */
    private IPlayerManager p;
    
    public HandlerChoiceFace(HybridFace hyF, UUID id, IGameManager g1, IPlayerManager p1) {
    	this.hybridFace = hyF;
    	this.idPlayer = id;
    	this.g = g1;
    	this.p = p1;
    }
    
    /**
     * First step in the face's choice event,
     * instantiate a new ChoiceFace object containing the face with a choice,
     * sends to the client the ChoiceFace object in order to make him choose between the two faces of the hybrid one. 
     * Calls waitingDecision() that waits until the Client responds. 
     * When, the client has answered, prints its choice and add the resources corresponding to the face selected.
     */
    public void execute() {
    	ChoiceFace cF = new ChoiceFace();
    	cF.setFace(this.hybridFace);
    	
    	this.g.sendToClient(idPlayer, Protocol.CHOICE_FACE, cF);
    	
    	while(chFace == null) {
    		this.waitingDecision();
    	}
    	
		Display.getInstance().choiceFace(chFace.getChoiceFace());
    	this.p.getInventory().addRessources(chFace.getChoiceFace());
    }
	
    /**
     * waitingDecision use synchronization (lock + condition.wait) in order to wait for the client's choice (which face he wants). 
     * The wait is interrupt with the notifyDecision() method.
     */
	private void waitingDecision() {
		this.lock.lock();
		
		try {
			this.condition.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.lock.unlock();
	}
	
	/**
	 * notifyDecision() unlocks the waitDecision() methods when the client has answered.
	 */
	public void notifyDecision() {
		this.lock.lock();
		this.condition.signal();
		this.lock.unlock();
	}
	
	/**
	 * handleChoiceFace is used to transmit the client's choice,
	 * this method is only triggered when the client has responded.
	 * 
	 * @param ch is the object containing the client's choice (the face he wants),
	 * we store his choice and unlocks the thread awaiting for his decision by calling notifyDecision().
	 */
	public void handleChoiceFace(ChoiceFace ch) {
		this.chFace = ch;
		this.notifyDecision();
	}

}
