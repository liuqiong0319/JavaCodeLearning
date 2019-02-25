package learning.work.first.head.test.page360;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by qiong.liu on 2018/10/29.
 * 创建GUI四个步骤
 * 1.创建window(JFrame)
 * JFrame frame=new JFrame();
 * 2.创建组件
 * JButton button=new JButton("click me");
 * 3.将组件添加到frame上
 * frame.getContentPane().add(button);
 * 4.显示出来
 * frame.setSize(300,300);
 * frame.setVisible(true);
 */
public class SimpleGui1B implements ActionListener {

    JButton button;

    public static void main(String[] args) {
        SimpleGui1B simpleGui1B=new SimpleGui1B();
        simpleGui1B.go();

    }


    public  void go(){
        JFrame frame=new JFrame("frame demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        button=new JButton("click here");

        button.addActionListener(this);

        frame.getContentPane().add(button);

        frame.setSize(300,300);

        frame.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {

        button.setText("I have been clicked");

    }
}
