package uoft.csc207.fishtank;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

abstract class TankMember {

    /**
     * This TankMember's first coordinate, (represent the width in the tank).
     */
    int x;
    /**
     * This TankMember's second coordinate, (represent the height in the tank).
     */
    int y;
    /**
     * This TankMember's FishTankManager, where this TankMember lie.
     */
    FishTankManager tank;


    Paint paintText = new Paint();

    /**
     * Constructs TankMember at the position (x,y)
     */
    TankMember(int x, int y, FishTankManager Tank) {
        this.x = x;
        this.y = y;
        this.paintText.setTextSize(36);
        this.paintText.setTypeface(Typeface.DEFAULT_BOLD);
        this.tank = Tank;
    }

    /**
     * Draws this fish tank item.
     */

    public abstract void draw(Canvas canvas);

    /**
     * Draws the given string in the given graphics context at
     * at the given cursor location.
     */

    void drawString(Canvas canvas, String s, int x, int y) {
        canvas.drawText(s, x * FishTankView.charWidth, y * FishTankView.charHeight, paintText);
    }
}
