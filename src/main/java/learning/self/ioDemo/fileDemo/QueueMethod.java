package java.learning.self.ioDemo.fileDemo;

import java.util.LinkedList;

/**
 * Created by qiong.liu on 2018/4/19.
 */
public class QueueMethod<E> {
        private LinkedList<E> link;

        public QueueMethod() {
            link = new LinkedList<E>();
        }

        public void myAdd(E obj) {
            link.addFirst(obj);
        }

        public E myGet() {
            return link.removeLast();
        }

        public boolean isNull() {
            return link.isEmpty();
        }

}
