package com.hdtech.main;

import java.awt.*;
import java.util.Random;


public class MenuParticale extends GameObject {
    private Handler handler;
    private Color color;
    private Random r = new Random();


    private int red;
    private int green;
    private int blue;

    public MenuParticale(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        red = r.nextInt(255);
        green = r.nextInt(255);
        blue = r.nextInt(255);
        this.color = new Color(red, green, blue);

        velX = (r.nextInt(7 - -7) + -7);
        velY = (r.nextInt(7 - -7) + -7);
        if (velX == 0) velX = 1;
        if(velY == 0) velY = 1;

    }

    public Rectangle getBounds(){
        return new Rectangle((int) x , (int) y , 16, 16);
    }
    public void tick() {
        x+= velX;
        y+= velY;

        if(y <= 0 || y >= Game.HEIGHT - 48 ) velY *= -1;
        if(x <= 0 || x >= Game.WIDTH - 48 ) velX *= -1;

        handler.addObject(new Trail((int) x, (int) y, ID.Trail, 16, 16, color , 0.05f, handler));


    }


    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect((int) x , (int) y,16, 16);
    }


}


