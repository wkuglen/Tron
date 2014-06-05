package TheGrid;

import info.gridworld.actor.Rock;
import java.awt.Color;

/**
 * @author Will Kuglen
 * Trail is a Colored Wrapper class for a Rock
 */
public class Trail extends Rock{
	
	/**
	 * Default constructor - Creates a rock; Basically gives a different look and color to the rock
	 * @param c intended Color of Trail, should be the same as the Bike
	 */
	public Trail(Color c)
	{
		super(c);
	}//public Trail(Color c)
}//public class Trail extends Rock
