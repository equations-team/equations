package gamestatemanager;
/**
 * Parent class for the Opponent AI and Human Player classes, any functionality that is needed
 * for both of those classes can be factored to here
 * @author derrek
 *
 */
public abstract class AbstractPlayer {
	protected Manager gsm;
	
	public AbstractPlayer(Manager gsm) {
		this.gsm = gsm;
	}
	
	/**
	 * Method that manager will call for the player to make a move
	 */
	public abstract void takeTurn();

}
