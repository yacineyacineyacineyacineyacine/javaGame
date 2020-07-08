package com.hdtech.main;

import java.awt.*;
import java.util.Random;

public class EnemyBossBullet extends GameObject {
    private Handler handler;
    private Random r = new Random();
    public EnemyBossBullet(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velX = (r.nextInt(5 - -5) + -5);
        velY = 5;
    }

    public Rectangle getBounds(){
        return new Rectangle((int) x , (int) y , 16, 16);
    }
    public void tick() {
        x+= velX;
        y+= velY;

        //if(y <= 0 || y >= Game.HEIGHT - 48 ) velY *= -1;
        //if(x <= 0 || x >= Game.WIDTH - 48 ) velX *= -1;
        if(y >= Game.HEIGHT) handler.removeObject(this);
        handler.addObject(new Trail((int) x, (int) y, ID.Trail, 16, 16, Color.RED , 0.02f, handler));


    }


    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect((int) x , (int) y,16, 16);
    }


}
