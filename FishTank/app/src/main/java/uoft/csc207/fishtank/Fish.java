package uoft.csc207.fishtank;

import android.graphics.Canvas;
import android.graphics.Color;

/**
 * A fish.
 */
public class Fish extends TankMember {

    /**
     * How this fish appears on the screen.
     */
    private String appearance;

    /**
     * Indicates whether this fish is moving right.
     */

    private boolean goingRight;

    /**
     * Indicates whether this fish is hungry or not.
     */

    private boolean hungry;

    /**
     * Constructs a new fish at the position(x,y)
     */
    Fish(int x, int y, FishTankManager tank) {
        super(x, y, tank);
        this.appearance = "><>";
        this.paintText.setColor(Color.CYAN);
        this.goingRight = true;
        this.hungry = false;

    }

    /**
     * Constructs a fish with hungriness element
     */
    Fish(int x, int y, FishTankManager tank, boolean hungry) {
        super(x, y, tank);
        this.appearance = "><>";
        this.goingRight = true;
        this.hungry = hungry;
        if (this.hungry) {
            this.paintText.setColor(Color.YELLOW);
        } else
            this.paintText.setColor(Color.CYAN);
    }

    /**
     * gets this fish hungry.
     */
    void getsHungry() {
        this.hungry = true;
        this.paintText.setColor(Color.YELLOW);
    }

    /**
     * gets this fish full.
     */
    void getsFull() {
        this.hungry = false;
        this.paintText.setColor(Color.CYAN);
    }

    /**
     * Causes this fish to blow a bubble.
     */
    private void blowBubble() {
        Bubble b = new Bubble(this.x, this.y, this.tank);
        System.out.println(this.x + " " + this.y);

        //FishTankManager.myLittleFishies[this.x][this.y] = b;
        this.tank.myLittleFishies.add(b);
    }


    /**
     * Build and initialize this fish's forward and backward
     * appearances.
     */
    private void reverseAppearance() {
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < this.appearance.length(); i++) {
            switch (this.appearance.charAt(i)) {
                case '>':
                    temp.append('<');
                    break;
                case '<':
                    temp.append('>');
                    break;
            }
        }
        this.appearance = temp.toString();
    }


    /**
     * Turns this fish around, causing it to reverse direction.
     */
    private void turnAround() {
        this.goingRight = !this.goingRight;
        this.reverseAppearance();
    }

    /**
     * Draws this fish tank item.
     */
    public void draw(Canvas canvas) {
        drawString(canvas, this.appearance, this.x, this.y);
    }

    /**
     * decide if fish moves right or left, cannot bumped into a wall, should turn around before that.
     */
    private void horiz_move(int width) {
        if (this.goingRight) {
            if (this.x < (width - 1)) {
                this.x += 1;
            } else {
                this.turnAround();
            }
        } else {
            if (this.x != 0) {
                this.x -= 1;
            } else {
                this.turnAround();
            }
        }
    }

    /**
     * decide if fish moves up or down, cannot bumped into a wall, should move in the other
     * direction before that. If d > 0.7 try to go up; <0.3 try to go down, otherwise don't do
     * anything
     */
    private void vertic_move(int height, double d) {
        if (d > 0.7) {
            if (this.y < height - 1) {
                this.y += 1;
            } else {
                this.y--;
            }
        } else if (d < 0.3) {
            if (this.y != 0) {
                this.y -= 1;
            } else {
                this.y++;
            }
        }
    }

    /**
     * Causes this item to take its turn in the fish-tank simulation.
     */
    void move(int width, int height) {

        //choose if fish turn around.
        double d = Math.random();
        if (d < 0.04) {
            this.turnAround();
        }

        // Decide in which horizontal direction fish goes.
        this.horiz_move(width);

        // Decide if fish blows a bobble.
        if (d < 0.1) {
            this.blowBubble();
        }

        // Figure out whether to move up or down, or neither.
        this.vertic_move(height, d);
    }
}
