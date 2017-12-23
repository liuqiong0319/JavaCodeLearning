package learning.self.day08;

import java.io.Serializable;

/**
 * Created by qiong.liu on 2017/12/18.
 */
public class Animal  implements Serializable{
    private static final long serialVersionUID = 8822818790694831649L;
    private String name;
    private String color;
    private String[] alias;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public String[] getAlias() {
        return alias;
    }
    public void setAlias(String[] alias) {
        this.alias = alias;
    }

    public Animal(String name, String color, String[] alias) {
        this.name = name;
        this.color = color;
        this.alias = alias;
    }

    public Animal() {
    }

    public Animal(String name) throws AgeOutOfLimitException{
        if (name.equals("111"))
        {
            throw new AgeOutOfLimitException(name+"非法");
        }
        this.name = name;
    }
}