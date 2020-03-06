package uoft.csc207.fishtank;

import android.graphics.Canvas;
import android.graphics.Color;

public class Seaweed extends TankMember {

    /**
     * The number of weed segments.
     */
    private int l;

    /**
     * Indicates whether the bottom segment is leaning right.
     */
    private boolean leanRight;

    /**
     * Constructs a new seaweed item at the specified cursor
     * location (x,y),l segments tall.
     */
    Seaweed(int x, int y, FishTankManager Tank, int l) {
        super(x, y, Tank);
        this.l = l;
        this.paintText.setColor(Color.GREEN);
    }

    /**
     * Draws this Seaweed.
     */
    public void draw(Canvas canvas) {

        // WWhich way does the first segment lean?
        boolean lr = this.leanRight;
        String even = "/"; //even parts of seaweed leaning right
        String odd = "\\"; //odd parts of seaweed leaning left
        //if not leanRight, then need to swap even and odd
        if (!lr) {
            even = "\\";
            odd = "/";
        }
        for (int i = 0; i < l; i += 2) {
            drawString(canvas, even, this.x, (this.y - i));
            if (i + 1 < l)
                drawString(canvas, odd, this.x, (this.y - (i + 1)));
        }
    }

    /**
     * Changes the leaning direction of this Seaweed.
     */
    void wave() {
        this.leanRight = !this.leanRight;
    }

}