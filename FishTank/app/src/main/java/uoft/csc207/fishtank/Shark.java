package uoft.csc207.fishtank;

import android.graphics.Canvas;
import android.graphics.Color;

import java.util.Random;

public class Shark extends TankMember {
    /**
     * How this shark appears on the screen.
     */
    private String appearance;

    /**
     * where is it moving now
     */
    private boolean rightway;

    /**
     * Constructs a shark
     */
    Shark(int x, int y, FishTankManager tank) {
        super(x, y, tank);
        this.appearance = "[-(*>";
        this.rightway = true;
        this.paintText.setColor(Color.WHITE);
    }

    /**
     * If the shark is within 2 units from the fish it will eat it and change the color of its appearance.
     */
    void eat_fish() {
        for (int j = 0; j < this.tank.myLittleFishies.size(); j++) {
            TankMember member = this.tank.myLittleFishies.get(j);
            if (member instanceof Fish && Math.abs(this.x - member.x) < 2 && Math.abs(this.y - member.y) < 2) {
                tank.myLittleFishies.remove(member);
                Random rand = new Random();
                int r = rand.nextInt(255);
                int g = rand.nextInt(255);
                int b = rand.nextInt(255);
                int shark_color = Color.rgb(r, g, b);
                this.paintText.setColor(shark_color);
            }
        }
    }

    /**
     * turns the shark around.
     */
    private void turnAround() {
        String left = "<*)-]";
        String right = "[-(*>";
        if (this.rightway) {
            this.appearance = left;
        } else {
            this.appearance = right;
        }
        this.rightway = !this.rightway;
    }

    /**
     * Shark monitors only two levels under the water.
     */
    void move(int width) {
        if (this.rightway) {
            if (this.x < (width - 1)) {
                this.x += 1;
            } else {
                this.turnAround();
                this.y++;
            }
        } else {
            if (this.x != 0) {
                this.x -= 1;
            } else {
                this.turnAround();
                this.y--;
            }
        }
    }

    /**
     * Draws the shark.
     */
    public void draw(Canvas canvas) {
        drawString(canvas, this.appearance, this.x, this.y);
    }


}
