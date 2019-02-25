package learning.work.first.head.test.page346;

import javax.sound.midi.*;

/**
 * Created by qiong.liu on 2018/10/29.
 */
public class MiniMusicCmdLine {
    public static void main(String[] args) {

        MiniMusicCmdLine miniMusicCmdLine=new MiniMusicCmdLine();
        if (args.length<2){
            System.out.println("Don't forget the instument and note args");
        }
        else {
            int instrument=Integer.parseInt(args[0]);
            int note=Integer.parseInt(args[1]);
            miniMusicCmdLine.play(instrument,note);
        }

    }

    public void play(int instrument,int note){
        try {
            Sequencer sequencer= MidiSystem.getSequencer();
            sequencer.open();
            Sequence sequence=new Sequence(Sequence.PPQ,4);
            Track track=sequence.createTrack();
            MidiEvent event=null;

            ShortMessage first=new ShortMessage();
            first.setMessage(192,1,instrument,0);
            MidiEvent changeInstrument=new MidiEvent(first,1);
            track.add(changeInstrument);

            ShortMessage second=new ShortMessage();
            second.setMessage(133,1,note,100);
            MidiEvent noteOn=new MidiEvent(second,1);
            track.add(noteOn);

            ShortMessage third=new ShortMessage();
            third.setMessage(128,1,note,100);
            MidiEvent noteOff=new MidiEvent(third,16);
            track.add(noteOff);

            sequencer.setSequence(sequence);
            sequencer.start();


        }catch (MidiUnavailableException me){
            me.printStackTrace();
        }catch (InvalidMidiDataException ie){
            ie.printStackTrace();
        }


    }
}
