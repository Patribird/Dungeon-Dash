package model.dungeon.cell;

import model.Utility;

import java.io.Serializable;

public class Wall implements Cell, Serializable {

    private final int myX;
    private final int myY;

    public Wall(final int theX, final int theY) {
        myX = theX;
        myY = theY;
    }

    @Override
    public int getX() {
        return myX;
    }

    @Override
    public int getY() {
        return myY;
    }

    @Override
    public String toString() {
        return Utility.generateSegment() + "\n" + Utility.generateSegment();
    }

}
