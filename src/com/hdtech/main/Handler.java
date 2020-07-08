package com.hdtech.main;

import java.awt.*;
import java.util.LinkedList;

public class Handler {

    public LinkedList<GameObject> objects = new LinkedList<GameObject>();

    public void tick(){
     for (int i = 0; i < objects.size(); i++){

         GameObject tempObject = objects.get(i);
         tempObject.tick();
     }
    }

    public void clearEnemies() {
        for (int i = 0; i < objects.size(); i++){
            GameObject tempObject =  objects.get(i);
            if(tempObject.getId() == ID.Player){
                objects.clear();
                if(Game.gameState != Game.STATE.End){
                    addObject(new Player((int) tempObject.getX(), (int) tempObject.getY()  - 32, ID.Player, this));
                }

            }
        }
    }

    public void render(Graphics g){
        if(objects.size() > 0){
            for (int i = 0; i < objects.size(); i++){
                GameObject tempObject = objects.get(i);
                tempObject.render(g);
            }
         }

    }

    public void addObject(GameObject object){
        objects.add(object);
    }

    public void removeObject(GameObject object){
        objects.remove(object);
    }


}
