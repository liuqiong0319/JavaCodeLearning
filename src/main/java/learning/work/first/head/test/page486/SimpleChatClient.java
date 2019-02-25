package learning.work.first.head.test.page486;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by qiong.liu on 2018/11/21.
 */
public class SimpleChatClient {
    JTextField outgoing;
    PrintWriter writer;
    BufferedReader br;
    JTextArea chatBox;
    Socket sock;

    public void go() throws Exception{
        JFrame frame=new JFrame("Simple Chat Client");
        Font bigFont=new Font("sanserif",Font.BOLD,18);
        JPanel mainPanel=new JPanel();
        outgoing =new JTextField(20);
        chatBox=new JTextArea(10,20);
        chatBox.setFont(bigFont);
        chatBox.setEditable(false);
        chatBox.setLineWrap(true);

        JScrollPane chatPane=new JScrollPane(chatBox);
        chatPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        chatPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);


        JButton sendButton=new JButton("Send");
        sendButton.addActionListener(new SendButtonListener());

        mainPanel.add(outgoing);
        mainPanel.add(sendButton);
        mainPanel.add(chatPane);
        frame.getContentPane().add(BorderLayout.CENTER,mainPanel);
        setUpNetworking();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(640,500);
        frame.setVisible(true);
    }
    private void setUpNetworking() throws Exception{
        sock=new Socket("127.0.0.1",5001);
        writer = new PrintWriter(sock.getOutputStream());
//        br=new BufferedReader(new InputStreamReader(sock.getInputStream()));
//        String message=br.readLine();
//        System.out.println(message);

    }

    public class SendButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String message=outgoing.getText();
            writer.println(message);
            writer.flush();
            chatBox.append("用户"+sock.getInetAddress().getHostName()+":"+message+"\n");
            outgoing.setText("");
            outgoing.requestFocus();
        }
    }
    public static void main(String[] args) throws Exception{
       new SimpleChatClient().go();
    }
}
