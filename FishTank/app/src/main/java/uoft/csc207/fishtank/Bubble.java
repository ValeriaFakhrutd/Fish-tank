package uoft.csc207.fishtank;

import android.graphics.Canvas;
import android.graphics.Color;

/**
 * A bubble.
 */
public class Bubble extends TankMember {

    /**
     * How this bubble appears on the screen.
     */
    private String appearance;

    /**
     * Constructs a new bubble at the specified cursor location (x, y).
     */
    Bubble(int x, int y, FishTankManager Tank) {
        super(x, y, Tank);
        this.paintText.setColor(Color.LTGRAY);
        this.appearance = ".";
    }


    /**
     * Draws the bubble.
     */
    public void draw(Canvas canvas) {
        drawString(canvas, this.appearance, this.x, this.y);
    }

    /**
     * Causes this bubble to move upwards, upwards left or upwards right with probability p = 1/3.
     */

    void floatDirection() {
        // Always move upwards.
        this.y--;
        double grow = Math.random();
        if (grow < 0.05) {
            if (this.appearance.equals(".")) this.appearance = "o";
            else this.appearance = "O";
        }
        double d = Math.random();
        if (d >= 0.66) {
            this.x--; //move to the left.
        } else if (d >= 0.33) {
            this.x++;  //move to the right.
        }
    }

}
