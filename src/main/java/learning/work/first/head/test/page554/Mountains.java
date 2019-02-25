package learning.work.first.head.test.page554;

/**
 * Created by qiong.liu on 2018/12/28.
 */
public class Mountains {
    private  String name;
    private int height;

    public Mountains(String name, int height) {
        this.name = name;
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return  name + ' ' +height;
    }
}
