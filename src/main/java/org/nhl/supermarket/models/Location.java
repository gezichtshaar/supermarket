package org.nhl.supermarket.models;

import org.nhl.supermarket.interfaces.BuyZone;

/**
 * Created by ruben on 06/10/14.
 */
public class Location {
    private BuyZone[][] buyZones;
    private int x, y;

    public Location(BuyZone[][] buyZones) {
    	this(buyZones, 0, 0);
    }
    
    public Location(BuyZone[][] buyZones, int x, int y) {
        this.buyZones = buyZones;
        this.x = x;
        this.y = y;
    }

    public void moveToDest(int xDest, int yDest) {
        if (x < xDest) {
            x++;
        }
        else if (x > xDest) {
            y++;
        }
        else if (y < yDest) {
            x++;
        }
        else if (y > yDest) {
            y--;
        }
    }
    
    public BuyZone getBuyZone() {
		return buyZones[x][y];
	}
}
