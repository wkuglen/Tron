package Main;
import java.awt.Color;
import java.util.Date;

import ArtificialIntelligence.LevelA;
import TheGrid.Background;
import TheGrid.Trail;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;


public class Bike extends Bug{
	
	public static final boolean isPlayer = true;
	public static final boolean isAI = false;
	
	private boolean type;
	private int AIdegree = 1;
	private Date timeOfDeath;
	
	/**
     * Constructs a red bug.
     */
    public Bike()
    {
        setColor(Color.BLACK);
        type = isAI;
    }

    /**
     * Constructs a bug of a given color.
     * @param bugColor the color for this bug
     */
    public Bike(Color bikeColor, boolean intendedType)
    {
        setColor(bikeColor);
        type = intendedType;
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
        	System.out.println(this);
        	TronUtil.addToList(this);
        }
        
    }

    /**
     * Turns the bug 45 degrees to the right without changing its location.
     */
    

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
        	setDirection(LevelA.chooseD(this));
        	
        }
        Location next = loc.getAdjacentLocation(getDirection());
        if (!gr.isValid(next))
            return false;
        Actor neighbor = gr.get(next);
        return (neighbor instanceof Background);
        // ok to move into empty location or onto flower
        // not ok to move onto any other actor
    }
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
    public String getTimeOfDeath()
    {
    	return timeOfDeath.toString();
    }
}
