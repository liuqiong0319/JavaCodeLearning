package learning.work.first.head.test.page379;

import learning.work.first.head.test.page364.MyDrawPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by qiong.liu on 2018/11/15.
 */
public class TwoButtons {
    JFrame frame;
    JLabel label;

    public static void main(String[] args) {
        TwoButtons gui=new TwoButtons();
        gui.go();
    }

    private void go() {
        frame=new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton labelButton=new JButton("change label");
        labelButton.addActionListener(new LabelListener());
        JButton colorButton=new JButton("change circle");
        colorButton.addActionListener(new ColorListener());

        label=new JLabel("I'm a label");
        MyDrawPanel drawPanel=new MyDrawPanel();

        frame.getContentPane().add(BorderLayout.SOUTH,colorButton);
        frame.getContentPane().add(BorderLayout.CENTER,drawPanel);
        frame.getContentPane().add(BorderLayout.EAST,labelButton);
        frame.getContentPane().add(BorderLayout.WEST,label);

        frame.setSize(300,300);
        frame.setVisible(true);

    }

    private class LabelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            label.setText("Ouch!");

        }
    }

    private class ColorListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.repaint();

        }
    }
}
