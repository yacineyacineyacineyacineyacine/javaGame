package com.hdtech.main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 3998879327168279813L;
    public static final int WIDTH = 640, HEIGHT = WIDTH /12 *9;
    private Thread thread;
    private boolean running = false;
    private Handler handler;
    private Menu menu;
    private Random r;
    private HUD hud;
    private Spawn spawner;

    public enum STATE {
        Menu,
        Select,
        Help,
        Game,
        End
    };

    public static STATE gameState = STATE.Menu;
    public static boolean paused = false;

    public Game(){

        handler = new Handler();
        hud = new HUD();
        menu = new Menu(this, handler, hud);
        this.addKeyListener(new KeyInput(handler, this));
        this.addMouseListener(menu);

        AudioPlayer.load();
        AudioPlayer.getMusic("music").loop();

        new Window(WIDTH, HEIGHT, "Let 's Build a Game", this);
        spawner = new Spawn(handler, hud);
        r = new Random();

        if(gameState == STATE.Game){
           handler.addObject(new Player(WIDTH/2 - 32, HEIGHT/2  - 32, ID.Player, handler));
           handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH) - 50, r.nextInt(Game.HEIGHT) - 50, ID.BasicEnemy, handler));
        }else {
            for (int i = 0; i < 10; i++){
                handler.addObject(new MenuParticale(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticale, handler));
            }
        }
    }

    public synchronized void start(){
     thread = new Thread(this);
     thread.start();
     running = true;
    }

    public void stop(){
        try {
            thread.join();
            running = false;
        }catch (Exception exp){
            exp.printStackTrace();
        }

    }

    public void run(){
         this.requestFocus();
         long lastTime = System.nanoTime();
         double amountOfTicks = 60.0;
         double ns = 1_000_000_000 / amountOfTicks;
         double delta = 0;
         long timer = System.currentTimeMillis();
         int frames = 0;
         while (running){
             long now = System.nanoTime();
             delta += (now - lastTime) /ns;
             lastTime = now;
             while (delta >= 1){
                 tick();
                 delta--;
             }

             if(running)
                 render();
             frames++;
             if(System.currentTimeMillis() - timer > 1000){
                 timer += 1000;
                 //System.out.println("FPS: + " + frames);
                 frames = 0;
             }
         }

         stop();

    }

    private void tick(){

        if(gameState == STATE.Game){
            if(!paused){
                hud.tick();
                spawner.tick();
                handler.tick();
                if(HUD.HEALTH <=0){
                    HUD.HEALTH = 100;
                    gameState = STATE.End;
                    handler.clearEnemies();
                    for (int i = 0; i < 10; i++){
                        handler.addObject(new MenuParticale(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticale, handler));
                    }
                }
            }
        }else if(gameState == STATE.Menu ||gameState == STATE.End || gameState == STATE.Select) {
            menu.tick();
            handler.tick();
        }
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g =  bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);

        if(paused) {g.setColor(Color.WHITE); g.drawString("PAUSED", 100, 100);}

        if(gameState == STATE.Game){
            hud.render(g);
        }else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End || gameState == STATE.Select){
            menu.render(g);
        }

        g.dispose();
        bs.show();
    }

    public static float clamp(float var, float min, float max){
        if(var >= max)
            return var = max;
        else if (var <= min)
            return var = min;
         else
            return var;

    }

    public static void main(String [] args){
      new Game();
    }
}
