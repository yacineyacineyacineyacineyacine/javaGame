package com.hdtech.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private Handler handler;
    private Game game;
    private boolean [] keyDown = new boolean[4];

    public KeyInput(Handler handler, Game game){
      this.handler = handler;
      this.game = game;
      keyDown[0] = false;
      keyDown[1] = false;
      keyDown[2] = false;
      keyDown[3] = false;
    }
    public void keyPressed(KeyEvent e){
       int key = e.getKeyCode();
       for (int i = 0; i < handler.objects.size(); i++){
           GameObject tempObject = handler.objects.get(i);

           if(tempObject.getId().equals(ID.Player)){


               if(key == KeyEvent.VK_UP)    {AudioPlayer.getSound("menu_sound").play(); tempObject.setVelY(-5); keyDown[0] = true; }
               if(key == KeyEvent.VK_DOWN)  {AudioPlayer.getSound("menu_sound").play(); tempObject.setVelY(5);  keyDown[1] = true;}
               if(key == KeyEvent.VK_RIGHT) {AudioPlayer.getSound("menu_sound").play(); tempObject.setVelX(5);  keyDown[2] = true;}
               if(key == KeyEvent.VK_LEFT)  {AudioPlayer.getSound("menu_sound").play(); tempObject.setVelX(-5); keyDown[3] = true;}
           }
       }
       if(key == KeyEvent.VK_ESCAPE) System.exit(1);
       if(key == KeyEvent.VK_P){
           if(game.gameState == Game.STATE.Game){
               if(game.paused) Game.paused = false;
               else game.paused = true;
           }
       }
    }


    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        for (int i = 0; i < handler.objects.size(); i++){
            GameObject tempObject = handler.objects.get(i);

            if(tempObject.getId().equals(ID.Player)){


                if(key == KeyEvent.VK_UP)    keyDown[0] = false;//tempObject.setVelY(0);
                if(key == KeyEvent.VK_DOWN)  keyDown[1] = false; //tempObject.setVelY(0);
                if(key == KeyEvent.VK_RIGHT) keyDown[2] = false;//tempObject.setVelX(0);
                if(key == KeyEvent.VK_LEFT)  keyDown[3] = false; //tempObject.setVelX(0);
                if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
                if(!keyDown[2] && !keyDown[3]) tempObject.setVelX(0);
            }
        }
    }
}
