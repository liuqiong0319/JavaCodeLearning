package learning.work.first.head.test.page364;

import javax.swing.*;
import java.awt.*;

/**
 * Created by qiong.liu on 2018/11/15.
 */
public class MyDrawPanel extends JPanel {
    @Override
    public void paintComponent(Graphics g){
        g.setColor(Color.orange);
        g.fillRect(20,50,100,100);
    }

    public static void main(String[] args) {

        MyDrawPanel myDrawPanel=new MyDrawPanel();
//        myDrawPanel.paintComponent(Graphics g);
    }
}
