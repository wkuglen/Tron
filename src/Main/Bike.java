package Main;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.Color;
import java.util.Date;
import ArtificialIntelligence.LevelA;
import ArtificialIntelligence.LevelB;
import ArtificialIntelligence.LevelC;
import TheGrid.Background;
import TheGrid.Trail;

/**
 * @author Will Kuglen
 * Bikes are the game pieces and constantly move to account for the 
 * constant movement in real life
 * Extends Bug
 */
public class Bike extends Bug{
	
	//Public Variables
	public static final boolean isPlayer = true;
	public static final boolean isAI = false;
	
	//Private Variables
	private boolean type;
	private int AIdegree;
	private int movesRemain; //movesRemain = AIdegree 
	private Date timeOfDeath;
	
	//Constructors\\
    /**
     * Default Constructor - Creates a default, black bike with a random AI
     */
    public Bike()
    {
        setColor(Color.BLACK);
        type = isAI;
        AIdegree = (int)(Math.random()*3)+1;
        movesRemain = AIdegree;
    }//public Bike()
    /**
     * Defined Constructor - if AI random AI level created;
     * @param bikeColor intended Color of Bike
     * @param intendedType must be isPlayer or isAI
     */
    public Bike(Color bikeColor, boolean intendedType)
    {
        setColor(bikeColor);
        type = intendedType;
        AIdegree = (int)(Math.random()*3)+1;
        movesRemain = AIdegree;
        turn();//so the bike initially faces right
        turn();
    }//public Bike(Color bikeColor, boolean intendedType)

    //Movement Methods\\
    /**
     * Moves if it can move, becomes Derezzed otherwise.
     */
    public void act()
    {
        if (canMove())
            move();
        else
        {
        	Grid g = getGrid();
        	Location grave = getLocation();
        	removeSelfFromGrid();
        	timeOfDeath = new Date();
        	TronUtil.addToList(this);
        	Trail t = new Trail(getColor());
            t.putSelfInGrid(g, grave);
        }//else of if (canMove())       
    }//public void act()

    /**
     * Moves the bike forward, putting a trail into the location it previously
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
    }//public void move()

    /**
     * Tests whether this bike can move forward based on what the AI system tells it its direction
     * @return true if this bike can move.
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
        }//if(!type)//if AI
        Location next = loc.getAdjacentLocation(getDirection());
        if (!gr.isValid(next))        
            return false;
        Actor neighbor = gr.get(next);
        return (neighbor instanceof Background);
    }//public boolean canMove()
    
    //Utility Methods (Called periodically not continuously)\\
    /**
     * @return String version of Date when removed from grid
     */
    public String getTimeOfDeath() 
    {
    	return timeOfDeath.toString();
    }//public String getTimeOfDeath() 
    
    /**
     * @return a String detailing the Bike
     */
    public String toString()
    {
    	String s = "";
    	if(getColor().equals(Color.CYAN))
    	{
			s += "Blue";
    	}//if(getColor().equals(Color.CYAN))
    	else if(getColor().equals(Color.RED))
    	{
    		s += "Red";
    	}//else if(getColor().equals(Color.RED))
    	else if(getColor().equals(Color.ORANGE))
    	{
    		s += "Green";
    	}//else if(getColor().equals(Color.ORANGE))
    	else if(getColor().equals(Color.MAGENTA))
    	{
    		s += "Purple";
    	}//else if(getColor().equals(Color.MAGENTA))
    	else //unknown color
    	{
    		s += "Unknown color";
    	}//else //unknown color
    	if(type == isAI)
    	{
    		s += "(AI Level "+AIdegree+")";
    	}//if(type == isAI)
    	if(type == isPlayer)
    	{
    		s += "(you)";
    	}//if(type == isPlayer)
		return s;   	
    }//public String toString()
    
    //Moves Remain Methods\\
    /**
     * @return the number of movesRemaining
     */
    public int getMovesRemain()
    {
    	return movesRemain;
    }//public int getMovesRemain()
    
    /**
     * decreases the number of movesRemaining
     */
    public void decreaseMovesRemain()
    {
    	movesRemain--;
    }//public void decreaseMovesRemain()
    
    /**
     * resets the movesRemaining to the original value, AI degree
     */
    public void resetMovesRemain()
    {
    	movesRemain = AIdegree;
    }//public void resetMovesRemain()
     
}//public class Bike extends Bug
