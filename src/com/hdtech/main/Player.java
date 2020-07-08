package com.hdtech.main;

import java.awt.*;
import java.util.Random;

public class Player extends GameObject {

    Random r = new Random();
    Handler handler;
    public Player(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

       // velX = 1;

    }
    public void collision(){
        for(int i =0; i < handler.objects.size(); i++){
            GameObject tempObject = handler.objects.get(i);
            if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy || tempObject.getId() == ID.EnemyBoss){
                if(getBounds().intersects(tempObject.getBounds())){
                    //collision code
                    HUD.HEALTH -= 2;
                    if(!(tempObject.getId() == ID.EnemyBoss)){
                        tempObject.setVelX( tempObject.getVelX() * -1);
                        tempObject.setVelY( tempObject.getVelY() * -1);
                    }


                }
            }

        }
    }
    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 32, 32);
    }

    public void tick() {
      x += velX;
      y += velY;

      x = Game.clamp(x, 0, Game.WIDTH - 64);
      y = Game.clamp(y, 0, Game.HEIGHT - 64);
        handler.addObject(new Trail((int)x, (int)y, ID.Trail, 32, 32, Color.WHITE , 0.05f, handler));

      collision();

    }




    public void render(Graphics g) {
      g.setColor(Color.WHITE);
      g.fillRect((int) x, (int) y, 32, 32);

    }




}
