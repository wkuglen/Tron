package ArtificialIntelligence;

public class LevelB {

private static int movesRemain = 2 ;

	public static int chooseD(Bike bike)
	{
		if(movesRemain == 0)
		{
			movesRemain = 3;
		    	Grid<Actor> g = bike.getGrid();
		    	Location loc = bike.getLocation();
		    	Location prospective = loc.getAdjacentLocation(Location.NORTH);
			int currentDir = Location.NORTH;
		    	for(int i = 0; i <= 3; i++)//3 for possibilities
			{
				int view = 2;
				while(g.isValid(prospective) && view >= 0)
				{
					if(!(g.get(prospective) instanceof Trail) && !(g.get(prospective) instanceof Bike))
						return;
					Location temp = loc.getAdjacentLocation(currentDir);
					if(g.isValid(temp))
						prospective = temp;
					else
						return;
					view--;
				}
				if(currentDir == Location.NORTH || currentDir == Location.SOUTH)
				{
					if( Math.abs(loc.getRows()-prospective.getRows()) == 2)
					{	movesRemain--;
						return currentDir;
					}
				}
				if(currentDir == Location.EAST || currentDir == Location.WEST)
				{
					if( Math.abs(loc.getCols()-prospective.getCols()) == 2)
					{	movesRemain--;
						return currentDir;
					}
				}
				view--;
			}
			movesRemain--;
			return LevelA.chooseD(bike)
		}
		movesRemain--;
		return bike.getDirection();
	}
}
