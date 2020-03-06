package uoft.csc207.fishtank;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

class FishTankManager {

    /**
     * All the TankMembers in the fish tank.
     */
    List<TankMember> myLittleFishies;
    /**
     * The height of the tank.
     */
    private int gridheight;
    /**
     * The width of the tank.
     */
    private int gridwidth;

    /**
     * The fish tank manager.
     */
    FishTankManager(int width, int height) {
        this.gridwidth = width;
        this.gridheight = height;
        myLittleFishies = new ArrayList<>();
    }

    void draw(Canvas canvas) {
        for (TankMember member : myLittleFishies) {
            member.draw(canvas);
        }
    }

    void update() {
        for (int j = 0; j < myLittleFishies.size(); j++) {
            TankMember member = myLittleFishies.get(j);
            if (member instanceof Seaweed) {
                ((Seaweed) member).wave();
            } else if (member instanceof Fish) {
                ((Fish) member).move(this.gridwidth, this.gridheight);
            } else if (member instanceof Bubble) {
                ((Bubble) member).floatDirection();
                if (member.y < 0) {
                    myLittleFishies.remove(member);
                }
            } else {
                ((Shark) member).move(this.gridwidth);
                ((Shark) member).eat_fish();
            }
        }
    }

    void createTankItems() {
        myLittleFishies.add(new Fish(7, 18, this));
        myLittleFishies.add(new Fish(10, 22, this));
        myLittleFishies.add(new Fish(17, 14, this));
        myLittleFishies.add(new Fish(15, 28, this));
        myLittleFishies.add(new Fish(5, 18, this));
        myLittleFishies.add(new Fish(16, 5, this));
        Fish hungry = new Fish(10, 10, this);
        hungry.getsHungry();
        myLittleFishies.add(hungry);
        Fish full = new Fish(16, 12, this, true);
        full.getsFull();
        myLittleFishies.add(full);
        myLittleFishies.add(new Fish(16, 22, this));
        myLittleFishies.add(new Fish(23, 18, this));
        myLittleFishies.add(new Fish(6, 12, this, true));
        myLittleFishies.add(new Fish(10, 20, this, true));
        myLittleFishies.add(new Seaweed(33, 20, this, 9));
        myLittleFishies.add(new Seaweed(24, 13, this, 6));
        myLittleFishies.add(new Seaweed(3, 15, this, 12));
        myLittleFishies.add(new Seaweed(13, 10, this, 5));
        myLittleFishies.add(new Shark(13, 10, this));
        myLittleFishies.add(new Shark(13, 15, this));

    }
}
