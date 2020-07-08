package com.hdtech.main;

import java.awt.*;

public class SmartEnemy extends GameObject {
    private Handler handler;
    private GameObject player;

    public SmartEnemy(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        for(int i =0; i < handler.objects.size(); i++ ){

            if( handler.objects.get(i).getId() == ID.Player){
               player = handler.objects.get(i);
            }
        }
        velX = 5;
        velY = 5;
    }

    public Rectangle getBounds(){
        return new Rectangle((int) x , (int) y , 16, 16);
    }
    public void tick() {


        float diffX = x - player.getX() - 8;
        float diffY = y - player.getY() - 8;

        float distance = (float) Math.sqrt((x - player.getX())*(x - player.getX()) + (y - player.getY())*(y - player.getY()));

        velX = (float) ((-1.0)/distance * diffX);
        velY = (float) ((-1.0)/distance * diffY);

        x+= velX;
        y+= velY;

        //if(y <= 0 || y >= Game.HEIGHT - 48 ) velY *= -1;
        //if(x <= 0 || x >= Game.WIDTH - 48 ) velX *= -1;

        handler.addObject(new Trail((int) x, (int) y, ID.Trail, 16, 16, Color.green , 0.02f, handler));


    }


    public void render(Graphics g) {
        g.setColor(Color.green);
        g.fillRect((int) x , (int) y,16, 16);
    }


}
