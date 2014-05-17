import java.awt.Color;

import TheGrid.Background;
import TheGrid.Trail;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;


public class Bike extends Bug{
	/**
     * Constructs a red bug.
     */
    public Bike()
    {
        setColor(Color.BLUE);
    }

    /**
     * Constructs a bug of a given color.
     * @param bugColor the color for this bug
     */
    public Bike(Color bikeColor)
    {
        setColor(bikeColor);
    }

    /**
     * Moves if it can move, turns otherwise.
     */
    public void act()
    {
    	update();
        if (canMove())
            move();
        else
        {
        	removeSelfFromGrid();
        	System.out.println("You Lose");
        }
        
    }

    /**
     * Turns the bug 45 degrees to the right without changing its location.
     */
    public void update()
    {
        setDirection(getDirection() + Location.HALF_RIGHT);
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
        Location next = loc.getAdjacentLocation(getDirection());
        if (!gr.isValid(next))
            return false;
        Actor neighbor = gr.get(next);
        return (neighbor instanceof Background);
        // ok to move into empty location or onto flower
        // not ok to move onto any other actor
    }
}
