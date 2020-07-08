package com.hdtech.main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import static com.hdtech.main.Game.WIDTH;
import static com.hdtech.main.Game.HEIGHT;

public class Menu extends MouseAdapter {

    private Game game;
    private Handler handler;
    private HUD hud;
    private Random r = new Random();

    public Menu(Game game, Handler handler, HUD hud){
        this.game = game;
        this.hud = hud;
        this.handler = handler;

    }

    public void mousePressed(MouseEvent e){

        int mx = e.getX();
        int my = e.getY();
            if(game.gameState == Game.STATE.Menu){
                //Play Button
                if(mousseOver(mx, my,210, 150, 200, 64 )){
                    game.gameState = Game.STATE.Game;

                    handler.addObject(new Player(WIDTH/2 - 32, HEIGHT/2  - 32, ID.Player, handler));
                    handler.clearEnemies();
                    handler.addObject(new BasicEnemy(r.nextInt(WIDTH) - 50, r.nextInt(Game.HEIGHT) - 50, ID.BasicEnemy, handler));

                    AudioPlayer.getSound("menu_sound").play();
                }

                //Help Button
                if(mousseOver(mx, my,210, 250, 200, 64)){
                    game.gameState = Game.STATE.Help;
                    AudioPlayer.getSound("menu_sound").play();
                }

                //Quite Button
                if(mousseOver(mx, my,210, 350, 200, 64)){
                    System.exit(1);
                }
            }



        //Back Button
        if(game.gameState == Game.STATE.Help){
            if(mousseOver(mx, my,210, 350, 200, 64)){
                game.gameState = Game.STATE.Menu;
                AudioPlayer.getSound("menu_sound").play();
                return;
            }
        }

        //Try Again Button
        if(game.gameState == Game.STATE.End){
            if(mousseOver(mx, my,210, 350, 200, 64)){
                Game.gameState = Game.STATE.Game;
                HUD.HEALTH = 100;
                hud.setScore(0);
                hud.setLevel(1);
                handler.addObject(new Player(WIDTH/2 - 32, HEIGHT/2  - 32, ID.Player, handler));
                handler.clearEnemies();
                handler.addObject(new BasicEnemy(r.nextInt(WIDTH) - 50, r.nextInt(Game.HEIGHT) - 50, ID.BasicEnemy, handler));
                AudioPlayer.getSound("menu_sound").play();

            }
        }




    }

    public void mouseReleased(MouseEvent e){

    }

    public boolean mousseOver(int mx, int my, int x, int y, int width, int height){
        if(mx > x && mx < x + width){
            if(my > y && my < y + height){
                return true;
            }else return false;
        }else return false;
    }

    public void tick(){


    }

    public void render(Graphics g){

        if(game.gameState == Game.STATE.Menu){
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 20);

            g.setFont(fnt);
            g.setColor(Color.WHITE);
            g.drawString("Wave", 240, 70);

            g.setFont(fnt2);
            g.drawRect(210, 150, 200, 64);
            g.drawString("Play", 280, 180);

            g.drawRect(210, 250, 200, 64);
            g.drawString("Help", 280, 280);

            g.drawRect(210, 350, 200, 64);
            g.drawString("Quite", 280, 380);

        }else if(game.gameState == Game.STATE.Help){
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 20);
            Font fnt3 = new Font("arial", 1, 18);

            g.setFont(fnt);
            g.setColor(Color.WHITE);
            g.drawString("Help", 240, 70);

            g.setFont(fnt3);
            g.drawString("Use the Up, Down, Right and Left key to dodge enemies",75,180);

            g.drawRect(210, 350, 200, 64);
            g.setFont(fnt2);
            g.drawString("Back", 280, 380);

        }else if(game.gameState == Game.STATE.End){
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 20);
            Font fnt3 = new Font("arial", 1, 18);

            g.setFont(fnt);
            g.setColor(Color.WHITE);
            g.drawString("Game Over", 200, 70);

            g.setFont(fnt3);
            g.drawString("You lost with a score of: " + hud.getScore() ,200,180);

            g.drawRect(210, 350, 200, 64);
            g.setFont(fnt2);
            g.drawString("Try Again", 260, 380);

        }if(game.gameState == Game.STATE.Select){
            Font fnt = new Font("arial", 1, 30);
            Font fnt2 = new Font("arial", 1, 20);

            g.setFont(fnt);
            g.setColor(Color.WHITE);
            g.drawString("Select Level Of Dificaulty", 150, 70);

            g.setFont(fnt2);
            g.drawRect(210, 150, 200, 64);
            g.drawString("Normal", 280, 180);

            g.drawRect(210, 250, 200, 64);
            g.drawString("Hard", 280, 280);

            g.drawRect(210, 350, 200, 64);
            g.drawString("Back", 280, 380);

        }

    }
}
