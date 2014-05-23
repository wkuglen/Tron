package ArtificialIntelligence;

import java.util.Random;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import Main.Bike;
import TheGrid.Trail;

public class TheBrain {

	public static int chooseD(Bike bike) {
		Grid<Actor> g = bike.getGrid();
		Location loc = bike.getLocation();
		Location prospective = loc.getAdjacentLocation(bike.getDirection());
		if(g.isValid(prospective))
		{
			if( (g.get(prospective) instanceof Trail) )//|| (g.get(prospective) instanceof Bike))
			{
				if(canAvoid())
				{
					return findClear(bike);
				}
			}
		}
			// TODO Auto-generated method stub
		return potentialTurn(bike);
	}
	
	private static boolean canAvoid()
	{
		Random r = new Random();
		if(r.nextInt()%2==0)
			return true;
		return false;
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
	
	private static int potentialTurn(Bike bike)
	{
		int choice = (int)(Math.random()*10);
		Grid<Actor> g = bike.getGrid();
		Location loc = bike.getLocation();
		Location prospective = loc.getAdjacentLocation(Location.NORTH);
		switch(choice)
		{
			case 1: prospective = loc.getAdjacentLocation(Location.NORTH);
					if(g.isValid(prospective))
						return Location.NORTH;
					break;
			case 2: prospective = loc.getAdjacentLocation(Location.EAST);
					if(g.isValid(prospective))
						return Location.EAST;
					break;
			case 3: prospective = loc.getAdjacentLocation(Location.SOUTH);
					if(g.isValid(prospective))
						return Location.SOUTH;
					break;
			case 4: prospective = loc.getAdjacentLocation(Location.WEST);
					if(g.isValid(prospective))
						return Location.WEST;
					break;
		}
		return findClear(bike);
	}

}
