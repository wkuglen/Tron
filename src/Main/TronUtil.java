package Main;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class TronUtil {
	
	private static ArrayList<Bike> theDerezzed = new ArrayList<Bike>();
	
	public static void addToList(Bike b)
	{
		theDerezzed.add(b);
	}

	public static void endGame(Grid g)
	{
		ArrayList<Location> actorLoc = g.getOccupiedLocations();
		for(Location l: actorLoc)
		{
			if(g.get(l) instanceof Bike)
			{
				JOptionPane.showMessageDialog(null, "Players still remain. Get back in there!");
				return;
			}
		}
		String finalMsg = "Tron, you were derezzed!";
		finalMsg += "\n   "+Driver.startTime.toString();
		finalMsg += "\n   "+theDerezzed.get(0)+" was Derezzed at "+theDerezzed.get(0).getTimeOfDeath();
		finalMsg += "\n   "+theDerezzed.get(1)+" was Derezzed at "+theDerezzed.get(1).getTimeOfDeath();
		finalMsg += "\n   "+theDerezzed.get(2)+" was Derezzed at "+theDerezzed.get(2).getTimeOfDeath();
		finalMsg += "\n   "+theDerezzed.get(3)+" was Derezzed at "+theDerezzed.get(3).getTimeOfDeath();
		finalMsg += "\n   "+theDerezzed.get(3)+" wins!";
		JOptionPane.showMessageDialog(null, finalMsg);
		System.exit(0);
	}
	
}
