package event;

 
/**
 * The Enum Protocol defines all transmitted events between Client and Server.
 */
public enum Protocol {
	/** Identification protocol to identify which client use which AI */
	IDENTIFICATION,
	/** The choice forge is the name given to the event associated, which is choosing to buy an Item in a the Forge. */
	
	CHOICE_FORGE,
	CHOICE_FACE;
}
