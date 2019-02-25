package learning.work.first.head.test.page486;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by qiong.liu on 2018/11/23.
 */
public class SimpleChatServer2 {
    ArrayList clientOutputStreams;

    public class ClientHandler implements Runnable{
        BufferedReader reader;
        Socket sock;

        public ClientHandler(Socket sock) {
            try {
                this.sock = sock;
                InputStreamReader isReader=new InputStreamReader(sock.getInputStream());
                reader=new BufferedReader(isReader);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

        @Override
        public void run() {
            String message;
            try{
                while ((message=reader.readLine())!=null){
                    System.out.println("read "+message);
                    tellEveryone(message);
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new SimpleChatServer2().go();
    }

    private void go() {
        clientOutputStreams=new ArrayList();
        try{
            ServerSocket ss=new ServerSocket(5001);
            while (true){
                Socket s=ss.accept();
                PrintWriter writer=new PrintWriter(s.getOutputStream());
                clientOutputStreams.add(writer);

                Thread t=new Thread(new ClientHandler(s));
                t.start();
                System.out.println("got a connection");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void tellEveryone(String message) {
        Iterator it=clientOutputStreams.iterator();
        while (it.hasNext()){
            try{
                PrintWriter writer=(PrintWriter)it.next();
                writer.println();
                writer.flush();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
