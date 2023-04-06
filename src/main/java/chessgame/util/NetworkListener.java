package chessgame.util;

// Classes implementing this interface can get notified when a message is received

public interface NetworkListener {

	/**
	 * Gest called when a message is received
	 * 
	 * @param message The message
	 */
	public void messageReceived(Message message);

}
