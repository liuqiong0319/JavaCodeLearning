package learning.work.first.head.test.page486;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by qiong.liu on 2018/11/26.
 */
public class SimpleChatClient2 {

    JTextArea incoming;
    JTextField outgoing;
    BufferedReader reader;
    PrintWriter writer;
    Socket socket;

    public static void main(String[] args) {
        SimpleChatClient2 client=new SimpleChatClient2();
        client.go();
    }

    private void go() {
        JFrame frame=new JFrame("Simple Char Client");
        JPanel mainPanel=new JPanel();
        incoming=new JTextArea(15,20);
        incoming.setLineWrap(true);
        incoming.setWrapStyleWord(true);
        incoming.setEditable(false);

        JScrollPane mScroller=new JScrollPane(incoming);
        mScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        mScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        outgoing=new JTextField(20);
        JButton sendButton=new JButton("Send");
        sendButton.addActionListener(new SendButtonListener());
        mainPanel.add(mScroller);
        mainPanel.add(outgoing);
        mainPanel.add(sendButton);
        setUpNetworking();
        Thread readerThread=new Thread(new IncomingReader());
        readerThread.start();

        frame.getContentPane().add(BorderLayout.CENTER,mainPanel);
        frame.setSize(400,500);
        frame.setVisible(true);

    }

    private void setUpNetworking() {

        try{
            socket=new Socket("127.0.0.1",5001);
            InputStreamReader sreamReader=new InputStreamReader(socket.getInputStream());
            reader=new BufferedReader(sreamReader);
            writer=new PrintWriter(socket.getOutputStream());
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    private class SendButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                writer.println(outgoing.getText());
                writer.flush();
            }catch (Exception ex){
                ex.printStackTrace();
            }
            outgoing.setText("");
            outgoing.requestFocus();
        }
    }

    public class IncomingReader implements Runnable {
        @Override
        public void run() {
            String message;
            try{
                while ((message=reader.readLine())!=null){
                    System.out.println("read "+ message);
                    incoming.append(message+"\n");
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }

        }
    }
}
