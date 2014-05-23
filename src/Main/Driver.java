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

	static Bike bluePlayer;
	static Bike orangeCPU;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BoundedGrid<Actor> playingField = new BoundedGrid<Actor>(40, 40);
		ActorWorld aw = new ActorWorld(playingField);
		
		
		bluePlayer = new Bike(Color.BLUE, Bike.isPlayer);
		orangeCPU = new Bike(Color.ORANGE, Bike.isAI);
		
		
		Location bStart = new Location(0,0);
		Location oStart = new Location(playingField.getNumRows()-1,playingField.getNumCols()-1);
		
		
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
		
		
		
		java.awt.KeyboardFocusManager.getCurrentKeyboardFocusManager()
		.addKeyEventDispatcher(new java.awt.KeyEventDispatcher() {
			public boolean dispatchKeyEvent(java.awt.event.KeyEvent event) {
				String key = javax.swing.KeyStroke.getKeyStrokeForEvent(event).toString();
				if (key.equals("pressed UP"))
					bluePlayer.setDirection(Location.NORTH);
				if (key.equals("pressed RIGHT"))
					bluePlayer.setDirection(Location.EAST);
				if (key.equals("pressed DOWN"))
					bluePlayer.setDirection(Location.SOUTH);
				if (key.equals("pressed LEFT"))
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
