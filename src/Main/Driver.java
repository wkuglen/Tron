package Main;

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import java.awt.Color;
import java.util.Date;
import TheGrid.Background;

/**
 * @author Will Kuglen
 * Controls (Drives) the program.
 */
public class Driver {

	public static Date startTime = new Date();//time of creation of Game
	
	//private variables
	private static BoundedGrid<Actor> playingField;
	private static Bike bluePlayer;
	private static Bike orangeCPU;
	private static Bike redCPU;
	private static Bike whiteCPU;
	
	/**
	 * Main method of Tron program.  Initializes all variables and first-time set up.
	 */
	public static void main(String[] args) {
		//World set-up
		playingField = new BoundedGrid<Actor>(40, 40);
		ActorWorld aw = new ActorWorld(playingField);
		
		//player initialization
		bluePlayer = new Bike(Color.CYAN, Bike.isPlayer);
		orangeCPU = new Bike(Color.ORANGE, Bike.isAI);
		redCPU = new Bike(Color.RED, Bike.isAI);
		whiteCPU = new Bike(Color.MAGENTA, Bike.isAI);		
		
		//Starting positions of bikes
		Location bStart = new Location(0,0);
		Location oStart = new Location(playingField.getNumRows()-1,playingField.getNumCols()-1);
		Location rStart = new Location(0,playingField.getNumCols()-1);
		Location wStart = new Location(playingField.getNumRows()-1,0);
		
		//fills the grid with the Background
		for(int r = 0; r<playingField.getNumRows(); r++)
		{
			for(int c=0; c<playingField.getNumCols(); c++)
			{
				Background bg = new Background();
				bg.putSelfInGrid(playingField, new Location(r,c));
			}//for(int c=0; c<playingField.getNumCols(); c++)
		}//for(int r = 0; r<playingField.getNumRows(); r++)
		
		//placement of players
		bluePlayer.putSelfInGrid(playingField, bStart);
		orangeCPU.putSelfInGrid(playingField, oStart);
		orangeCPU.setDirection(Location.WEST);
		redCPU.putSelfInGrid(playingField, rStart);
		redCPU.setDirection(Location.SOUTH);
		whiteCPU.putSelfInGrid(playingField, wStart);
		whiteCPU.setDirection(Location.NORTH);
		
		
		/**
		 * Keyboard Listener/Control System
		 */
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
				if (key.equals("pressed E"))				
					TronUtil.endGame(playingField);
				if (key.equals("pressed R"))				
					TronUtil.resetGame(playingField, bluePlayer, orangeCPU, redCPU, whiteCPU);
				if (key.equals("pressed T"))				
					TronUtil.pressT();
				return true;
			}
		});		//Keyboard Listener/Control System
		TronUtil.beginGame();//shows instructions
		aw.show();//shows Grid	
	}
	
}
