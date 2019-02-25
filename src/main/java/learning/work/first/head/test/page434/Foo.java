package learning.work.first.head.test.page434;

import java.io.Serializable;

/**
 * Created by qiong.liu on 2018/11/16.
 */
 class Foo implements Serializable {
    int width;//transient,当无法序列化的变量不能被存储时,可以使用transient将他标记出来,序列化程序会把它跳过.
    int height;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
