package java.learning.self.day08;

/**
 * Created by qiong.liu on 2017/12/20.
 */
public class NoteBook {
    private int state;

    public NoteBook(int state) {
        this.state = state;
    }

    public NoteBook() {
    }

    public void run()throws BlueScreenException,SmokeException{
        if(state==1) {
            throw new BlueScreenException("电脑蓝屏了");
        }
        if(state==2){
            throw new SmokeException("电脑冒烟了");
        }
        System.out.println("电脑运行");
    }
    public void restart(){
        state=0;
        System.out.println("电脑重启了");
    }
}


