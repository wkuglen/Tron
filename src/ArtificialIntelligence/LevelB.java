package ArtificialIntelligence;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import Main.Bike;
import TheGrid.Background;

/**
 * @author Will Kuglen
 * Normal AI class (Medium)
 */
public class LevelB {

	/**
	 * @param bike to be analyzed
	 * @return location by looking 2 spaces ahead of current direction
	 */
	public static int chooseD(Bike bike)
	{
		Grid<Actor> g = bike.getGrid();
	    Location loc = bike.getLocation();
		if(bike.getMovesRemain() <= 0)
		{
			int currentDir = bike.getDirection()+(int)(Math.random()*4)*90;//to increase randomness of direction
		    Location prospective = loc.getAdjacentLocation(currentDir);
			
		    for(int i = 0; i <= 3; i++)//3 for possibilities; 4 iterations N, E, S, W
			{
		    	prospective = loc.getAdjacentLocation(currentDir);
		    	boolean validMove = false;// guilty until proven innocent - safeguards against looking outside of the grid
				int view = 1;//number from next location
				while(g.isValid(prospective) && view > 0 )//should exit after one iteration set up this way for LevelC
				{
					if(g.get(prospective) instanceof Background)
					{
						Location temp = prospective.getAdjacentLocation(currentDir);
						if(g.isValid(temp) && g.get(temp) instanceof Background)
						{
							validMove = true;
							view--;
							prospective = temp;
						}//if(g.isValid(temp) && g.get(temp) instanceof Background)
						else
						{
							view = -1;
							validMove = false;
						}//else of if(g.isValid(temp) && g.get(temp) instanceof Background)				
					}//if(g.get(prospective) instanceof Background)
					else
					{
						view = -1;
						validMove = false;
					}//else of if(g.get(prospective) instanceof Background)
				}//while(g.isValid(prospective) && view > 0 )
				if(validMove)
				{
					//FOR DEBUGGING ONLY: System.out.println(bike+"I'm at"+loc+"looking at"+prospective);
					bike.resetMovesRemain();
					return currentDir;
				}//if(validMove)
				else//if(!stillValid)
				{
					currentDir += 90;
					if(currentDir >= 360)
						currentDir = 0 + 90*(currentDir/90-4);
					//FOR DEBUGGING ONLY: System.out.println(bike.toString()+bike.getDirection()+"turning to"+currentDir);
				}//else of if(validMove)
			}//for
			bike.resetMovesRemain();
			//Degraded in case of no easy way out
			return LevelA.chooseD(bike);
		}//if(bike.getMovesRemain() <= 0)
		bike.decreaseMovesRemain();
		if(g.isValid(loc.getAdjacentLocation(bike.getDirection())) && g.get(loc.getAdjacentLocation(bike.getDirection())) instanceof Background)
			return bike.getDirection();
		else		
			return LevelA.chooseD(bike);
	}//public static int chooseD(Bike bike)
}//public class LevelB
