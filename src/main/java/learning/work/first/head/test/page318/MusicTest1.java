package learning.work.first.head.test.page318;

import javax.sound.midi.*;

/**
 * Created by qiong.liu on 2018/10/26.
 * sequencer 演奏 sequence，它包含音轨，而音轨包含 MIDI 事件
 */
public class MusicTest1 {

    static Sequencer sequencer;//取得播放设备

    public static void play() {
//        回放 MIDI sequence 的硬件或软件设备就是所谓的 sequencer。MIDI sequence 包含加了时间戳的 MIDI 数据列表，例如可从标准 MIDI 文件读取的数据
//        Sequence 可从一个 MIDI 文件创建，方法是将该文件读入到输入流并调用 MidiSystem 的某个 getSequence 方法
        try {
            sequencer= MidiSystem.getSequencer();
            sequencer.open();//打开设备,继承父类MidiDevice的方法

            Sequence sequence=new Sequence(Sequence.SMPTE_24,4);//创建一个具有指定的定时 division 类型和定时精度的新 MIDI音乐信息供sequencer回放
            Track track=sequence.createTrack();// 创建一个新的、初始为空的轨道作为此 Sequence 的一部分

            //创建事件,即相当于音符,开机音符
            ShortMessage a=new ShortMessage();
            a.setMessage(144,1,44,100);//表示发出44音符
            MidiEvent noteOn=new MidiEvent(a,1);//表示在第一拍启动这个message
            track.add(noteOn);//将新事件添加至音轨。


            //创建事件,即相当于音符,关机音符
            ShortMessage b=new ShortMessage();//描述做什么
            b.setMessage(128,1,44,100);
            /**
             *message带事件要执行什么操作的部分也就是sequencer实际执行的指令.
             * 创建midi的message,使用shortMessage的实例调用setMessage()
             * b.setMessage(128,1,44,100);
             * 信息格式:
             * 第一个参数表示信息类型,即144在此处代表开机,128在此处代表关机
             * 第二个参数表示频道即不同的乐器演奏,如1频道表示吉他,2频道表示古筝等
             * 第三个参数表示要发出的音符.0-127代表不同音高
             * 第四个参数表示音道,即多大的音道按下?0几乎听不到.
             */
            MidiEvent noteOff =new MidiEvent(a,16);//指定何时做
            //MidiEvent(a,16)
            //第一个参数表示要执行的内容,第二个参数表示要执行的时机
            track.add(noteOff);//将新事件添加至音轨。

            //将sequence送到sequencer上
            sequencer.setSequence(sequence);
            sequencer.start();//开始回放当前已加载 sequence 中的 MIDI 数据

        } catch (MidiUnavailableException me) {
            me.printStackTrace();
        }catch (InvalidMidiDataException ie ) {
            ie.printStackTrace();
        }finally{
            sequencer.close();
        }
    }

    public static void play2() throws MidiUnavailableException{
        Sequencer sequencer=MidiSystem.getSequencer();
        throw  new MidiUnavailableException("异常发生了!!!");

    }

    public static void main(String[] args) throws Exception{
        play();
//        play2();
    }
}
