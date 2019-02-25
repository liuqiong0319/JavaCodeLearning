package learning.work.first.head.test.page650;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Track;
import javax.swing.*;
import java.awt.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

/**
 * Created by qiong.liu on 2018/12/29.
 */
public class BeatBoxFinal {
    JFrame theFreame;
    JPanel mainPanel;
    JList incomingList;
    JTextField userMessage;
    ArrayList<JCheckBox> checkBoxList;
    Vector<String> listVector=new Vector<String>();
    String userName;
    ObjectOutputStream out;
    ObjectInputStream in;
    HashMap<String,boolean[]> otherSeqMap=new HashMap<String, boolean[]>();
    Sequencer sequencer;
    Sequence sequence;
    Sequence mySequence=null;
    Track track;

    String instrumentNames[]={"Bass Drum","Closed Hi-Hat","Open Hi-Hat","Acoustic Snare",
            "Crash Cymbal","Hand Clap","High Tom","Hi Bongo","Maracas","Whistle","Low Conga",
            "CowBell","Vibraslap","Low-Mid Tom","High Agogo","Open Hi Conga"};
    int instruments[]={35,42,46,38,49,39,50,60,70,72,64,56,58,47,67,63};


    public static void main(String[] args) {
        new BeatBoxFinal().startUp(args[0]);
    }

    private void startUp(String name) {
        userName=name;
        try {
            Socket socket=new Socket("127.0.0.1",32034);
            out=new ObjectOutputStream(socket.getOutputStream());
            in=new ObjectInputStream(socket.getInputStream());
            Thread remote=new Thread(new RemoteReader());
            remote.start();


        }catch (Exception ex){
            System.out.println("could not connect - you'll have to paly it alone");
        }
        setUpMidi();
        buildGUI();
    }

    private void buildGUI() {

        theFreame=new JFrame("Cyber BeatBox");
        BorderLayout layout=new BorderLayout();
        JPanel backGround=new JPanel(layout);
        backGround.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        checkBoxList =new ArrayList<JCheckBox>();
        Box buttonBox=new Box(BoxLayout.Y_AXIS);
        JButton start=new JButton("Start");
        start.addActionListener(new MyStartListener());
        buttonBox.add(start);

        JButton stop=new JButton("Stop");
        start.addActionListener(new MyStopListener());
        buttonBox.add(stop);

        JButton upTempo=new JButton("Tempo Up");
        start.addActionListener(new MyUpTempoListener());
        buttonBox.add(upTempo);

        JButton downTempo=new JButton("Tempo Down");
        start.addActionListener(new MyDownTempoListener());
        buttonBox.add(downTempo);

        JButton sendIt=new JButton("SendIt");
        start.addActionListener(new MySendItListener());
        buttonBox.add(sendIt);

        userMessage=new JTextField();
        buttonBox.add(userMessage);

        incomingList=new JList();
        incomingList.addListSelectionListener(new MySelectionListener());
        incomingList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        JScrollPane theList=new JScrollPane(incomingList);
        buttonBox.add(theList);
        incomingList.setListData(listVector);

        Box nameBox=new Box(BoxLayout.Y_AXIS);
        for (int i=0;i<16;i++){
            nameBox.add(new Label(instrumentNames[i]));
        }

        backGround.add(BorderLayout.EAST,buttonBox);
        backGround.add(BorderLayout.WEST,nameBox);

        theFreame.getContentPane().add(backGround);
        GridLayout grid=new GridLayout(16,16);
        grid.setVgap(1);
        grid.setVgap(2);
        mainPanel=new JPanel(grid);
        backGround.add(BorderLayout.CENTER,mainPanel);
        for (int i=0;i<256;i++){
            JCheckBox c=new JCheckBox();
            c.setSelected(false);
            checkBoxList.add(c);
            mainPanel.add(c);
        }
        theFreame.setBounds(50,50,300,300);
        theFreame.pack();;
        theFreame.setVisible(true);

    }

    private void setUpMidi() {
        try{
            sequencer= MidiSystem.getSequencer();
            sequencer.open();
            sequence=new Sequence(Sequence.PPQ,4);
            track=sequence.createTrack();
            sequencer.setTempoInBPM(120);


        }catch (Exception ex){

        }

    }
    
    
}
