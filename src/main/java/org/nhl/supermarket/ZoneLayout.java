package org.nhl.supermarket;

/**
 * Created by ruben on 06/10/14.
 */
public class ZoneLayout {
    private BuyZone[][] buyZones;

    public ZoneLayout() {
        this.buyZones = new BuyZone[][] {{}};
    }

    public int[] closestCoordToDest(int xCurr, int yCurr, int xDest, int yDest) {
        if (xCurr < xDest) {
            return new int[] {xCurr+1, yCurr};
        }
        else if (xCurr > xDest) {
            return new int[] {xCurr-1, yCurr};
        }
        else if (yCurr < yDest) {
            return new int[] {xCurr, yCurr+1};
        }
        else if (yCurr > yDest) {
            return new int[] {xCurr, yCurr-1};
        }
        else {
            return new int[] {xCurr, yCurr};
        }
    }
}
