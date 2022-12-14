package com.sxt;

import java.awt.*;

/**
 * 难度选择类
 */
public class GameSelect {

    //判断是否点击到难度
    boolean hard(){
        if(GameUtil.MOUSE_X>100&&GameUtil.MOUSE_X<400){
            if(GameUtil.MOUSE_Y>50&&GameUtil.MOUSE_Y<100){
                GameUtil.level=1;
                GameUtil.state=0;
                return true;
            }
            if(GameUtil.MOUSE_Y>150&&GameUtil.MOUSE_Y<200){
                GameUtil.level=2;
                GameUtil.state=0;
                return true;
            }
            if(GameUtil.MOUSE_Y>250&&GameUtil.MOUSE_Y<300){
                GameUtil.level=3;
                GameUtil.state=0;
                return true;
            }
        }
        return false;
    }

    void paintSelf(Graphics g){
        g.setColor(Color.white);
        g.drawRect(100,50,300,50);
        GameUtil.drawWord(g,"简单",220,85,30,Color.green);
        g.setColor(Color.white);
        g.drawRect(100,150,300,50);
        GameUtil.drawWord(g,"普通",220,185,30,Color.yellow);
        g.setColor(Color.white);
        g.drawRect(100,250,300,50);
        GameUtil.drawWord(g,"困难",220,285,30,Color.orange);
    }

    void hard(int level){
        switch (level){
            case 1:
                GameUtil.RAY_MAX = 5;
                GameUtil.MAP_W = 9;
                GameUtil.MAP_H = 9;
                break;
            case 2:
                GameUtil.RAY_MAX = 10;
                GameUtil.MAP_W = 10;
                GameUtil.MAP_H = 10;
                break;
            case 3:
                GameUtil.RAY_MAX = 15;
                GameUtil.MAP_W = 13;
                GameUtil.MAP_H = 13;
                break;
            default:
        }
    }
}
