package com.hdtech.main;

import java.awt.*;

public class Trail extends GameObject {

    private float alpha = 1;
    private Handler handler;
    private Color color;
    private int width, height;
    private float life;


    public Trail(float x, float y, ID id, int width, int height, Color color, float life, Handler handler ) {
        super(x, y, id);
        this.handler = handler;
        this.color = color;
        this.width = width;
        this.height = height;
        this.life =life;
    }

    public void tick() {

        if(alpha > life){
            alpha -= life - 0.001f;

        }else {
            handler.removeObject(this);
        }

    }


    public void render(Graphics g) {
       Graphics2D g2d = (Graphics2D) g;
       g2d.setComposite(makeTransparent(alpha));
       g2d.setColor(color);
       g2d.fillRect((int) x, (int) y, width, height);
       g2d.setComposite(makeTransparent(alpha));


    }

    private AlphaComposite makeTransparent(float alpha){
        int type = AlphaComposite.SRC_OVER;
        return AlphaComposite.getInstance(type, alpha);
    }


    public Rectangle getBounds() {
        return null;
    }


}
