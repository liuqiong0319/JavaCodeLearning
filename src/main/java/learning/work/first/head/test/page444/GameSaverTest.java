package learning.work.first.head.test.page444;

import java.io.*;

/**
 * Created by qiong.liu on 2018/11/19.
 */
public class GameSaverTest {
    public static void main(String[] args) {
        GameCharacter one=new GameCharacter(50,"ELF",new String[]{"bow","sword","dust"});
        GameCharacter two=new GameCharacter(200,"Troll",new String[]{"bare hands","big ax"});
        GameCharacter three=new GameCharacter(120,"Magician",new String[]{"spells","invisibility"});

        try {
            ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(("/Users/qiong.liu/Documents/game.txt")));
            oos.writeObject(one);
            oos.writeObject(two);
            oos.writeObject(three);
            oos.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
//        one=null;
//        two=null;
//        three=null;
        try {
            ObjectInputStream ois=new ObjectInputStream(new FileInputStream(("/Users/qiong.liu/Documents/game.txt")));
            GameCharacter oneRestore=(GameCharacter)ois.readObject();
            GameCharacter twoRestore=(GameCharacter)ois.readObject();
            GameCharacter threeRestore=(GameCharacter)ois.readObject();

            System.out.println("One's type:"+oneRestore.getType());
            System.out.println("Two's type:"+twoRestore.getType());
            System.out.println("Three's type:"+threeRestore.getType());
            ois.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
