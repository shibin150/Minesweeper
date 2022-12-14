package com.sxt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameWin extends JFrame {

    int wigth = 2 * GameUtil.OFFSET + GameUtil.MAP_W * GameUtil.SQUARE_LENGTH;
    int height = 4 * GameUtil.OFFSET + GameUtil.MAP_H * GameUtil.SQUARE_LENGTH;

    Image offScreenImage = null;
    Image offScreenImagetwo = null;

    MapBottom mapBottom = new MapBottom();
    MapTop mapTop = new MapTop();
    GameSelect gameSelect = new GameSelect();
    Background backgroundbase=new Background();
    Backgroundeasy backgroundeasy=new Backgroundeasy();
    Backgroundmid backgroundmid=new Backgroundmid();
    Backgroundhard backgroundhard=new Backgroundhard();
    //是否开始,f未开始,t开始
    boolean begin=false;

    void launch(){
        GameUtil.START_TIME=System.currentTimeMillis();
        this.setVisible(true);
        if(GameUtil.state==3){
            this.setSize(500,500);
            this.setResizable(false);
        }else {
            this.setSize(wigth,height);
            this.setResizable(false);
        }
        this.setLocationRelativeTo(null);
        this.setTitle("扫雷游戏");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //鼠标事件
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                switch (GameUtil.state){
                    case 0 :
                        if(e.getButton()==1){
                        GameUtil.MOUSE_X = e.getX();
                        GameUtil.MOUSE_Y = e.getY();
                        GameUtil.LEFT = true;
                        }
                        if(e.getButton()==3) {
                            GameUtil.MOUSE_X = e.getX();
                            GameUtil.MOUSE_Y = e.getY();
                            GameUtil.RIGHT = true;
                        }
                    case 1 :
                    case 2 :
                    case 6 :
                    case 7 :
                        GameUtil.isPause = false;
                        if(e.getButton()==1){
                            if(e.getX()>GameUtil.OFFSET + GameUtil.SQUARE_LENGTH*(GameUtil.MAP_W/2)
                                    && e.getX()<GameUtil.OFFSET + GameUtil.SQUARE_LENGTH*(GameUtil.MAP_W/2) + GameUtil.SQUARE_LENGTH
                                    && e.getY()>GameUtil.OFFSET
                                    && e.getY()<GameUtil.OFFSET+GameUtil.SQUARE_LENGTH){
                                mapBottom.reGame();
                                mapTop.reGame();
                                GameUtil.FLAG_NUM=0;
                                GameUtil.START_TIME=System.currentTimeMillis();
                                GameUtil.state=0;
                            }
                        }
                        if(e.getButton()==2){
                            GameUtil.state=3;
                            begin=true;
                        }
                        break;
                    case 3:
                        if(e.getButton()==1){
                        GameUtil.MOUSE_X = e.getX();
                        GameUtil.MOUSE_Y = e.getY();
                        begin = gameSelect.hard();
                        }

                        break;
                    default:

                }


            }
        });


        while (true){
            repaint();
            begin();
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    void begin(){
        if(begin){
            begin=false;
            gameSelect.hard(GameUtil.level);
            dispose();
            GameWin gameWin = new GameWin();
            GameUtil.START_TIME = System.currentTimeMillis();
            GameUtil.FLAG_NUM=0;
            mapBottom.reGame();
            mapTop.reGame();
            gameWin.launch();
        }
    }

    @Override
    public void paint(Graphics g) {
        if(GameUtil.state==3){
            offScreenImagetwo= this.createImage(wigth,height);
            Graphics gImagetwo=offScreenImagetwo.getGraphics();
            gImagetwo.fillRect(0,0,500,500);
            backgroundbase.paintSelf(gImagetwo);
            gameSelect.paintSelf(gImagetwo);
            g.drawImage(offScreenImagetwo,0,0,null);

        }else {
            offScreenImage = this.createImage(wigth, height);
            Graphics gImage = offScreenImage.getGraphics();
            gImage.fillRect(0,0,wigth,height);
            if(GameUtil.level==1){
                backgroundeasy.paintSelf(gImage);
            }
            else if (GameUtil.level==2) {
                backgroundmid.paintSelf(gImage);
            }
            else if(GameUtil.level==3) {
                backgroundhard.paintSelf(gImage);
            }
            mapBottom.paintSelf(gImage);
            mapTop.paintSelf(gImage);
            g.drawImage(offScreenImage, 0, 0, null);
        }
    }

    public static void main(String[] args) {
        Runnable playSound=new PlaySound("bgm.wav");
        Thread thread =new Thread(playSound);
        thread.start();
        GameWin gameWin = new GameWin();
        gameWin.launch();

    }
}
