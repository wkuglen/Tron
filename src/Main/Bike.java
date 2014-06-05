package Main;
import java.awt.Color;
import java.util.Date;

import ArtificialIntelligence.LevelA;
import ArtificialIntelligence.LevelB;
import ArtificialIntelligence.LevelC;
import TheGrid.Background;
import TheGrid.Trail;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;


public class Bike extends Bug{
	
	//Public Variables
	public static final boolean isPlayer = true;
	public static final boolean isAI = false;
	
	//Private Variables
	private boolean type;
	private int AIdegree = 3;
	private int movesRemain; //movesRemain = AIdegree 
	private Date timeOfDeath;
	
	//Constructors\\
    /**
     * 
     */
    public Bike()
    {
        setColor(Color.BLACK);
        type = isAI;
        movesRemain = AIdegree;
    }

    /**
     * Defined Constructor
     * @param bikeColor intended Color of Bike
     * @param intendedType must be isPlayer or isAI
     */
    public Bike(Color bikeColor, boolean intendedType)
    {
        setColor(bikeColor);
        type = intendedType;
        movesRemain = AIdegree;
        turn();
        turn();
    }

    /**
     * Moves if it can move, turns otherwise.
     */
    public void act()
    {
    	//update();
        if (canMove())
            move();
        else
        {
        	removeSelfFromGrid();
        	timeOfDeath = new Date();
        	//System.out.println(this);
        	TronUtil.addToList(this);
        }
        
    }

    /**
     * Moves the bug forward, putting a flower into the location it previously
     * occupied.
     */
    public void move()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return;
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        if (gr.isValid(next))
            moveTo(next);
        else
            removeSelfFromGrid();
        Trail t = new Trail(getColor());
        t.putSelfInGrid(gr, loc);
    }

    /**
     * Tests whether this bug can move forward into a location that is empty or
     * contains a flower.
     * @return true if this bug can move.
     */
    public boolean canMove()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return false;
        Location loc = getLocation();
        if(!type)//if AI
        {
        	if(AIdegree == 1)
        		setDirection(LevelA.chooseD(this));
        	if(AIdegree == 2)
        		setDirection(LevelB.chooseD(this));
        	if(AIdegree == 3)
        		setDirection(LevelC.chooseD(this));
        	
        }
        Location next = loc.getAdjacentLocation(getDirection());
        if (!gr.isValid(next))        
            return false;
        Actor neighbor = gr.get(next);
        return (neighbor instanceof Background);
    }
    
    //Utility Methods (Called periodically not continuously)\\
    /**
     * @return String version of Date when removed from grid
     */
    public String getTimeOfDeath() 
    {
    	return timeOfDeath.toString();
    }
    public int getMovesRemain()
    {
    	return movesRemain;
    }
    public void decreaseMovesRemain()
    {
    	movesRemain--;
    }
    public void resetMovesRemain()
    {
    	movesRemain = AIdegree;
    }
    /**
     * @return a String detailing the Bike
     */
    public String toString()
    {
    	String s = "";
    	if(getColor().equals(Color.CYAN))
    	{
			s += "Blue";
    	}
    	else if(getColor().equals(Color.RED))
    	{
    		s += "Red";
    	}
    	else if(getColor().equals(Color.ORANGE))
    	{
    		s += "Green";
    	}
    	else if(getColor().equals(Color.MAGENTA))
    	{
    		s += "Purple";
    	}
    	if(type == isAI)
    	{
    		s += "(AI Level "+AIdegree+")";
    	}
    	if(type == isPlayer)
    	{
    		s += "(you)";
    	}
		return s;
    	
    }
    
}
