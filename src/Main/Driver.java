package Main;

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.grid.UnboundedGrid;
import info.gridworld.world.World;

import java.awt.Color;

import TheGrid.Background;

public class Driver {

	private static Bike bluePlayer;
	private static Bike orangeCPU;
	private static Bike redCPU;
	private static Bike whiteCPU;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BoundedGrid<Actor> playingField = new BoundedGrid<Actor>(40, 40);
		ActorWorld aw = new ActorWorld(playingField);
		
		bluePlayer = new Bike(Color.CYAN, Bike.isPlayer);
		orangeCPU = new Bike(Color.ORANGE, Bike.isAI);
		redCPU = new Bike(Color.RED, Bike.isAI);
		whiteCPU = new Bike(Color.MAGENTA, Bike.isAI);
		
		
		Location bStart = new Location(0,0);
		Location oStart = new Location(playingField.getNumRows()-1,playingField.getNumCols()-1);
		Location rStart = new Location(0,playingField.getNumCols()-1);
		Location wStart = new Location(playingField.getNumRows()-1,0);
		
		
		for(int r = 0; r<playingField.getNumRows(); r++)
		{
			for(int c=0; c<playingField.getNumCols(); c++)
			{
				Background bg = new Background();
				bg.putSelfInGrid(playingField, new Location(r,c));
			}
		}
		bluePlayer.putSelfInGrid(playingField, bStart);
		orangeCPU.putSelfInGrid(playingField, oStart);
		orangeCPU.setDirection(Location.WEST);
		redCPU.putSelfInGrid(playingField, rStart);
		redCPU.setDirection(Location.SOUTH);
		whiteCPU.putSelfInGrid(playingField, wStart);
		whiteCPU.setDirection(Location.NORTH);
		
		
		
		java.awt.KeyboardFocusManager.getCurrentKeyboardFocusManager()
		.addKeyEventDispatcher(new java.awt.KeyEventDispatcher() {
			public boolean dispatchKeyEvent(java.awt.event.KeyEvent event) {
				String key = javax.swing.KeyStroke.getKeyStrokeForEvent(event).toString();
				if (key.equals("pressed W"))
					bluePlayer.setDirection(Location.NORTH);
				if (key.equals("pressed D"))
					bluePlayer.setDirection(Location.EAST);
				if (key.equals("pressed S"))
					bluePlayer.setDirection(Location.SOUTH);
				if (key.equals("pressed A"))
					bluePlayer.setDirection(Location.WEST);
				/*if (key.equals("pressed W"))
					orangeCPU.setDirection(Location.NORTH);
				if (key.equals("pressed A"))
					orangeCPU.setDirection(Location.WEST);
				if (key.equals("pressed S"))
					orangeCPU.setDirection(Location.SOUTH);
				if (key.equals("pressed D"))
					orangeCPU.setDirection(Location.EAST);*/
				return true;
			}
		});
		
		aw.show();

	}
	

}
