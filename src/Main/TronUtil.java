package Main;

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import TheGrid.Background;

public class TronUtil {
	
	private static ArrayList<Bike> theDerezzed = new ArrayList<Bike>();
	
	public static void addToList(Bike b)
	{
		theDerezzed.add(b);
	}

	public static void beginGame(Grid g)
	{
		String initMsg = "Welcome to The Grid, Tron!";
		initMsg += "\n "+"Basic Gameplay and Rules:";
		initMsg += "\n  "+"Controls:";
		initMsg += "\n   "+"'W' turns you Up";
		initMsg += "\n   "+"'A' turns you Left";
		initMsg += "\n   "+"'S' turns you Down (for what)";
		initMsg += "\n   "+"'D' turns you Right";
		initMsg += "\n   "+"'E' ends the game";
		initMsg += "\n   "+"'R' resets the game";
		initMsg += "\n   "+"'T' has a surprise (but wait until after the game)";
		initMsg += "\n  "+"Rules:";
		initMsg += "\n   "+"If a player runs into a trail, off the grid, or into its own trail";
		initMsg += "\n    "+"they are derezzed (removed from the game)";
		initMsg += "\n   "+"Last man standing wins!";
		initMsg += "\n"+"Good Luck!";
		JOptionPane.showMessageDialog(null, initMsg);
		
		
	}
	public static void resetGame(Grid g, Bike p1, Bike p2, Bike p3, Bike p4)
	{
		theDerezzed = new ArrayList<Bike>();
		
		for(int r = 0; r<g.getNumRows(); r++)
		{
			for(int c=0; c<g.getNumCols(); c++)
			{
				Background bg = new Background();
				bg.putSelfInGrid(g, new Location(r,c));
			}
		}
		
		Location oneStart = new Location(0,0);
		Location twoStart = new Location(g.getNumRows()-1,g.getNumCols()-1);
		Location threeStart = new Location(0,g.getNumCols()-1);
		Location fourStart = new Location(g.getNumRows()-1,0);
		
		p1.putSelfInGrid(g, oneStart);
		p1.setDirection(Location.EAST);
		p2.putSelfInGrid(g, twoStart);
		p2.setDirection(Location.WEST);
		p3.putSelfInGrid(g, threeStart);
		p3.setDirection(Location.SOUTH);
		p4.putSelfInGrid(g, fourStart);
		p4.setDirection(Location.NORTH);
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
		finalMsg += "\n"+theDerezzed.get(3)+" wins!";
		JOptionPane.showMessageDialog(null, finalMsg);
		System.exit(0);
	}
	
	public static void pressT()
	{
		BoundedGrid<Actor> easterEggGrid = new BoundedGrid(40,40);
		ActorWorld aw = new ActorWorld(easterEggGrid);
		Bike theRealTron = new Bike(Color.CYAN, !Bike.isAI);
		for(int r = 0; r<easterEggGrid.getNumRows(); r++)
		{
			for(int c=0; c<easterEggGrid.getNumCols(); c++)
			{
				Background bg = new Background();
				bg.putSelfInGrid(easterEggGrid, new Location(r,c));
			}
		}
		
		theRealTron.putSelfInGrid(easterEggGrid, new Location(5,2));
		aw.show();
		//Creating the Logo
		//T
		theRealTron.setDirection(Location.EAST);
		for(int i = 1; i<=7; i++)
		{
			theRealTron.act();
		}
		theRealTron.setDirection(Location.SOUTH);
		for(int i = 1; i<=10; i++)
		{
			theRealTron.act();
		}
		theRealTron.setDirection(Location.NORTHEAST);
		for(int i = 1; i<=1; i++)
		{
			theRealTron.act();
		}
		theRealTron.setDirection(Location.NORTH);
		for(int i = 1; i<=9; i++)
		{
			theRealTron.act();
		}
		theRealTron.setDirection(Location.EAST);
		for(int i = 1; i<=10; i++)
		{
			theRealTron.act();
		}
		//R
		theRealTron.setDirection(Location.SOUTH);
		for(int i = 1; i<=5; i++)
		{
			theRealTron.act();
		}
		theRealTron.setDirection(Location.WEST);
		for(int i = 1; i<=5; i++)
		{
			theRealTron.act();
		}
		theRealTron.setDirection(Location.SOUTHEAST);
		for(int i = 1; i<=5; i++)
		{
			theRealTron.act();
		}
		//N
		theRealTron.setDirection(Location.SOUTH);
		for(int i = 1; i<=12; i++)
		{
			theRealTron.act();
		}
		theRealTron.setDirection(Location.NORTHWEST);
		for(int i = 1; i<=3; i++)
		{
			theRealTron.act();
		}
		theRealTron.setDirection(Location.NORTH);
		for(int i = 1; i<=3; i++)
		{
			theRealTron.act();
		}
		theRealTron.setDirection(Location.NORTHWEST);
		for(int i = 1; i<=3; i++)
		{
			theRealTron.act();
		}
		theRealTron.setDirection(Location.SOUTH);
		for(int i = 1; i<=9; i++)
		{
			theRealTron.act();
		}
		//O
		theRealTron.setDirection(Location.WEST);
		for(int i = 1; i<=6; i++)
		{
			theRealTron.act();
		}
		theRealTron.setDirection(Location.NORTHEAST);
		for(int i = 1; i<=3; i++)
		{
			theRealTron.act();
		}
		theRealTron.setDirection(Location.NORTH);
		for(int i = 1; i<=3; i++)
		{
			theRealTron.act();
		}
		theRealTron.setDirection(Location.NORTHWEST);
		for(int i = 1; i<=3; i++)
		{
			theRealTron.act();
		}
		theRealTron.setDirection(Location.WEST);
		for(int i = 1; i<=3; i++)
		{
			theRealTron.act();
		}
		theRealTron.setDirection(Location.SOUTHWEST);
		for(int i = 1; i<=3; i++)
		{
			theRealTron.act();
		}
		theRealTron.setDirection(Location.SOUTH);
		for(int i = 1; i<=3; i++)
		{
			theRealTron.act();
		}
		theRealTron.setDirection(Location.SOUTHEAST);
		for(int i = 1; i<=3; i++)
		{
			theRealTron.act();
		}
		theRealTron.setDirection(Location.EAST);
		for(int i = 1; i<=2; i++)
		{
			theRealTron.act();
		}
		JOptionPane.showMessageDialog(null, "Tron by Will Kuglen");
		theRealTron.removeSelfFromGrid();
		System.exit(0);
	}
}
