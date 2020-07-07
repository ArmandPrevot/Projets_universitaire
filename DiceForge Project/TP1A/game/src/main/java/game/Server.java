package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.fasterxml.jackson.databind.ObjectMapper;

import event.ChoiceExploitForge;
import event.ChoiceFace;
import event.ChoiceForge;
import event.Protocol;
import game.Display;
import interfaces.IServer;
import resources.Statistic;

// TODO: Auto-generated Javadoc
/**
 * The Class Server handles the server socket and interactions with the engine.
 */

public class Server implements Runnable, ConnectListener, DisconnectListener, IServer {
	
	/**  The engine of the game. */
	GameManager engine;
	
	/** The server. */
	SocketIOServer server;
	
	/**  Use for synchronization. */
	private Lock lock = new ReentrantLock();
	
	/**  For wait/notify on lock. */
    private Condition condition = this.lock.newCondition();
	
	/**  Boolean to know whether the socket server is running or not. */
	private boolean running;
	
	/** Determines how many games will be launch in a row. */
	private int gameNbr;
	
	/**
	 * Instantiates a new server, setting the server on the game.
	 *
	 * @param nbr of game to launch
	 */
	public Server(int nbr) {
		this.setRunning(false);
		this.engine = new GameManager(this);
		this.gameNbr = nbr;
    }
	

	/**
	 * When a Client disconnect from the server, prints his id.
	 *
	 * @param client the client that is not connected anymore
	 */
	@Override
	public void onDisconnect(SocketIOClient client) {
		Display.getInstance().printOnDisconnectClient(client);
	}

	/**
	 * When a Client connect on the server, adds new Player to the game with his SocketUUID. 
	 * When the total amount of player is 4, launches the game by unlocking the wait in the run() method.
	 * 
	 * @param client the client
	 */
	@Override
	public void onConnect(SocketIOClient client) {
		
		this.engine.addNewPlayer(client.getSessionId());
		
		String str = client.getHandshakeData().getSingleUrlParam("version");
		this.engine.addAINameToPlayerName(str, client.getSessionId());
		
		if(this.engine.listPlayers.size() == 4) {
		//	this.engine.stat = new Statistic(new ArrayList<>(this.engine.listPlayers.values()));
		//	System.out.println(this.engine.stat.toString());
			this.lock.lock();
			this.condition.signal();
			this.lock.unlock();
		}
	}

	/**
	 * Define what's running in the the Server Thread,
	 * Configure the server socket,
	 * Subscribe the server socket to game events. 
	 * The thread waits until the gameNbr games has been played 
	 * Stop afterwards.
	 * 
	 */
	@Override
	public void run() {
		Configuration config = new Configuration();
		config.setHostname("127.0.0.1");
		config.setPort(11000);
		
		this.server = new SocketIOServer(config);
		server.addConnectListener(this);
		server.addDisconnectListener(this);
		this.initEvent();
		server.start();
		this.setRunning(true);
		
		synchronized(this) {
			this.notify();
		}
		
		this.lock.lock();
		
		try {
			this.condition.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.lock.unlock();
		
		for(int i=0; i<this.gameNbr; i++) {
			Display.getInstance().startGame(i+1);
			this.engine.play();
			this.engine.reset();
		}
		
		Display.getInstance().printStats(this.engine);
		server.stop();
	}
	
	/**
	 * Gets the game nbr.
	 *
	 * @return the game nbr
	 */
	public int getGameNbr() {
		return gameNbr;
	}


	/**
	 * Sets the game nbr.
	 *
	 * @param gameNbr the new game nbr
	 */
	public void setGameNbr(int gameNbr) {
		this.gameNbr = gameNbr;
	}


	/**
	 * Checks if is running.
	 *
	 * @return true, if is running
	 */
	public boolean isRunning() {
		return running;
	}

	/**
	 * Initialize the server socket's subscriptions to game events.
	 */
	public void initEvent() {
		this.server.addEventListener(Protocol.CHOICE_FORGE.name(), String.class, new DataListener<String>() {

			@Override
			public void onData(SocketIOClient client, String data, AckRequest ackSender) throws Exception {
				ChoiceExploitForge ch = new ObjectMapper().readValue(data, ChoiceExploitForge.class);
				
				engine.handleExploitForge(ch);
				
			}
			
		});
		
		this.server.addEventListener(Protocol.CHOICE_FACE.name(), String.class, (socketIOClient, choiceFace, ackRequest) -> {
            ChoiceFace choice = new ObjectMapper().readValue(choiceFace, ChoiceFace.class);
            engine.choiceFace(choice, socketIOClient.getSessionId());
        });
	}
	
	/**
	 * Sets the running.
	 *
	 * @param running the new running
	 */
	public void setRunning(boolean running) {
		this.running = running;
	}
	
	/**
	 * Send to client.
	 *
	 * @param id the identifier of the Player
	 * @param p the Protocol used
	 * @param o the object to send
	 */
	public void sendToClient(UUID id, Protocol p, Object o) {
		this.server.getClient(id).sendEvent(p.name(), o);
	}

}
