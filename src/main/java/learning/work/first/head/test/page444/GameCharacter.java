package learning.work.first.head.test.page444;

import java.io.Serializable;

/**
 * Created by qiong.liu on 2018/11/19.
 */
public class GameCharacter implements Serializable {

    private String type;
    private int power;
    private String[] weapons;

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String[] getWeapons() {
        return weapons;
    }

    public void setWeapons(String[] weapons) {
        this.weapons = weapons;
    }

    public String getType() {
        return type;
    }
    public GameCharacter(int power, String type, String[] weapons) {
        this.power=power;
        this.type=type;
        this.weapons=weapons;

    }

}
