package ArtificialIntelligence;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import Main.Bike;
import TheGrid.Trail;
import TheGrid.Background;

/**
 * @author Will Kuglen
 * Basic AI class (Easy)
 */
public class LevelA {
	
	/**
	 * @param bike to be analyzed
	 * @return chosen direction
	 */
	public static int chooseD(Bike bike) 
	{
		int choice = (int)(Math.random()*100);
		Grid<Actor> g = bike.getGrid();
	    Location loc = bike.getLocation();
	    Location prospective = loc.getAdjacentLocation(Location.NORTH);
	    switch(choice)
		{
		case 1: prospective = loc.getAdjacentLocation(Location.NORTH);
	    		if(g.isValid(prospective) && g.get(prospective) instanceof Background)
	    		{	
	    			return Location.NORTH;
		    	}
		        break;
		case 2: prospective = loc.getAdjacentLocation(Location.EAST);
				if(g.isValid(prospective) && g.get(prospective) instanceof Background)
				{	
					return Location.EAST;
				}
				break;
		case 3: prospective = loc.getAdjacentLocation(Location.SOUTH);
				if(g.isValid(prospective) && g.get(prospective) instanceof Background)
				{	
					return Location.SOUTH;
				}
				break;
		case 4: prospective = loc.getAdjacentLocation(Location.WEST);
				if(g.isValid(prospective) && g.get(prospective) instanceof Background)
				{	
					return Location.WEST;
				}
				break;
		default:return findClear(bike);					
		}//switch(choice)
		return bike.getDirection();
	}//public static int chooseD(Bike bike) 
	
	/**
	 * separate method to make chooseD easier to read
	 * @param bike to be analyzed
	 * @return first clear path
	 */
	private static int findClear(Bike bike)
	{
		Grid<Actor> g = bike.getGrid();
		Location loc = bike.getLocation();
		Location prospective = loc.getAdjacentLocation(Location.NORTH);
		if(g.isValid(prospective))
			if(!(g.get(prospective) instanceof Trail) && !(g.get(prospective) instanceof Bike))
				return Location.NORTH;
		prospective = loc.getAdjacentLocation(Location.EAST);
		if(g.isValid(prospective))
			if(!(g.get(prospective) instanceof Trail) && !(g.get(prospective) instanceof Bike))
				return Location.EAST;
		prospective = loc.getAdjacentLocation(Location.SOUTH);
		if(g.isValid(prospective))
			if(!(g.get(prospective) instanceof Trail) && !(g.get(prospective) instanceof Bike))
				return Location.SOUTH;
		prospective = loc.getAdjacentLocation(Location.WEST);
		if(g.isValid(prospective))
			if(!(g.get(prospective) instanceof Trail) && !(g.get(prospective) instanceof Bike))
				return Location.WEST;
		return bike.getDirection();
	}//private static int findClear(Bike bike)
	
}//public class LevelA
