package com.hdtech.main;

import java.awt.*;

public class BasicEnemy extends GameObject {
    private Handler handler;
    public BasicEnemy(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velX = 5;
        velY = 5;
    }

    public Rectangle getBounds(){
        return new Rectangle((int) x , (int) y , 16, 16);
    }
    public void tick() {
       x+= velX;
       y+= velY;

       if(y <= 0 || y >= Game.HEIGHT - 48 ) velY *= -1;
       if(x <= 0 || x >= Game.WIDTH - 48 ) velX *= -1;

       handler.addObject(new Trail((int) x, (int) y, ID.Trail, 16, 16, Color.RED , 0.02f, handler));


    }


    public void render(Graphics g) {
       g.setColor(Color.RED);
       g.fillRect((int) x , (int) y,16, 16);
    }


}
