package learning.work.first.head.test.page392;

import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;

/**
 * Created by qiong.liu on 2018/11/16.
 */
public class MiniMusicPlayers {
    static JFrame f=new JFrame("my first music video");
    static MyDrawPanel ml;

    public static void main(String[] args) {
        MiniMusicPlayers mini=new MiniMusicPlayers();
        mini.go();
    }

    private void go() {
        setUpGui();
        try{
            Sequencer sequencer= MidiSystem.getSequencer();
            sequencer.open();
            sequencer.addControllerEventListener(ml,new int[] {127});
            Sequence seq=new Sequence(Sequence.PPQ,4);
            Track track=seq.createTrack();
            int r=0;
            for (int i=0;i<60;i+=4){
                r=(int)((Math.random()*50)+1);
                track.add(makeEvent(144,1,r,100,i));
                track.add(makeEvent(176,1,127,0,i));
                track.add(makeEvent(128,1,r,100,i+2));
            }
            sequencer.setSequence(seq);
            sequencer.setTempoInBPM(120);
            sequencer.start();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private MidiEvent makeEvent(int i, int i1, int r, int i2, int i3) {
        MidiEvent event=null;
        try {
            ShortMessage a=new ShortMessage();
            a.setMessage(i,i1,r,i2);
            event=new MidiEvent(a,i3);
        }catch (Exception e){}
        return event;
    }

    private void setUpGui() {
        ml=new MyDrawPanel();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setContentPane(ml);
        f.setBounds(30,30,300,300);
        f.setVisible(true);
    }

    class MyDrawPanel extends JPanel implements ControllerEventListener{
        boolean msg=false;
        public void controlChange(ShortMessage event){
            msg=true;
            repaint();
        }
        public void paintComponent(Graphics g){
            if (msg){
                Graphics2D g2=(Graphics2D)g;
                int r=(int)(Math.random()*250);
                int gr=(int)(Math.random()*250);
                int b=(int)(Math.random()*250);
                g.setColor(new Color(r,gr,b));

                int ht=(int)((Math.random()*120)+10);
                int width=(int)((Math.random()*120)+10);

                int x=(int)((Math.random()*40)+10);
                int y=(int)((Math.random()*40)+10);

                g.fillRect(x,y,ht,width);
                msg=false;
            }
        }
    }
}
