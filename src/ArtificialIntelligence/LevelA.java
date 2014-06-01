package ArtificialIntelligence;


import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import Main.Bike;
import TheGrid.Trail;
import TheGrid.Background;

public class LevelA {
	
	private static int movesRemain = (int)(Math.random()*10)+1 ;

	public static int chooseD(Bike bike) 
	{
		if(movesRemain%2 == 0)
		{
			movesRemain = (int)(Math.random()*10)+2;
		    int choice = (int)(Math.random()*10);
		    Grid<Actor> g = bike.getGrid();
		    Location loc = bike.getLocation();
		    Location prospective = loc.getAdjacentLocation(Location.NORTH);
		    switch(choice)
		    {
		    case 1: prospective = loc.getAdjacentLocation(Location.NORTH);
		    		if(g.isValid(prospective) && g.get(prospective) instanceof Background)
		    		{	movesRemain--;
		    			return Location.NORTH;
		    		}
		            break;
			case 2: prospective = loc.getAdjacentLocation(Location.EAST);
					if(g.isValid(prospective) && g.get(prospective) instanceof Background)
					{	movesRemain--;
						return Location.EAST;
					}
					break;
			case 3: prospective = loc.getAdjacentLocation(Location.SOUTH);
					if(g.isValid(prospective) && g.get(prospective) instanceof Background)
					{	movesRemain--;
						return Location.SOUTH;
					}
					break;
			case 4: prospective = loc.getAdjacentLocation(Location.WEST);
					if(g.isValid(prospective) && g.get(prospective) instanceof Background)
					{	movesRemain--;
						return Location.WEST;
					}
					break;
			default:movesRemain--;
					return findClear(bike);					
			}
		    
		}
		movesRemain--;
		//System.out.print("{}");
		return bike.getDirection();
	}
	
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
	}
	//for find clear use adjacent empty locations possibly may have 45 degree turns
	
}
