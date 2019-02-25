package java.learning.self.day08;

import lombok.Data;

/**
 * Created by qiong.liu on 2017/12/20.
 */
@Data
public class Teacher {
    private String name;
    private NoteBook noteBook;

    public Teacher(String name) {
        this.name = name;
        noteBook = new NoteBook();
    }

    public Teacher() {
    }

//    public Teacher(NoteBook noteBook, String name) {
//
//        this.noteBook = noteBook;
//        this.name = name;
//    }

    public void teach(String name,NoteBook noteBook) throws NoplanException {
        try {
            noteBook.run();
            System.out.println(name + "老师开始讲课了");
        } catch ( BlueScreenException e ) {
            System.out.println(e.toString());
//            e.printStackTrace();
            noteBook.restart();
        } catch ( SmokeException e ) {
            System.out.println(e.toString());
            test();
            throw new NoplanException(e.getMessage()+",课时停止");
        }
        System.out.println(name+"老师讲课了");
    }

    public void test(){
        System.out.println("做练习");
    }
}
