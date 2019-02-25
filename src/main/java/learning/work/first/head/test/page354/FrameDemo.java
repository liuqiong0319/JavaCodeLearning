package learning.work.first.head.test.page354;

import javax.swing.*;
import java.awt.*;

/**
 * Created by qiong.liu on 2018/10/29.
 */
public class FrameDemo {

    public static void main(String[] args) {
        //创建一个窗体,并给窗体命名
        JFrame frame=new JFrame("frame demo");
        //设置窗体大小
        frame.setSize(300,300);
        //设置用户关闭窗体时执行的操作,以下操作表示关闭窗体时退出程序
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //创建组件并将组件命名
        JButton button=new JButton("click me");
        button.doClick();
        //设置组件大小
        button.setSize(300,300);
        //将组件添加至窗体中,不是直接添加到窗体中,而是添加至窗体的窗格(pane)中
        frame.getContentPane().add(button, BorderLayout.CENTER);

        //调整此窗口大小,以适应其组件的首选大小和布局
        frame.pack();

        //展示窗体
        frame.setVisible(true);
    }
}
