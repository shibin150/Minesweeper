package com.sxt;


import java.awt.*;

public class Background {
     static Image backgroundbase =Toolkit.getDefaultToolkit().getImage("imgs/backgroundbase.png");
    void paintSelf(Graphics g){
        g.drawImage(backgroundbase,0,0,null);

    }

}
 class Backgroundeasy {
    static Image backgroundeasy =Toolkit.getDefaultToolkit().getImage("imgs/backgroundeasy.png");
    void paintSelf(Graphics g){
        g.drawImage(backgroundeasy,0,0,null);

    }

}
 class Backgroundmid {
    static Image backgroundmid =Toolkit.getDefaultToolkit().getImage("imgs/backgroundmid.png");
    void paintSelf(Graphics g){
        g.drawImage(backgroundmid,0,0,null);

    }

}
 class Backgroundhard {
    static Image backgroundhard =Toolkit.getDefaultToolkit().getImage("imgs/backgroundhard.png");
    void paintSelf(Graphics g){
        g.drawImage(backgroundhard,0,0,null);

    }

}
