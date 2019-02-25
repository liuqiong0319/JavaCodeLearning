package java.learning.self.awt.frame;

import java.awt.*;
import java.awt.event.*;

/**
 * Created by qiong.liu on 2018/5/25.
 */
public class FrameDemo {


    public static void main(String[] args) {
        //创建窗体
        Frame frame=new Frame("My Frame");

        //设置窗体大小
        frame.setSize(500,400);
        //设置窗体显示位置
        frame.setLocation(400,200);

        //设置布局
        frame.setLayout(new FlowLayout());

        //添加组件
        Button but=new Button("My button");
        TextField tf=new TextField(40);

        //添加文本框组件
        frame.add(tf);
        //将组件添加到窗体中
        frame.add(but);

        /**
         *  文本框中只能输入数字
         *  事件源:文本框
         *  文本框注册键盘监听
         *  事件处理
         */

        tf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
//                char key=e.getKeyChar();
//                int code=e.getKeyCode();
////                System.out.println(code+"……"+KeyEvent.getKeyText(code));
//                if (!(code>=KeyEvent.VK_0 && code<=KeyEvent.VK_9))
//                {
//                    System.out.println("必须是0-9的数字");
//                    e.consume();//取消默认处理方式
//                }

                if(e.isControlDown() && e.getKeyCode()==KeyEvent.VK_ENTER){
                    System.out.println("ctrl+enter running...");
                }
            }
        });



        /**
         * 需求:点击按钮有效果:打印一句话
         * 1,确定事件源:按钮
         * 2,添加监听器:给按钮添加监听器,在按钮对象中找
         * 2,确定监听后的处理方式:
         */

//        ActionListener al=new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
////                System.out.println("按钮被触发了……"+e);
////                System.exit(0);
//
//                for (int i=5;i>0;i--){
//                    for (int j=i;j>0;j--){
//                        System.out.print("*");
//                    }
//                    System.out.println();
//                }
//                System.exit(0);
//            }
//        };


        /**
         * 需求:点击窗体x就可以实现窗体的关闭
         * 思路:
         * 1,事件源:窗体
         * 2,监听器:通过窗口对象去查
         * 3,事件处理
         */

        //WindowAdapter接收窗口事件的抽象适配器类。此类中的方法为空。此类存在的目的是方便创建侦听器对象。没有抽象方法的抽象类
//        frame.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                super.windowClosing(e);
//                System.exit(0);
//            }
//            @Override
//            public void windowOpened(WindowEvent e) {
//                System.out.println("孔雀开屏!");
//            }
//
//        });
//
//        frame.addWindowListener(new WindowListener() {
//            @Override
//            public void windowOpened(WindowEvent e) {
//
//            }
//
//            @Override
//            public void windowClosing(WindowEvent e) {
//
//                System.out.println("window closing");
//                System.exit(0);
//
//            }
//
//            @Override
//            public void windowClosed(WindowEvent e) {
//
//            }
//
//            @Override
//            public void windowIconified(WindowEvent e) {
//
//            }
//
//            @Override
//            public void windowDeiconified(WindowEvent e) {
//
//            }
//
//            @Override
//            public void windowActivated(WindowEvent e) {
//
//            }
//
//            @Override
//            public void windowDeactivated(WindowEvent e) {
//
//            }
//        });


        /**
         * 需求:演示鼠标监听
         * 组件.addXXXListenr(new XXXAdapter(){
         * @Override
         *  public void xxxmethod(XXXEvent e){}
         * }
         * )
         *
         * 想要对鼠标双击进行处理,应该找鼠标事件对象.因为事件对象易产生,内部必然封装事件源以及事件相关内容.
         */

        but.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(e.getClickCount()==2) {
                    System.out.println("Mouse double click");
                    System.exit(0);
                }
            }
        });

//        but.addActionListener(al);
        //让窗体显示
        frame.setVisible(true);//显示窗体
    }
}
